package co.edu.poli.parcial2_jero.modelo;

/**
 * Clase de modelo que representa al propietario o titular de una o más tarjetas.
 */
public class Titular {

	/** Documento identificador del titular. */
	private String id;

	/** Nombre completo del titular. */
	private String nombre;

	/**
	 * Constructor principal para instanciar un Titular.
	 * 
	 * @param id     Identificador único del titular.
	 * @param nombre Nombre del titular.
	 */
	public Titular(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return id + ", " + nombre;
	}
}
