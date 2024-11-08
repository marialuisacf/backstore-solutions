package backsolutions.dto;

public class InfantilDTO extends SocioDTO {
    private String numSocioTutor;

    public InfantilDTO(int numSocio, String nombre, String tipo, String numSocioTutor) {
        super(numSocio, nombre, tipo);
        this.numSocioTutor = numSocioTutor;
    }

    public String getNumSocioTutor() {
        return numSocioTutor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numero de socio del tutor=" + numSocioTutor;
    }
}
