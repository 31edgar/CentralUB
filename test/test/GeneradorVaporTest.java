package test;

import prog2.model.GeneradorVapor;
import prog2.model.InComponent;
import prog2.model.PaginaIncidencies;
import prog2.vista.CentralUBException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test per a {@link GeneradorVapor}.
 * <p>
 *     Es comprova el comportament del generador de vapor
 *     respecte a l'activació, desactivació, càlcul d'output,
 *     cost operatiu i registre d'incidències.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see GeneradorVapor
 * @see InComponent
 * @since 1.0
 */
public class GeneradorVaporTest {

    private GeneradorVapor generador;
    private PaginaIncidencies pagina;

    /**
     * Inicialitza el generador abans de cada test.
     */
    @BeforeEach
    public void setUp() {
        generador = new GeneradorVapor();
        pagina = new PaginaIncidencies(1);
    }

    /**
     * Test del mètode {@link GeneradorVapor#activa()}.
     */
    @Test
    public void testActiva() throws CentralUBException {
        generador.activa();
        assertTrue(generador.getActivat(), "El generador hauria d'estar activat després d'executar activa().");
    }

    /**
     * Test del mètode {@link GeneradorVapor#desactiva()}.
     */
    @Test
    public void testDesactiva() {
        generador.desactiva();
        assertFalse(generador.getActivat(), "El generador hauria d'estar desactivat després d'executar desactiva().");
    }

    /**
     * Test del mètode {@link GeneradorVapor#getCostOperatiu()}.
     */
    @Test
    public void testGetCostOperatiu() {
        assertEquals(25.0f, generador.getCostOperatiu(),"El cost operatiu hauria de ser 25.");
    }

    /**
     * Test del mètode {@link GeneradorVapor#calculaOutput(float)} quan el generador està activat.
     */
    @Test
    public void testCalculaOutputActivat() throws CentralUBException {
        generador.activa();
        float input = 100f;
        float expected = 90f;
        assertEquals(expected, generador.calculaOutput(input), "L'output hauria de ser el 90% de l'input si està activat.");
    }

    /**
     * Test del mètode {@link GeneradorVapor#calculaOutput(float)} quan el generador està desactivat.
     */
    @Test
    public void testCalculaOutputDesactivat() {
        generador.desactiva();
        float input = 100f;
        float expected = 25f;
        assertEquals(expected, generador.calculaOutput(input), "L'output hauria de ser 25 si el generador està desactivat.");
    }

    /**
     * Test del mètode {@link GeneradorVapor#revisa(PaginaIncidencies)} quan el generador està desactivat.
     */
    @Test
    public void testRevisaDesactivat() {
        generador.desactiva();
        generador.revisa(pagina);
        assertTrue(pagina.toString().contains("Generador de Vapor fora de servei"),
                "Hauria d'afegir una incidència si el generador està desactivat.");
    }

    /**
     * Test del mètode {@link GeneradorVapor#revisa(PaginaIncidencies)} quan el generador està activat.
     */
    @Test
    public void testRevisaActivat() throws CentralUBException {
        generador.activa();
        generador.revisa(pagina);
        assertFalse(pagina.toString().contains("Generador de Vapor fora de servei"),
                "No hauria d'afegir cap incidència si el generador està activat.");
    }
}
