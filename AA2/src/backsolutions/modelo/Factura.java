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
     */
    public Factura(Socio socio) {
        this.socio = socio;
        this.totalExcursiones = 0.0; //Inicializa en 0, ya que cuando se dan de alta empiezan con 0 excursiones.
        this.totalCuota = socio.calculoCuotaMensual(); //Calcula directamente a partir del socio, es más útil para mantener la consistencia del estado inicial.
        this.totalPagar = calcularTotalFactura(); //Se calcula automáticamente a partir de los valores iniciales.
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

    public double calcularTotalFactura() {
        return totalExcursiones + totalCuota + totalPagar;
    }

    public void agregarTotalExcursiones(double total){
        this.totalExcursiones += total;
        this.totalCuota = calcularTotalFactura(); //Actualiza total a pagar
    }

    public String generarFactura() {
        return "Factura para: " + socio.detallesSocio() +
                "\nTotal Cuota: " + totalCuota +
                "\nTotal Excursiones: " + totalExcursiones +
                "\nTotal a Pagar: " + totalPagar;
    }

    /**
     * Representación de la información de la clase backsolutions.modelo.Factura con toString
     * @return devuelve el metodo toString de la clase backsolutions.modelo.Factura
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
