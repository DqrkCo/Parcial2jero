package co.edu.poli.parcial2_jero.modelo;

/**
 * Representa una tarjeta de tipo crédito.
 * Hereda los atributos básicos de {@link Tarjeta} e incluye el límite de crédito disponible.
 */
public class Credito extends Tarjeta {

	/** Límite de cupo de la tarjeta de crédito. */
	private double limite;

	/**
	 * Constructor principal para instanciar una tarjeta de crédito.
	 * 
	 * @param numero   Número identificador de la tarjeta.
	 * @param fechaExp Fecha de expedición en formato MM/yyyy.
	 * @param estado   Estado de la tarjeta.
	 * @param titular  Titular asociado a la tarjeta.
	 * @param limite   Cupo máximo de crédito asignado.
	 */
	public Credito(String numero, String fechaExp, EstadoTarjeta estado, Titular titular, double limite) {
		super(numero, fechaExp, estado, titular);
		this.limite = limite;
	}

	/**
	 * Obtiene el límite de crédito.
	 * 
	 * @return Límite asignado.
	 */
	public double getLimite() {
		return limite;
	}

	/**
	 * Establece el límite de crédito.
	 * 
	 * @param limite Nuevo límite de crédito.
	 */
	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "Credito [" + super.toString() + ", limite=" + limite + "]";
	}
}
