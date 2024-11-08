package backsolutions.dto;

public class FederacionDTO {
    private String codigo;
    private String nombre;

    public FederacionDTO(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Federacion: " +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '.';
    }
}
