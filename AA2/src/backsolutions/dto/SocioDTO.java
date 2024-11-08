package backsolutions.dto;

public class SocioDTO {
    private int numSocio;
    private String nombre;
    private String tipo;

    public SocioDTO(int numSocio, String nombre, String tipo) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getNumSocio() {
        return numSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Socio:" +
                "Numero de socio=" + numSocio +
                ", Nombre='" + nombre + '\'' +
                ", Tipo='" + tipo + '\'' +
                '.';
    }
}
