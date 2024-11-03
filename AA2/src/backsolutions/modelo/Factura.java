package backsolutions.modelo;

import java.util.List;
import java.time.LocalDate;

/**
 * Se crea la clase backsolutions.modelo.Factura
 */

public class Factura {
    /**
     * Atributos de la clase backsolutions.modelo.Factura
     */
    private int idFactura;
    private Socio socio;
    private List<Inscripcion> inscripciones;

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
    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", socio=" + socio +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
