package backsolutions.controlador;

import backsolutions.dto.ExcursionDTO;
import backsolutions.modelo.Excursion;
import backsolutions.modelo.dao.ExcursionDAO;
import backsolutions.modelo.dao.DAOFactoryProvider;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorExcursion {
    private final ExcursionDAO excursionDAO;

    //constructor
    public ControladorExcursion() {
        this.excursionDAO = DAOFactoryProvider.getDAOFactory().getExcursionDAO(); // Obtener el DAO de DAOFactoryProvider
    }

    // Metodo para buscar una excursión por código
    public Excursion buscarExcursion(String codigo) throws ControladorExcepcion {
        try {
            return excursionDAO.buscarExcursion(codigo);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al buscar la excursión: " + e.getMessage());
        }
    }

    //metodo para añadir una excursión usando ExcursionDTO
    public void addExcursion(ExcursionDTO excursionDTO) throws ControladorExcepcion {
        try {
            // Convierte el ExcursionDTO en un objeto Excursion
            Excursion excursion = new Excursion(
                    excursionDTO.getCodigo(),
                    excursionDTO.getDescripcion(),
                    excursionDTO.getFecha(),
                    excursionDTO.getNumDias(),
                    excursionDTO.getPrecioInscripcion()
            );
            excursionDAO.guardarExcursion(excursion);
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al añadir la excursión: " + e.getMessage());
        }
    }


    //metodo para buscar excursiones por un filtro de fechas
    public List<ExcursionDTO> filtrarExcursiones(LocalDate inicio, LocalDate fin) throws ControladorExcepcion {
        try {
            List<Excursion> excursiones = excursionDAO.filtrarExcursiones(inicio, fin);
            return excursiones.stream()
                    .map(exc -> new ExcursionDTO(
                            exc.getCodigo(),
                            exc.getDescripcion(),
                            exc.getFecha(),
                            exc.getNumDias(),
                            exc.getPrecioInscripcion()))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new ControladorExcepcion("Error al filtrar las excursiones: " + e.getMessage());
        }
    }

}
