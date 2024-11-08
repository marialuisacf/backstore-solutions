package backsolutions.dto;

public class FederadoDTO extends SocioDTO{
    private FederacionDTO federacion;

    public FederadoDTO(int numSocio, String nombre, String tipo, FederacionDTO federacion) {
        super(numSocio, nombre, tipo);
        this.federacion = federacion;
    }

    public FederacionDTO getFederacion() {
        return federacion;
    }

    @Override
    public String toString() {
        return super.toString() + ", federacion=" + federacion;
    }
}
