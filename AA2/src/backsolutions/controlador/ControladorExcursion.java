package backsolutions.controlador;

import backsolutions.modelo.Excursion;
import java.time.LocalDate;
import java.util.List;

public class ControladorExcursion {
    private List<Excursion> excursiones;

    public ControladorExcursion(List<Excursion> excursiones) {
        this.excursiones = excursiones;
    }

    // Metodo para buscar una excursión por código
    public Excursion buscarExcursion(String codigoExcursion) {
        return excursiones.stream()
                .filter(exc -> exc.getCodigo().equals(codigoExcursion))
                .findFirst()
                .orElse(null);
    }

    public void addExcursion(Excursion excursion) throws ControladorExcepcion {
        if (excursiones.stream().anyMatch(e -> e.getCodigo().equals(excursion.getCodigo()))) {
            throw new ControladorExcepcion("La excursión con código " + excursion.getCodigo() + " ya existe.");
        }
        excursiones.add(excursion);
        System.out.println("Excursión añadida con éxito.");
    }

    public void mostrarExcursiones() {
        excursiones.forEach(System.out::println);
    }

    public void filtrarExcursiones(LocalDate inicio, LocalDate fin) throws ControladorExcepcion {
        List<Excursion> filtradas = excursiones.stream()
                .filter(exc -> exc.getFecha().isAfter(inicio) && exc.getFecha().isBefore(fin))
                .toList();

        if (filtradas.isEmpty()) {
            throw new ControladorExcepcion("No se encontraron excursiones en el rango de fechas proporcionado.");
        }

        filtradas.forEach(System.out::println);
    }
}
