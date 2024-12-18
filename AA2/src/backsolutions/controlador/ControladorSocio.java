package backsolutions.controlador;

import backsolutions.modelo.*;
import backsolutions.modelo.dao.SocioDAO;
import backsolutions.modelo.dao.DAOFactoryProvider;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import backsolutions.util.DatabaseConnection;

public class ControladorSocio {
    private final SocioDAO socioDAO;
    private final ControladorInscripcion controladorInscripcion;

    public ControladorSocio(ControladorInscripcion controladorInscripcion) {
        this.socioDAO = DAOFactoryProvider.getDAOFactory().getSocioDAO(); // Obtener el DAO de DAOFactoryProvider
        this.controladorInscripcion = controladorInscripcion;
    }

    //Metodo para buscar un socio por su número identificador
    public Socio buscarSocio(int numSocio) throws ControladorExcepcion {
        try {
            return socioDAO.buscarSocio(numSocio);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al buscar el socio: " + e.getMessage());
        }
    }

    //Metodo para añadir un nuevo socio a la BD
    public void addSocio(Socio socio) throws ControladorExcepcion {
        if (socio instanceof Estandar) {
            // Aseguramos que el seguro se ha creado correctamente en el socio de tipo Estándar
            Estandar estandar = (Estandar) socio;
            if (estandar.getSeguro() == null) {
                throw new ControladorExcepcion("Debe especificar un seguro para el socio estándar.");
            }
        }

        try {
            socioDAO.guardarSocio(socio); // Aquí es donde se envía el socio completo con seguro
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al guardar el socio: " + e.getMessage());
        }
    }

    //Metodo para eliminar un socio por su número identificador numSocio
    public void deleteSocio(int numSocio) throws ControladorExcepcion {
        try {
            socioDAO.eliminarSocio(numSocio);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al eliminar el socio: " + e.getMessage());
        }
    }

    //Metodo para buscar y actualizar el seguro de un socio estándar en la BD
    public void modificarSeguro(int numSocio, String nuevoTipoSeguro, double nuevoPrecioSeguro) throws ControladorExcepcion {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);  // Iniciar transacción

            // Recuperar y verificar que el socio sea de tipo estándar
            Socio socio = socioDAO.buscarSocio(numSocio);
            if (!(socio instanceof Estandar)) {
                throw new ControladorExcepcion("El socio no es de tipo estándar.");
            }

            // Actualizar el seguro del socio
            socioDAO.actualizarSeguro(numSocio, nuevoTipoSeguro, nuevoPrecioSeguro, conn);

            conn.commit();  // Confirmar transacción
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Deshacer cambios en caso de error
                } catch (SQLException ex) {
                    throw new ControladorExcepcion("Error al hacer rollback: " + ex.getMessage());
                }
            }
            throw new ControladorExcepcion("Error al modificar el seguro del socio: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Cerrar la conexión
                } catch (SQLException ex) {
                    throw new ControladorExcepcion("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }

    // Metodo para mostrar socios con un filtro (todos/estandar/federado/infantil)
    public List<Socio> mostrarSociosFiltrados(String filtro) throws ControladorExcepcion {
        try {
            return socioDAO.listarSocios(filtro);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al listar socios: " + e.getMessage());
        }
    }

    public String mostrarFacturaMensual(Socio socio) throws ControladorExcepcion {
        List<Inscripcion> inscripciones = obtenerInscripcionesDelSocio(socio);

        if (inscripciones.isEmpty()) {
            return "No hay inscripciones para el socio con número: " + socio.getNumSocio();
        } else {
            StringBuilder mensaje = new StringBuilder("Facturas Mensuales para el Socio: " + socio.getNumSocio() + "\n");
            for (Inscripcion inscripcion : inscripciones) {
                double importe = calcularImporte(inscripcion); //metodo existente
                mensaje.append(String.format("Excursión: %s, Importe: %.2f%n", inscripcion.getExcursion().getDescripcion(), importe));
            }
            return mensaje.toString();
        }
    }

    // Metodo para obtener las inscripciones de un socio desde la BD a través del controladorInscripcion
    private List<Inscripcion> obtenerInscripcionesDelSocio(Socio socio) {
        return controladorInscripcion.mostrarInscripcionesPorSocio(socio.getNumSocio());
    }

    private double calcularImporte(Inscripcion inscripcion) {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();

        // Aplica descuentos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            return precioBase + inscripcion.getSeguroPrecio(); // Asegurate de tener un metodo para obtener el precio del seguro
        } else if (inscripcion.getSocio() instanceof Federado) {
            return precioBase * 0.9; // Descuento del 10% para federados
        } else if (inscripcion.getSocio() instanceof Infantil) {
            return precioBase * 0.5; // Descuento del 50% para infantiles
        }

        return precioBase; // Si no se aplica ningún descuento
    }

}
