package co.edu.poli.parcial2_jero.servicios;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Gestiona la conexión a la base de datos MySQL aplicando el patrón Singleton.
 * Carga las credenciales desde un archivo de variables de entorno (.env).
 */
public class ConexionBD {

	/** Instancia única de la clase (Singleton). */
	private static ConexionBD instancia;
	
	/** Objeto de conexión JDBC activo. */
	private Connection conexion;

	/**
	 * Constructor privado para prevenir la instanciación directa.
	 * Lee las variables del archivo .env e inicia la conexión MySQL.
	 * 
	 * @throws Exception Si faltan variables en el .env o falla la carga del driver MySQL.
	 */
	private ConexionBD() throws Exception {

		Dotenv dotenv = Dotenv.load();

		String url = dotenv.get("DB_URL");
		String user = dotenv.get("DB_USER");
		String pass = dotenv.get("DB_PASSWORD");

		if (url == null || user == null || pass == null) {
			throw new RuntimeException("Faltan variables en el .env");
		}

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, user, pass);
	}

	/**
	 * Obtiene la instancia única de {@link ConexionBD}.
	 * 
	 * @return Instancia singleton de la conexión.
	 * @throws Exception Si ocurre un error al inicializar la conexión.
	 */
	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	/**
	 * Retorna la conexión activa a la base de datos.
	 * Reinstancia el objeto si la conexión previa ha sido cerrada.
	 * 
	 * @return Conexión {@link Connection} lista para operar.
	 * @throws Exception Si falla la reconexión.
	 */
	public Connection getConexion() throws Exception {
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}
