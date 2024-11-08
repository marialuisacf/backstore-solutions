package backsolutions.dto;

public class SeguroDTO {
    private String tipo;
    private double precio;

    public SeguroDTO(String tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Seguro: " +
                "tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '.';
    }
}
