package test;

import prog2.model.InComponent;
import prog2.model.Turbina;
import prog2.model.PaginaIncidencies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves per a {@link Turbina}.
 * <p>
 *     Comprova el comportament d'activació, desactivació, càlcul d'output
 *     i generació d'incidències de la turbina.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Turbina
 * @see InComponent
 * @since 1.0
 */
class TurbinaTest {

    private Turbina turbina;
    private PaginaIncidencies pagina;

    /**
     * Inicialitza la turbina i la pàgina d'incidències abans de cada test.
     */
    @BeforeEach
    void setUp() {
        turbina = new Turbina();
        pagina = new PaginaIncidencies(1);
    }

    /**
     * Comprova que s'activa correctament la turbina. {@link Turbina#activa()}
     */
    @Test
    void testActiva() throws CentralUBException {
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    /**
     * Comprova que es desactiva correctament la turbina. {@link Turbina#desactiva()}
     */
    @Test
    void testDesactiva() throws CentralUBException {
        turbina.activa();
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    /**
     * Comprova que calculaOutput retorna 0 si la turbina no està activada. {@link Turbina#calculaOutput(float)}
     */
    @Test
    void testCalculaOutputDesactivada() {
        turbina.desactiva();
        assertEquals(0, turbina.calculaOutput(120));
    }

    /**
     * Comprova que calculaOutput retorna 0 si l'entrada és inferior a 100, tot i estar activada. {@link Turbina#calculaOutput(float)}
     */
    @Test
    void testCalculaOutputActivadaInputBaix() throws CentralUBException {
        turbina.activa();
        assertEquals(0, turbina.calculaOutput(80));
    }

    /**
     * Comprova que calculaOutput retorna el doble de l'entrada si està activada i input ≥ 100. {@link Turbina#calculaOutput(float)}
     */
    @Test
    void testCalculaOutputActivadaInputAlt() throws CentralUBException {
        turbina.activa();
        assertEquals(240, turbina.calculaOutput(120), 0.01);
    }

    /**
     * Comprova que getCostOperatiu retorna el valor correcte. {@link Turbina#getCostOperatiu()}
     */
    @Test
    void testGetCostOperatiu() {
        assertEquals(20, turbina.getCostOperatiu(), 0.01);
    }

    /**
     * Comprova que revisa afegeix una incidència si la turbina no està activada. {@link Turbina#revisa(PaginaIncidencies)}
     */
    @Test
    void testRevisaAfegirIncidencia() {
        turbina.desactiva();
        turbina.revisa(pagina);
        assertTrue(pagina.toString().contains("Turbina fora de servei"));
    }
}
