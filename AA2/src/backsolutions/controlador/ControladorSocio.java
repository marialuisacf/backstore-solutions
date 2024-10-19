package backsolutions.controlador;

import backsolutions.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorSocio {
    private Lista<Socio> socios;

    public ControladorSocio() {
        this.socios = new Lista<>(); // Inicializamos la lista de socios
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
        System.out.println("Socio añadido con éxito.");
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
        System.out.println("Socio eliminado con éxito.");
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
        System.out.println("Tipo de seguro modificado con éxito.");
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


}
