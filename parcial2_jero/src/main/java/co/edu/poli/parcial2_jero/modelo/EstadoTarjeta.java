package co.edu.poli.parcial2_jero.modelo;

/**
 * Enumeración que representa los estados válidos de una tarjeta de crédito o débito.
 * Contiene la etiqueta requerida para el almacenamiento en base de datos (columna ENUM)
 * y despliegue en la interfaz gráfica.
 */
public enum EstadoTarjeta {

	ACTIVA("Activa"), BLOQUEADA("Bloqueada"), CANCELADA("Cancelada");

	/** Texto visible/almacenable del estado. */
	private final String etiqueta;

	/**
	 * Constructor interno del Enum.
	 * 
	 * @param etiqueta Nombre legible del estado.
	 */
	EstadoTarjeta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Obtiene la etiqueta textual del estado.
	 * 
	 * @return Etiqueta del estado.
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Mapea una cadena de texto a su correspondiente valor en la enumeración.
	 * 
	 * @param texto Cadena recibida de la base de datos o formulario.
	 * @return Instancia de {@link EstadoTarjeta}.
	 * @throws IllegalArgumentException Si el texto no coincide con ningún estado.
	 */
	public static EstadoTarjeta desdeEtiqueta(String texto) {
		if (texto != null) {
			for (EstadoTarjeta e : values()) {
				if (e.etiqueta.equalsIgnoreCase(texto.trim())) {
					return e;
				}
			}
		}
		throw new IllegalArgumentException("Estado de tarjeta no válido: " + texto);
	}

	@Override
	public String toString() {
		return etiqueta;
	}
}
