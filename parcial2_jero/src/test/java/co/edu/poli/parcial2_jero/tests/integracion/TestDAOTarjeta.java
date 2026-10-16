package co.edu.poli.parcial2_jero.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.poli.parcial2_jero.modelo.Credito;
import co.edu.poli.parcial2_jero.modelo.Debito;
import co.edu.poli.parcial2_jero.modelo.EstadoTarjeta;
import co.edu.poli.parcial2_jero.modelo.Tarjeta;
import co.edu.poli.parcial2_jero.modelo.Titular;
import co.edu.poli.parcial2_jero.servicios.ConexionBD;
import co.edu.poli.parcial2_jero.servicios.DAOTarjeta;

/**
 * Pruebas de integración para comprobar las operaciones de persistencia SQL 
 * de la clase {@link DAOTarjeta} sobre la base de datos.
 */
public class TestDAOTarjeta {

	/** Instancia del DAO a probar. */
    DAOTarjeta dao = new DAOTarjeta();

    // Borra las tarjetas de prueba antes y después de cada test,
    // así los tests se pueden ejecutar varias veces sin claves duplicadas.
    @BeforeEach
    @AfterEach
    void limpiar() throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        try (PreparedStatement ps = con
                .prepareStatement("DELETE FROM tarjeta WHERE numero IN ('999001','999002','999003')")) {
            ps.executeUpdate();
        }
    }

    /**
     * Verifica la inserción y consulta posterior de una tarjeta de débito en la BD.
     */
    @Test
    void create_debito_y_readone() throws Exception {

        Titular titular = new Titular("T001", "Test");

        Debito debito = new Debito(
                "999001",
                "12/2025",
                EstadoTarjeta.ACTIVA,
                titular,
                5000.0
        );

        String result = dao.create(debito);

        assertTrue(result.contains("guardada"));

        Tarjeta t = dao.readone("999001");

        assertNotNull(t);
        assertTrue(t instanceof Debito);
        assertEquals(EstadoTarjeta.ACTIVA, t.getEstado());

        Debito d = (Debito) t;
        assertEquals(5000.0, d.getSaldo());
    }

    /**
     * Verifica la inserción y lectura de una tarjeta de crédito en la BD.
     */
    @Test
    void create_credito_y_readone() throws Exception {

        Titular titular = new Titular("T001", "Test");

        Credito credito = new Credito(
                "999002",
                "12/2025",
                EstadoTarjeta.BLOQUEADA,
                titular,
                10000.0
        );

        String result = dao.create(credito);

        assertTrue(result.contains("guardada"));

        Tarjeta t = dao.readone("999002");

        assertNotNull(t);
        assertTrue(t instanceof Credito);
        assertEquals(EstadoTarjeta.BLOQUEADA, t.getEstado());

        Credito c = (Credito) t;
        assertEquals(10000.0, c.getLimite());
    }
    
    /**
     * Comprueba que la persitencia respete correctamente el estado CANCELADA.
     */
    @Test
    void estado_cancelada_sePersiste() throws Exception {

        Titular titular = new Titular("T001", "Test");

        dao.create(new Debito("999003", "12/2025", EstadoTarjeta.CANCELADA, titular, 100.0));

        Tarjeta t = dao.readone("999003");

        assertNotNull(t);
        assertEquals(EstadoTarjeta.CANCELADA, t.getEstado());
    }

    /**
     * Confirma que una búsqueda de número inexistente retorne {@code null}.
     */
    @Test
    void readone_noExiste() throws Exception {

        Tarjeta t = dao.readone("000000");

        assertNull(t);
    }
}
