package backsolutions.dto;

import java.time.LocalDate;

public class ExcursionDTO {
    private String codigo;
    private String descripcion;
    private LocalDate fecha;
    private int numDias;
    private double precioInscripcion;

    public ExcursionDTO(String codigo, String descripcion, LocalDate fecha, int numDias, double precioInscripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numDias = numDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public int getNumDias() { return numDias; }
    public double getPrecioInscripcion() { return precioInscripcion; }

    @Override
    public String toString() {
        return "Excursion :" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", numDias=" + numDias +
                ", precioInscripcion=" + precioInscripcion +
                '.';
    }
}
