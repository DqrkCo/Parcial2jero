package co.edu.poli.parcial2_jero.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import co.edu.poli.parcial2_jero.modelo.Credito;
import co.edu.poli.parcial2_jero.modelo.Debito;
import co.edu.poli.parcial2_jero.modelo.EstadoTarjeta;
import co.edu.poli.parcial2_jero.modelo.Tarjeta;
import co.edu.poli.parcial2_jero.modelo.Titular;
import co.edu.poli.parcial2_jero.servicios.DAOTarjeta;
import co.edu.poli.parcial2_jero.servicios.DAOTitular;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Platform;

/**
 * Controlador de la interfaz gráfica en JavaFX para la gestión de tarjetas bancarias.
 * Permite consultar tarjetas existentes y registrar nuevas tarjetas de crédito o débito.
 */
public class ControlFormCard {

	@FXML
	private Button bttConsulta;
	@FXML
	private TextField txtTarjeta1;
	@FXML
	private TextArea txtAreaResultado;
	@FXML
	private Button bttCreacion;
	@FXML
	private TextField txtTarjeta2;
	@FXML
	private DatePicker datepk1;
	@FXML
	private ComboBox<Titular> cmbTitular;
	@FXML
	private ComboBox<EstadoTarjeta> cmbEstado;
	@FXML
	private Label lblEstado;
	@FXML
	private RadioButton radio1; // Débito
	@FXML
	private RadioButton radio2; // Crédito
	@FXML
	private ToggleGroup tipo;

	/** Objeto de acceso a datos para la entidad Tarjeta. */
	private DAOTarjeta daoTarjeta;
	
	/** Objeto de acceso a datos para la entidad Titular. */
	private DAOTitular daoTitular;

	/**
	 * Inicializa los componentes del formulario, carga los combos de titulares y estados,
	 * y asigna los escuchadores de validación a los campos numéricos.
	 */
	@FXML
	private void initialize() {
		daoTarjeta = new DAOTarjeta();
		daoTitular = new DAOTitular();

		datepk1.setValue(LocalDate.now());

		List<Titular> lista = null;
		try {
			lista = daoTitular.readall();
		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}
		cmbTitular.getItems().setAll(lista);

		cmbEstado.getItems().setAll(EstadoTarjeta.values());

		txtTarjeta1.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtTarjeta1);
		});

		txtTarjeta2.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtTarjeta2);
		});

	}

	/**
	 * Maneja el evento de consulta de tarjeta por su número identificador.
	 * 
	 * @param event Evento de acción del botón de consulta.
	 */
	@FXML
	private void pressConsulta(ActionEvent event) {
		txtAreaResultado.setText("");
		lblEstado.setText("Estado: -");
		if (!txtTarjeta1.getText().isBlank() || !txtTarjeta1.getText().isEmpty()) {
			try {
				Tarjeta t = daoTarjeta.readone(txtTarjeta1.getText());

				if (t != null) {
					txtAreaResultado.setText(t.toString());
					lblEstado.setText("Estado: " + t.getEstado());
				} else
					mostrarAlerta("No existe el numero de tarjeta");
			} catch (Exception e) {
				mostrarAlerta(e.getMessage());
			}
		} else
			mostrarAlerta("Ingrese número de tarjeta");
	}

	/**
	 * Maneja el evento de creación e inserción de una nueva tarjeta en la base de datos.
	 * 
	 * @param event Evento de acción del botón de creación.
	 */
	@FXML
	private void pressCreacion(ActionEvent event) {

		String numero = txtTarjeta2.getText().trim();
		if (numero.isEmpty()) {
			mostrarAlerta("⚠ Ingrese el número de tarjeta.");
			return;
		}

		if (datepk1.getValue() == null) {
			mostrarAlerta("⚠ Seleccione la fecha de expedición.");
			return;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		String fechaExp = datepk1.getValue().format(formatter);

		Titular titular = cmbTitular.getValue();
		if (titular == null) {
			mostrarAlerta("⚠ Seleccione un titular.");
			return;
		}

		EstadoTarjeta estado = cmbEstado.getValue();
		if (estado == null) {
			mostrarAlerta("⚠ Seleccione el estado de la tarjeta.");
			return;
		}

		Tarjeta nueva;

		if (radio1.isSelected()) {
			nueva = new Debito(numero, fechaExp, estado, titular, 0.0);
		} else {
			nueva = new Credito(numero, fechaExp, estado, titular, 1000000);
		}

		try {
			String resultado = daoTarjeta.create(nueva);
			mostrarAlerta(resultado);
			if (resultado.startsWith("✔")) {
				limpiarFormCrear();
			}
		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}

	}

	/**
	 * Despliega un cuadro de diálogo informativo.
	 * 
	 * @param mensaje Texto del mensaje a mostrar.
	 */
	private void mostrarAlerta(String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/**
	 * Limpia los campos del formulario de creación.
	 */
	private void limpiarFormCrear() {
		txtTarjeta2.clear();
		datepk1.setValue(null);
		cmbTitular.setValue(null);
		cmbEstado.setValue(null);
		radio1.setSelected(true);
	}

	/**
	 * Valida que el texto de un campo sea estrictamente numérico al perder el foco.
	 * 
	 * @param txt Campo de texto a validar.
	 */
	private void validarSoloNumeros(TextField txt) {
		String texto = txt.getText().trim();
		if (!texto.isBlank()) {
			if (!texto.matches("\\d+")) {
				txtAreaResultado.setText("");
				txt.setStyle("-fx-border-color: red;");
				mostrarAlerta("Solo números son permitidos!");
				txt.setText("");
				Platform.runLater(() -> txt.requestFocus());
			} else {
				txt.setStyle("");
			}
		} else
			txt.setStyle("");
	}

}