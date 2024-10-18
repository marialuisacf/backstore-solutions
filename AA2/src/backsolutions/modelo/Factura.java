package backsolutions.modelo;

/**
 * Se crea la clase backsolutions.modelo.Factura
 */

public class Factura {

    /**
     * Atributos de la clase backsolutions.modelo.Factura
     */
    private Socio socio;
    private double totalExcursiones;
    private double totalCuota;
    private double totalPagar;

    //Constructor de la clase backsolutions.modelo.Factura con los parámetros necesarios para inicializar una factura

    /**
     * Atributos de la clase backsolutions.modelo.Factura añadidos al constructor
     * @param socio parámetro identificativo del socio asociado a la factura
     * @param totalExcursiones parámetro del total de las excursiones
     * @param totalCuota parámetro del total de la cuota
     * @param totalPagar parámetro del total a pagar
     */
    public Factura(Socio socio, double totalExcursiones, double totalCuota, double totalPagar) {
        this.socio = socio;
        this.totalExcursiones = totalExcursiones;
        this.totalCuota = totalCuota;
        this.totalPagar = totalPagar;
    }

    //Getter y Setter

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
     * Getter de totalExcursiones
     * @return devuelve el total de las excursiones
     */
    public double getTotalExcursiones() {
        return totalExcursiones;
    }
    /**
     * Setter de totalExcursiones
     * @param totalExcursiones parámetro del total de las excursiones
     */
    public void setTotalExcursiones(double totalExcursiones) {
        this.totalExcursiones = totalExcursiones;
    }
    /**
     * Getter de totalCuota
     * @return devuelve el total de la cuota mensual en la factura
     */
    public double getTotalCuota() {
        return totalCuota;
    }
    /**
     * Setter de totalCuota
     * @param totalCuota parámetro del total de la cuota en la factura
     */
    public void setTotalCuota(double totalCuota) {
        this.totalCuota = totalCuota;
    }
    /**
     * Getter de totalPagar
     * @return devuelve el total a pagar en la factura
     */
    public double getTotalPagar() {
        return totalPagar;
    }
    /**
     * Setter de totalPagar
     * @param totalPagar parámetro del total a pagar
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    //Métodos de la clase backsolutions.modelo.Factura

    /**
     * Representación de la información de la clase backsolutions.modelo.Factura con toString
     * @return devuelve el método toString de la clase backsolutions.modelo.Factura
     */
    @Override
    public String toString() {
        return "backsolutions.modelo.Factura{" +
                "socio=" + socio +
                ", totalExcursiones=" + totalExcursiones +
                ", totalCuota=" + totalCuota +
                ", totalPagar=" + totalPagar +
                '}';
    }
}
