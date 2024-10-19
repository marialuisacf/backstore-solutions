package backsolutions.controlador;

import backsolutions.modelo.Socio;
import java.util.List;

public class ControladorSocio {
    private List<Socio> socios;

    public ControladorSocio(List<Socio> socios) {
        this.socios = socios;
    }

    public void addSocio(Socio socio) throws ControladorExcepcion {
        if (socios.stream().anyMatch(s -> s.getNumSocio() == socio.getNumSocio())) {
            throw new ControladorExcepcion("El socio con número " + socio.getNumSocio() + " ya existe.");
        }
        socios.add(socio);
        System.out.println("Socio añadido con éxito.");
    }

    public void deleteSocio(int numSocio) throws ControladorExcepcion {
        Socio socio = socios.stream()
                .filter(s -> s.getNumSocio() == numSocio)
                .findFirst()
                .orElse(null);

        if (socio == null) {
            throw new ControladorExcepcion("No se encontró un socio con el número proporcionado.");
        }

        socios.remove(socio);
        System.out.println("Socio eliminado con éxito.");
    }

}
