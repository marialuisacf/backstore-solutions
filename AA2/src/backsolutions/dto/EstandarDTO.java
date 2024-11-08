package backsolutions.dto;

public class EstandarDTO extends SocioDTO {
    private String nif;
    private SeguroDTO seguro;

    public EstandarDTO(int numSocio, String nombre, String nif, SeguroDTO seguro) {
        super(numSocio, nombre, "Estandar");
        this.nif = nif;
        this.seguro = seguro;
    }

    public String getNif() {
        return nif;
    }

    public SeguroDTO getSeguro() {
        return seguro;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Estandar: " +
                "nif='" + nif + '\'' +
                ", seguro=" + seguro +
                '.';
    }
}
