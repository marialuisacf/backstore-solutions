package backsolutions.controlador.gui;

import backsolutions.modelo.dao.SocioDAO;
import backsolutions.modelo.dao.SocioDAOImpl;
import backsolutions.modelo.*;
import backsolutions.vista.gui.VistaSociosGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.Connection;
import backsolutions.util.DatabaseConnection;

public class ControladorSociosGUI {
    private final VistaSociosGUI vista; //Enlace con la interfaz grafica
    private final SocioDAO socioDAO; //para acceder a la BD
    private final ObservableList<Socio> listaSocios; //ObservableList para mostrar en la tabla

    public ControladorSociosGUI(VistaSociosGUI vista) {
        this.vista = vista;
        this.socioDAO = new SocioDAOImpl();
        this.listaSocios = FXCollections.observableArrayList();

        inicializarEventos();
        cargarSocios();
    }

    private void inicializarEventos() {
        vista.getAñadirSocioButton().setOnAction(e -> añadirSocio());
        vista.getMostrarTodosButton().setOnAction(e -> mostrarSocios());
        vista.getModificarSeguroButton().setOnAction(e -> modificarSeguro());
        vista.getEliminarSocioButton().setOnAction(e -> eliminarSocio());
        vista.getMostrarFacturaButton().setOnAction(e -> mostrarFactura());
    }

    // 1. Añadir Socio: recoge datos de la interfaz, crea el objeto Estandar/Federado/Infantil y lo guarda en la BD
    private void añadirSocio() {
        try {
            int numSocio = Integer.parseInt(vista.getNumSocioField().getText());
            String nombre = vista.getNombreField().getText();
            String tipo = vista.getTipoSocioBox().getValue();
            String nif = vista.getNifField().getText();
            String seguroTipo = vista.getSeguroBox().getValue();
            double seguroPrecio = Double.parseDouble(vista.getPrecioSeguroField().getText());
            String numSocioTutor = vista.getNumSocioTutorField().getText();

            Socio nuevoSocio = null;

            switch (tipo.toLowerCase()) {
                case "estandar":
                    Seguro seguro = new Seguro(seguroTipo, seguroPrecio);
                    nuevoSocio = new Estandar(numSocio, nombre, nif, seguro);
                    break;
                case "federado":
                    // Ajuste del constructor de Federacion
                    nuevoSocio = new Federado(numSocio, nombre, nif, new Federacion("FED001", "Federacion nacional"));
                    break;
                case "infantil":
                    nuevoSocio = new Infantil(numSocio, nombre, numSocioTutor);
                    break;
            }

            socioDAO.guardarSocio(nuevoSocio);
            listaSocios.add(nuevoSocio);
            limpiarCamposAñadir();
        } catch (Exception e) {
            mostrarError("Error al añadir socio: " + e.getMessage());
        }
    }


    private void limpiarCamposAñadir() {
        vista.getNumSocioField().clear();
        vista.getNombreField().clear();
        vista.getTipoSocioBox().setValue(null);
        vista.getNifField().clear();
        vista.getSeguroBox().setValue(null);
        vista.getPrecioSeguroField().clear();
        vista.getNumSocioTutorField().clear();
    }

    // 2. Mostrar Socios: carga todos los socios y los muestra en la tabla
    private void cargarSocios() {
        try {
            listaSocios.setAll(socioDAO.listarSocios("Todos"));
            vista.getTablaSocios().setItems(listaSocios);
        } catch (Exception e) {
            mostrarError("Error al cargar socios: " + e.getMessage());
        }
    }

    private void mostrarSocios() {
        try {
            String filtro = vista.getFiltroTipoSocioBox().getValue();
            listaSocios.setAll(socioDAO.listarSocios(filtro));
        } catch (Exception e) {
            mostrarError("Error al filtrar socios: " + e.getMessage());
        }
    }

    // 3. Modificar Tipo de Seguro: permite modificar el seguro de un socio Estandar
    private void modificarSeguro() {
        try {
            int numSocio = Integer.parseInt(vista.getModificarNumSocioField().getText());
            String nuevoTipoSeguro = vista.getNuevoTipoSeguroBox().getValue();
            double nuevoPrecioSeguro = Double.parseDouble(vista.getNuevoPrecioSeguroField().getText());

            // Verificar que todos los campos estén completos
            if (nuevoTipoSeguro == null || nuevoTipoSeguro.isEmpty()) {
                mostrarError("Debe seleccionar un tipo de seguro.");
                return;
            }

            // Verificar si el socio existe y es de tipo Estandar
            Socio socio = socioDAO.buscarSocio(numSocio); // Recuperar socio por numSocio
            if (socio == null) {
                mostrarError("No se encontró un socio con el número indicado.");
                return;
            }

            if (!(socio instanceof Estandar)) { // Solo permite socios de tipo Estandar
                mostrarError("Solo se puede modificar el seguro de los socios de tipo Estandar.");
                return;
            }

            // Llamada al método del DAO para modificar el seguro
            Connection conn = DatabaseConnection.getConnection();
            socioDAO.actualizarSeguro(numSocio, nuevoTipoSeguro, nuevoPrecioSeguro, conn);

            // Mostrar mensaje de éxito y actualizar la tabla
            mostrarExito("Tipo de seguro modificado correctamente.");
            cargarSocios();
        } catch (NumberFormatException e) {
            mostrarError("Formato inválido. Verifique los datos ingresados.");
        } catch (Exception e) {
            mostrarError("Error al modificar el seguro: " + e.getMessage());
        }
    }




    // 4. Eliminar Socio de la BD
    private void eliminarSocio() {
        try {
            int numSocio = Integer.parseInt(vista.getEliminarNumSocioField().getText());

            // Verificar si el socio existe antes de eliminarlo
            Socio socio = socioDAO.buscarSocio(numSocio); // Recuperar el socio
            if (socio == null) {
                mostrarError("Número de socio incorrecto o inexistente.");
                return; // Termina el método si el socio no existe
            }

            // Eliminar el socio
            socioDAO.eliminarSocio(numSocio);
            mostrarExito("Socio eliminado correctamente.");
            cargarSocios(); // Actualizar la tabla de socios
        } catch (NumberFormatException e) {
            mostrarError("Formato inválido. Por favor, ingrese un número válido.");
        } catch (Exception e) {
            mostrarError("Error al eliminar el socio: " + e.getMessage());
        }
    }


    // 5. Mostrar Factura mensual de un socio en un area de texto
    private void mostrarFactura() {
        try {
            int numSocio = Integer.parseInt(vista.getFacturaNumSocioField().getText());
            Socio socio = socioDAO.buscarSocio(numSocio);

            if (socio != null) {
                double totalFactura = socio.calculoCuotaMensual();
                String factura = "Factura del Socio " + numSocio + ":\n" +
                        "Nombre: " + socio.getNombre() + "\n" +
                        "Cuota Mensual: " + totalFactura + " €\n";
                vista.getFacturaTextArea().setText(factura);
            } else {
                vista.getFacturaTextArea().setText("No se encontró el socio con número: " + numSocio);
            }
        } catch (Exception e) {
            mostrarError("Error al mostrar la factura: " + e.getMessage());
        }
    }

    //utilizamos cuadros de dialogo Alert para informar sobre errores.
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
