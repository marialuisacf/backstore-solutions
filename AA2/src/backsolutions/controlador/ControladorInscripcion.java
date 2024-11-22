package backsolutions.controlador;

import backsolutions.modelo.*;
import backsolutions.modelo.dao.*;
import backsolutions.util.DatabaseConnection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import java.sql.Connection;

import backsolutions.modelo.dao.InscripcionDAO;
import backsolutions.modelo.dao.ExcursionDAOImpl;
import backsolutions.modelo.dao.DAOFactoryProvider;


public class ControladorInscripcion {
    private final InscripcionDAO inscripcionDAO; //declaramos el atributo inscripcionDAO

    //Constructor
    public ControladorInscripcion() {
        this.inscripcionDAO = DAOFactoryProvider.getDAOFactory().getInscripcionDAO(); // Obtener el DAO de Factory
    }


    //Metodo para añadir inscripciones de un socio a una excursion
    public void addInscripcion(String codigoExcursion, int numSocio, String tipoSeguro, double precioSeguro) throws ControladorExcepcion {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Verifica que la excursión existe
            Excursion excursion = new ExcursionDAOImpl().buscarExcursion(codigoExcursion);
            if (excursion == null) {
                throw new ControladorExcepcion("La excursión con código " + codigoExcursion + " no existe.");
            }

            // Verifica que el socio existe
            Socio socio = new SocioDAOImpl().buscarSocio(numSocio);
            if (socio == null) {
                throw new ControladorExcepcion("No se encontró un socio con el número proporcionado.");
            }

            // Genera un número único para la inscripción
            String numInscripcion = "INS" + System.currentTimeMillis();

            // Crea la inscripción con los detalles
            Inscripcion inscripcion = new Inscripcion(
                    numInscripcion,
                    socio,
                    excursion,
                    LocalDate.now(),
                    tipoSeguro,
                    precioSeguro
            );

            // Guarda la inscripción en la base de datos
            inscripcionDAO.guardarInscripcion(inscripcion);

            conn.commit();
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al guardar la inscripción: " + e.getMessage());
        }
    }

    //metodo para cancelar inscripciones con excepciones personalizadas
    public void cancelarInscripcion(int numSocio, String codigoExcursion) throws ControladorExcepcion {
        try {
            List<Inscripcion> inscripciones = inscripcionDAO.listarInscripcionesPorSocio(numSocio);
            Inscripcion inscripcionACancelar = null;

            // Buscar la inscripción que coincida con el socio y la excursión
            for (Inscripcion inscripcion : inscripciones) {
                if (inscripcion.getExcursion().getCodigo().equals(codigoExcursion)) {
                    inscripcionACancelar = inscripcion;
                    break;
                }
            }

            if (inscripcionACancelar == null) {
                throw new ControladorExcepcion("No se encontró una inscripción para el socio en la excursión proporcionada.");
            }

            // Eliminar la inscripción en la base de datos
            inscripcionDAO.eliminarInscripcion(inscripcionACancelar.getNumInscripcion());
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al cancelar la inscripción: " + e.getMessage());
        }
    }


    //metodo para mostrar todas las inscripciones, las recuperara de la BD usando inscripcionDAO
    public List<Inscripcion> mostrarInscripciones() throws ControladorExcepcion {
        try {
            return inscripcionDAO.listarInscripciones();
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al mostrar inscripciones: " + e.getMessage());
        }
    }

    //metodo para usar mostrarInscripciones() directamente ya que ahora se obtienen desde la BD
    public List<Inscripcion> getInscripciones() throws ControladorExcepcion {
        return mostrarInscripciones();
    }


    //metodo para calcular el importe
    public double calcularImporte(Inscripcion inscripcion) {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();
        double importe = precioBase;

        // Aplica descuentos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            importe += inscripcion.getSeguroPrecio();
        } else if (inscripcion.getSocio() instanceof Federado) {
            importe *= 0.9; // Descuento del 10%
        } else if (inscripcion.getSocio() instanceof Infantil) {
            importe *= 0.5; // Descuento del 50%
        }

        return importe;
    }

    //metodo para obtener las inscripciones de un socio específico directamente desde la BD
    public List<Inscripcion> mostrarInscripcionesPorSocio(int numSocio) throws ControladorExcepcion {
        try {
            return inscripcionDAO.listarInscripcionesPorSocio(numSocio);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al mostrar inscripciones por socio: " + e.getMessage());
        }
    }

    //metedo que obtiene las inscripciones del socio desde la BD y calcula el total mensual
    public double generarFacturaMensual(Socio socio) throws ControladorExcepcion {
        double cuotaMensual = socio.calculoCuotaMensual();
        double totalInscripciones = 0.0;

        // Obtener las inscripciones del socio
        List<Inscripcion> inscripciones = mostrarInscripcionesPorSocio(socio.getNumSocio());
        for (Inscripcion inscripcion : inscripciones) {
            totalInscripciones += calcularImporte(inscripcion);
        }

        return totalInscripciones + cuotaMensual;
    }


}
