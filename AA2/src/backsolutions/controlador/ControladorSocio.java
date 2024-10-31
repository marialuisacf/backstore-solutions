package backsolutions.controlador;

import backsolutions.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorSocio {
    private Lista<Socio> socios;
    private ControladorInscripcion controladorInscripcion;

    public ControladorSocio(ControladorInscripcion controladorInscripcion) {
        this.controladorInscripcion = controladorInscripcion; // Asigna la instancia de ControladorInscripcion
        this.socios = new Lista<>(); // Inicializa la lista de socios
    }

    // Metodo para buscar un socio por número
    public Socio buscarSocio(int numSocio) {
        for (int i = 0; i < socios.size(); i++) {
            Socio socio = socios.obtener(i);
            if (socio.getNumSocio() == numSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra el socio
    }

    public void addSocio(Socio socio) throws ControladorExcepcion {
        // Verificamos si el socio ya existe
        for (int i = 0; i < socios.size(); i++) {
            if (socios.obtener(i).getNumSocio() == socio.getNumSocio()) {
                throw new ControladorExcepcion("El socio con número " + socio.getNumSocio() + " ya existe.");
            }
        }
        socios.agregar(socio); // Añadimos el nuevo socio
    }

    public void deleteSocio(int numSocio) throws ControladorExcepcion {
        Socio socio = null;
        // Buscamos el socio por número
        for (int i = 0; i < socios.size(); i++) {
            if (socios.obtener(i).getNumSocio() == numSocio) {
                socio = socios.obtener(i);
                break; // Salimos del bucle una vez que encontramos el socio
            }
        }

        if (socio == null) {
            throw new ControladorExcepcion("No se encontró un socio con el número proporcionado.");
        }

        socios.eliminar(socio); // Eliminamos el socio
    }

    public void modificarSeguro(int numSocio, String nuevoTipoSeguro, double nuevoPrecioSeguro) throws ControladorExcepcion {
        Socio socio = null;

        // Buscamos el socio estándar por número
        for (int i = 0; i < socios.size(); i++) {
            if (socios.obtener(i) instanceof backsolutions.modelo.Estandar && socios.obtener(i).getNumSocio() == numSocio) {
                socio = socios.obtener(i);
                break; // Salimos del bucle una vez que encontramos el socio
            }
        }

        if (socio == null) {
            throw new ControladorExcepcion("No se encontró un socio estándar con el número proporcionado.");
        }

        // Llamamos al metodo modificarSeguro de la clase Estandar
        ((backsolutions.modelo.Estandar) socio).modificarSeguro(nuevoTipoSeguro, nuevoPrecioSeguro);
    }

    // Método para mostrar los socios filtrados por tipo
    public List<Socio> mostrarSociosFiltrados(String filtro) throws ControladorExcepcion {
        List<Socio> resultado = new ArrayList<>();

        for (int i = 0; i < socios.size(); i++) {
            Socio socio = socios.obtener(i);
            switch (filtro.toLowerCase()) {
                case "todos":
                    resultado.add(socio);
                    break;
                case "estandar":
                    if (socio instanceof Estandar) {
                        resultado.add(socio);
                    }
                    break;
                case "federado":
                    if (socio instanceof Federado) {
                        resultado.add(socio);
                    }
                    break;
                case "infantil":
                    if (socio instanceof Infantil) {
                        resultado.add(socio);
                    }
                    break;
                default:
                    throw new ControladorExcepcion("Filtro no válido: " + filtro);
            }
        }

        return resultado;
    }

    // Metodo para mostrar todos los socios
    public List<Socio> mostrarSocios(String filtro) throws ControladorExcepcion {
        List<Socio> resultado = new ArrayList<>();
        for (int i = 0; i < socios.size(); i++) {
            Socio socio = socios.obtener(i);
            switch (filtro.toLowerCase()) {
                case "todos":
                    resultado.add(socio);
                    break;
                case "estandar":
                    if (socio instanceof Estandar) {
                        resultado.add(socio);
                    }
                    break;
                case "federado":
                    if (socio instanceof Federado) {
                        resultado.add(socio);
                    }
                    break;
                case "infantil":
                    if (socio instanceof Infantil) {
                        resultado.add(socio);
                    }
                    break;
                default:
                    throw new ControladorExcepcion("Filtro no válido: " + filtro);
            }
        }
        return resultado;
    }

    public String mostrarFacturaMensual(Socio socio) {
        List<Inscripcion> inscripciones = obtenerInscripcionesDelSocio(socio);

        if (inscripciones.isEmpty()) {
            return "No hay inscripciones para el socio con número: " + socio.getNumSocio();
        } else {
            StringBuilder mensaje = new StringBuilder("Facturas Mensuales para el Socio: " + socio.getNumSocio() + "\n");
            for (Inscripcion inscripcion : inscripciones) {
                double importe = calcularImporte(inscripcion); // Metodo que ya tenemos
                mensaje.append(String.format("Excursión: %s, Importe: %.2f%n", inscripcion.getExcursion().getDescripcion(), importe));
            }
            return mensaje.toString();
        }
    }

    // Metodo para obtener las inscripciones de un socio
    private List<Inscripcion> obtenerInscripcionesDelSocio(Socio socio) {
        List<Inscripcion> inscripcionesDelSocio = new ArrayList<>();

        // Obtener la lista de inscripciones desde el controlador de inscripciones
        List<Inscripcion> todasLasInscripciones = controladorInscripcion.getInscripciones();

        // Iterar sobre las inscripciones y agregar las que corresponden al socio
        for (Inscripcion inscripcion : todasLasInscripciones) {
            if (inscripcion.getSocio().getNumSocio() == socio.getNumSocio()) {
                inscripcionesDelSocio.add(inscripcion);
            }
        }

        return inscripcionesDelSocio;
    }

    private double calcularImporte(Inscripcion inscripcion) {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();

        // Aplica descuentos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            return precioBase + inscripcion.getSeguro().getPrecio(); // Asegúrate de tener un método para obtener el precio del seguro
        } else if (inscripcion.getSocio() instanceof Federado) {
            return precioBase * 0.9; // Descuento del 10% para federados
        } else if (inscripcion.getSocio() instanceof Infantil) {
            return precioBase * 0.5; // Descuento del 50% para infantiles
        }

        return precioBase; // Si no se aplica ningún descuento
    }

}
