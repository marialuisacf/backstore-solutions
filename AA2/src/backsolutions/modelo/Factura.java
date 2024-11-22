package backsolutions.modelo;

import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "facturas") //nombre de la tabla en la BD

/**
 * Se crea la clase backsolutions.modelo.Factura
 */

public class Factura {
    /**
     * Atributos de la clase backsolutions.modelo.Factura
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo autonumerico
    @Column(name = "idFactura") //nombre del campo en la BD
    private int idFactura;

    @ManyToOne(fetch = FetchType.LAZY) //relacion muchos a uno con Socio/ fetch controla como se cargan las relaciones entre entidaddes /LAZY para que se cargue la relacion solo cuando se necesite
    @JoinColumn(name = "numSocio", nullable = false)
    private Socio socio;

    @Column(name = "fechaFactura", nullable = false)
    private LocalDate fechaFactura;

    @Column(name = "totalExcursiones", nullable = false)
    private double totalExcursiones;

    @Column(name = "totalCuota", nullable = false)
    private double totalCuota;

    @Column(name = "totalPagar", nullable = false)
    private double totalPagar;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // Relación uno a muchos con Inscripcion
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // Constructor vacío obligatorio para JPA
    public Factura() {}

    // Constructor con parámetros para inicializar Factura
    public Factura(Socio socio, LocalDate fechaFactura, double totalExcursiones, double totalCuota, double totalPagar) {
        this.socio = socio;
        this.fechaFactura = fechaFactura;
        this.totalExcursiones = totalExcursiones;
        this.totalCuota = totalCuota;
        this.totalPagar = totalPagar;
    }

    //Constructor de la clase backsolutions.modelo.Factura con los parámetros necesarios para inicializar una factura
    /**
     * Atributos de la clase Factura añadidos al constructor
     * @param idFactura parámetro identificativo de la factura
     * @param socio parámetro identificativo del socio asociado a la Factura
     * @param inscripciones parámetro identificativo del listado de inscripciones
     */
    public Factura(int idFactura, Socio socio, List<Inscripcion> inscripciones) {
        this.idFactura = idFactura;
        this.socio = socio;
        this.inscripciones = inscripciones;
    }
    // Constructor alternativo sin idFactura para cuando aún no se conoce el ID
    public Factura(Socio socio, List<Inscripcion> inscripciones) {
        this.socio = socio;
        this.inscripciones = inscripciones;
    }

    // Getters y Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    //Getters y Setters

    /**
     * Getter de socio
     * @return devuelve el socio de la factura
     */
    public Socio getSocio() {
        return socio;
    }
    /**
     * Setter del socio
     * @param socio parámetro identificativo del socio de la factura
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getTotalExcursiones() {
        return totalExcursiones;
    }

    public void setTotalExcursiones(double totalExcursiones) {
        this.totalExcursiones = totalExcursiones;
    }

    public double getTotalCuota() {
        return totalCuota;
    }

    public void setTotalCuota(double totalCuota) {
        this.totalCuota = totalCuota;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    /**
     * Getter inscripciones
     * @return
     */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    /**
     * Setter inscripciones
     * @param inscripciones
     */
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }


    // Métodos auxiliares
    public void addInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        inscripcion.setFactura(this);
    }

    public void removeInscripcion(Inscripcion inscripcion) {
        inscripciones.remove(inscripcion);
        inscripcion.setFactura(null);
    }

    //Métodos de la clase Factura

    // Metodo para calcular el total de la factura mensual

    public double calculoTotalFacturaMensual() {
        // Sumar la cuota mensual del socio
        double total = socio.calculoCuotaMensual();

        // Sumar el costo total de cada inscripción que corresponde al mes en curso
        for (Inscripcion inscripcion : inscripciones) {
            if (esDelMesActual(inscripcion.getFechaInscripcion())) {
                total += inscripcion.calculoPrecioTotal();
            }
        }

        return total;
    }

    // Metodo para generar la factura mensual

    public String generarFacturaMensual() {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura Mensual para el socio: ").append(socio.getNombre()).append("\n");
        factura.append("Número de socio: ").append(socio.getNumSocio()).append("\n");
        factura.append("Mes: ").append(LocalDate.now().getMonth()).append(" ").append(LocalDate.now().getYear()).append("\n");
        factura.append("Detalle de inscripciones del mes:\n");

        // Añadir el detalle de cada inscripción del mes
        for (Inscripcion inscripcion : inscripciones) {
            if (esDelMesActual(inscripcion.getFechaInscripcion())) {
                factura.append("Inscripción número: ").append(inscripcion.getNumInscripcion()).append("\n");
                factura.append("Excursión: ").append(inscripcion.getExcursion().getDescripcion()).append("\n");
                factura.append("Fecha de inscripción: ").append(inscripcion.getFechaInscripcion()).append("\n");
                factura.append("Precio total: ").append(inscripcion.calculoPrecioTotal()).append("€\n\n");
            }
        }

        // Añadir el total de la factura
        factura.append("Cuota mensual: ").append(socio.calculoCuotaMensual()).append("€\n");
        factura.append("Total de excursiones del mes: ").append(calculoTotalExcursionesMensual()).append("€\n");
        factura.append("Total factura mensual: ").append(calculoTotalFacturaMensual()).append("€\n");

        return factura.toString();
    }

    // Metodo auxiliar para verificar si una fecha es del mes actual
    private boolean esDelMesActual(LocalDate fecha) {
        LocalDate ahora = LocalDate.now();
        return fecha.getMonth() == ahora.getMonth() && fecha.getYear() == ahora.getYear();
    }

    // Metodo auxiliar para calcular el total de excursiones del mes
    public double calculoTotalExcursionesMensual() {
        double totalExcursiones = 0.0;
        for (Inscripcion inscripcion : inscripciones) {
            if (esDelMesActual(inscripcion.getFechaInscripcion())) {
                totalExcursiones += inscripcion.calculoPrecioTotal();
            }
        }
        return totalExcursiones;
    }

    /**
     * Representación de la información de la clase Factura con toString
     * @return devuelve el metodo toString de la clase Factura
     */
    /*@Override
    public String toString() {
        return "Factura" +
                "ID Factura=" + idFactura +
                ", Socio=" + socio +
                ", Inscripciones=" + inscripciones +
                '.';
    }*/
    @Override
    public String toString() {
        return "Factura:" +
                "ID Factura=" + idFactura +
                ", Socio=" + (socio != null ? socio.getNombre() : "Sin socio") +
                ", Fecha Factura=" + fechaFactura +
                ", Total Excursiones=" + totalExcursiones +
                ", Total Cuota=" + totalCuota +
                ", Total a Pagar=" + totalPagar +
                ", Inscripciones=" + inscripciones +
                '}';
    }

}
