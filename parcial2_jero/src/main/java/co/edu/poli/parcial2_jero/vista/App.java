package co.edu.poli.parcial2_jero.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Clase principal que inicializa y lanza la aplicación JavaFX de gestión de tarjetas.
 */
public class App extends Application {

	private static Scene scene;

	/**
	 * Punto de entrada principal para aplicaciones JavaFX. Carga la interfaz gráfica inicial
	 * desde la vista FXML y configura el escenario principal (Stage).
	 *
	 * @param stage Escenario principal sobre el cual se monta la vista.
	 * @throws IOException Si se presenta un error al cargar el archivo FXML.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("/co/edu/poli/parcial2_jero/formCard.fxml"));
		scene = new Scene(root);
		stage.setTitle("Tarjetas");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Reemplaza la raíz de la escena actual con una nueva vista FXML.
	 *
	 * @param fxml Nombre del archivo FXML (sin la extensión .fxml).
	 * @throws IOException Si ocurre un fallo en el proceso de lectura de la vista.
	 */
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	/**
	 * Carga un componente JavaFX a partir de un archivo FXML especificado.
	 *
	 * @param fxml Nombre del archivo FXML a cargar.
	 * @return Elemento raíz {@link Parent} cargado.
	 * @throws IOException Si el recurso FXML no es encontrado o es inválido.
	 */
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	/**
	 * Método de arranque estándar de la aplicación.
	 *
	 * @param args Argumentos recibidos por línea de comandos.
	 */
	public static void main(String[] args) {
		launch();
	}

}