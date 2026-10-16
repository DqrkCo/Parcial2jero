package co.edu.poli.parcial2_jero.modelo;

/**
 * Clase abstracta base que modela los atributos y comportamientos comunes 
 * de cualquier tarjeta bancaria del sistema.
 */
public abstract class Tarjeta {
	
	/** Número identificador de la tarjeta. */
	private String numero;
	
	/** Fecha de expedición de la tarjeta (MM/yyyy). */
	private String fechaExp;

	/** Estado actual de la tarjeta. */
	private EstadoTarjeta estado;

	/** Titular asignado a la tarjeta. */
	private Titular titular;

	/**
	 * Constructor base de la tarjeta.
	 * 
	 * @param numero   Número único de tarjeta.
	 * @param fechaExp Fecha de expedición.
	 * @param estado   Estado de la tarjeta (no puede ser nulo).
	 * @param titular  Titular asignado.
	 */
	public Tarjeta(String numero, String fechaExp, EstadoTarjeta estado, Titular titular) {
		this.numero = numero;
		this.fechaExp = fechaExp;
		this.estado = validarEstado(estado);
		this.titular = titular;
	}

	/**
	 * Valida que el estado no sea nulo.
	 * 
	 * @param estado Instancia de {@link EstadoTarjeta}.
	 * @return El estado validado.
	 * @throws IllegalArgumentException Si el estado es {@code null}.
	 */
	private static EstadoTarjeta validarEstado(EstadoTarjeta estado) {
		if (estado == null) {
			throw new IllegalArgumentException("El estado de la tarjeta es obligatorio.");
		}
		return estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public EstadoTarjeta getEstado() {
		return estado;
	}

	public void setEstado(EstadoTarjeta estado) {
		this.estado = validarEstado(estado);
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	/**
	 * Cambia el estado de la tarjeta a {@link EstadoTarjeta#BLOQUEADA}.
	 * 
	 * @return Mensaje de confirmación del cambio.
	 */
	public String bloquear() {
		this.estado = EstadoTarjeta.BLOQUEADA;
		return "Tarjeta " + numero + " BLOQUEADA.";
	}

	/**
	 * Cambia el estado de la tarjeta a {@link EstadoTarjeta#ACTIVA}.
	 * 
	 * @return Mensaje de confirmación del cambio.
	 */
	public String activar() {
		this.estado = EstadoTarjeta.ACTIVA;
		return "Tarjeta " + numero + " ACTIVADA.";
	}

	/**
	 * Cambia el estado de la tarjeta a {@link EstadoTarjeta#CANCELADA}.
	 * 
	 * @return Mensaje de confirmación del cambio.
	 */
	public String cancelar() {
		this.estado = EstadoTarjeta.CANCELADA;
		return "Tarjeta " + numero + " CANCELADA.";
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", titular=" + titular;
	}
}
