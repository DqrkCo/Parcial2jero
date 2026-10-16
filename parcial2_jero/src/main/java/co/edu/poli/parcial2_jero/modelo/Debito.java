package co.edu.poli.parcial2_jero.modelo;

/**
 * Representa una tarjeta de tipo débito.
 * Hereda los atributos básicos de {@link Tarjeta} e incluye el saldo disponible.
 */
public class Debito extends Tarjeta {

	/** Saldo actual en la cuenta de la tarjeta de débito. */
	private double saldo;

	/**
	 * Constructor principal para instanciar una tarjeta de débito.
	 * 
	 * @param numero   Número identificador de la tarjeta.
	 * @param fechaExp Fecha de expedición en formato MM/yyyy.
	 * @param estado   Estado de la tarjeta.
	 * @param titular  Titular asociado a la tarjeta.
	 * @param saldo    Saldo inicial o actual.
	 */
	public Debito(String numero, String fechaExp, EstadoTarjeta estado, Titular titular, double saldo) {
		super(numero, fechaExp, estado, titular);
		this.saldo = saldo;
	}

	/**
	 * Obtiene el saldo de la tarjeta.
	 * 
	 * @return Saldo disponible.
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Establece el saldo de la tarjeta.
	 * 
	 * @param saldo Nuevo saldo de la cuenta.
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Debito [" + super.toString() + ", saldo=" + saldo + "]";
	}
}
