package modeloTest;

import java.util.ArrayList;
import java.util.List;

public class Datos {
    // Listas para almacenar los datos
    private List<Socio> socios;
    private List<Excursion> excursiones;
    private List<Inscripcion> inscripciones;

    // Constructor
    public Datos() {
        this.socios = new ArrayList<>();
        this.excursiones = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    // Métodos para gestionar los datos de socios
    public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public List<Socio> obtenerSocios() {
        return socios;
    }

    public Socio buscarSocio(int numSocio) {
        for (Socio socio : socios) {
            if (socio.getNumSocio() == numSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra el socio
    }

    // Métodos para gestionar los datos de excursiones
    public void agregarExcursion(Excursion excursion) {
        excursiones.add(excursion);
    }

    public List<Excursion> obtenerExcursiones() {
        return excursiones;
    }

    public Excursion buscarExcursion(String codigo) {
        for (Excursion excursion : excursiones) {
            if (excursion.getCodigo().equals(codigo)) {
                return excursion;
            }
        }
        return null; // Retorna null si no se encuentra la excursión
    }

    // Métodos para gestionar los datos de inscripciones
    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    public List<Inscripcion> obtenerInscripciones() {
        return inscripciones;
    }

}
