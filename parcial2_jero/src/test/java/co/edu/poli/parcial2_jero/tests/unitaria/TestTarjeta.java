package co.edu.poli.parcial2_jero.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.parcial2_jero.modelo.Debito;
import co.edu.poli.parcial2_jero.modelo.EstadoTarjeta;
import co.edu.poli.parcial2_jero.modelo.Tarjeta;
import co.edu.poli.parcial2_jero.modelo.Titular;

/**
 * Pruebas unitarias para validar las reglas de negocio, mutación de estado y excepciones
 * definidas en el modelo {@link Tarjeta}.
 */
public class TestTarjeta {

	/**
	 * Verifica que el método {@link Tarjeta#bloquear()} cambie correctamente el estado.
	 */
	@Test
	void bloquear_cambiaEstadoABloqueada() {
		Titular titular = new Titular("1", "Test");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.ACTIVA, titular, 1000);

		String mensaje = t.bloquear();

		assertEquals(EstadoTarjeta.BLOQUEADA, t.getEstado());
		assertTrue(mensaje.contains("BLOQUEADA"));
	}

	/**
	 * Verifica que el método {@link Tarjeta#activar()} active una tarjeta bloqueada.
	 */
	@Test
	void activar_cambiaEstadoAActiva() {
		Titular titular = new Titular("1", "Test");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.BLOQUEADA, titular, 1000);

		String mensaje = t.activar();

		assertEquals(EstadoTarjeta.ACTIVA, t.getEstado());
		assertTrue(mensaje.contains("ACTIVADA"));
	}

	/**
	 * Verifica que el método {@link Tarjeta#cancelar()} cancele una tarjeta activa.
	 */
	@Test
	void cancelar_cambiaEstadoACancelada() {
		Titular titular = new Titular("1", "Test");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.ACTIVA, titular, 1000);

		String mensaje = t.cancelar();

		assertEquals(EstadoTarjeta.CANCELADA, t.getEstado());
		assertTrue(mensaje.contains("CANCELADA"));
	}

	/**
	 * Comprueba que construir una tarjeta sin un estado lance {@link IllegalArgumentException}.
	 */
	@Test
	void constructor_sinEstado_lanzaExcepcion() {
		Titular titular = new Titular("1", "Test");

		assertThrows(IllegalArgumentException.class, () -> new Debito("123", "12/2025", null, titular, 1000));
	}

	/**
	 * Verifica que al intentar asignar un estado nulo vía setter se arroje una excepción.
	 */
	@Test
	void setEstado_null_lanzaExcepcion() {
		Titular titular = new Titular("1", "Test");
		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.ACTIVA, titular, 1000);

		assertThrows(IllegalArgumentException.class, () -> t.setEstado(null));
		assertEquals(EstadoTarjeta.ACTIVA, t.getEstado());
	}

	/**
	 * Evalúa el correcto funcionamiento de la fábrica estática {@link EstadoTarjeta#desdeEtiqueta(String)}.
	 */
	@Test
	void estado_desdeEtiqueta() {
		assertEquals(EstadoTarjeta.ACTIVA, EstadoTarjeta.desdeEtiqueta("Activa"));
		assertEquals(EstadoTarjeta.BLOQUEADA, EstadoTarjeta.desdeEtiqueta("Bloqueada"));
		assertEquals(EstadoTarjeta.CANCELADA, EstadoTarjeta.desdeEtiqueta("Cancelada"));
		assertThrows(IllegalArgumentException.class, () -> EstadoTarjeta.desdeEtiqueta("Suspendida"));
		assertThrows(IllegalArgumentException.class, () -> EstadoTarjeta.desdeEtiqueta(null));
	}

	/**
	 * Valida que los getters retornen de manera exacta los datos ingresados en el constructor.
	 */
	@Test
	void getters_retornaValoresCorrectos() {
		Titular titular = new Titular("1", "Test");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.ACTIVA, titular, 1000);

		assertEquals("123", t.getNumero());
		assertEquals("12/2025", t.getFechaExp());
		assertEquals(EstadoTarjeta.ACTIVA, t.getEstado());
		assertEquals(titular, t.getTitular());
	}

	/**
	 * Valida la correcta actualización de los atributos a través de los setters.
	 */
	@Test
	void setters_modificanValores() {
		Titular titular = new Titular("1", "Test");
		Titular nuevo = new Titular("2", "Nuevo");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.ACTIVA, titular, 1000);

		t.setNumero("999");
		t.setFechaExp("01/2030");
		t.setEstado(EstadoTarjeta.CANCELADA);
		t.setTitular(nuevo);

		assertEquals("999", t.getNumero());
		assertEquals("01/2030", t.getFechaExp());
		assertEquals(EstadoTarjeta.CANCELADA, t.getEstado());
		assertEquals(nuevo, t.getTitular());
	}

	/**
	 * Comprueba que la representación textual devuelta por {@link Tarjeta#toString()} incluya los datos clave.
	 */
	@Test
	void toString_contieneDatos() {
		Titular titular = new Titular("1", "Test");

		Tarjeta t = new Debito("123", "12/2025", EstadoTarjeta.BLOQUEADA, titular, 1000);

		String texto = t.toString();

		assertTrue(texto.contains("123"));
		assertTrue(texto.contains("12/2025"));
		assertTrue(texto.contains("Bloqueada"));
	}
}
