package co.edu.poli.parcial2_jero.servicios;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones de persistencia CRUD básico.
 * 
 * @param <T> Tipo de entidad de modelo manejada por la interfaz.
 */
public interface CRUD<T> {

	/**
	 * Inserta un nuevo registro en la base de datos.
	 * 
	 * @param t Entidad a persistir.
	 * @return Mensaje con el resultado de la operación.
	 * @throws Exception Si ocurre un error de persistencia.
	 */
	String create(T t) throws Exception;

	/**
	 * Consulta una entidad por su clave primaria o identificador.
	 * 
	 * @param <K> Tipo del identificador.
	 * @param id  Identificador único de la entidad.
	 * @return La entidad correspondiente o {@code null} si no existe.
	 * @throws Exception Si ocurre un error de consulta.
	 */
	<K> T readone(K id) throws Exception;
	
	/**
	 * Obtiene el listado completo de registros de la entidad.
	 * 
	 * @return Lista de entidades encontradas.
	 * @throws Exception Si ocurre un error de consulta.
	 */
	List<T> readall() throws Exception;
}
