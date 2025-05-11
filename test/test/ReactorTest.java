package test;

import prog2.model.InComponent;
import prog2.model.Reactor;
import prog2.model.PaginaIncidencies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test per a {@link Reactor}.
 * <p>
 *     Es comprova el comportament del reactor pel que fa a l'activació,
 *     desactivació, càlcul d'output, cost operatiu i registre d'incidències
 *     relacionades amb la temperatura.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Reactor
 * @see InComponent
 * @since 1.0
 */
public class ReactorTest {

    private Reactor reactor;
    private PaginaIncidencies pagina;

    /**
     * Inicialitza el reactor abans de cada test.
     */
    @BeforeEach
    public void setUp() {
        reactor = new Reactor();
        pagina = new PaginaIncidencies(1);
    }

    /**
     * Test del mètode {@link Reactor#activa()} quan la temperatura és segura.
     */
    @Test
    public void testActivaTemperaturaSegura() throws CentralUBException {
        reactor.setTemperatura(900);
        reactor.activa();
        assertTrue(reactor.getActivat(), "El reactor hauria d'estar activat si la temperatura és segura.");
    }

    /**
     * Test del mètode {@link Reactor#activa()} quan la temperatura és massa alta.
     */
    @Test
    public void testActivaTemperaturaExcessiva() {
        reactor.setTemperatura(1200);
        CentralUBException exception = assertThrows(CentralUBException.class, () -> {
            reactor.activa();
        });
        assertEquals("No es pot iniciar el generador si es supera la temperatura màxima", exception.getMessage());
    }

    /**
     * Test del mètode {@link Reactor#desactiva()}.
     */
    @Test
    public void testDesactiva() {
        reactor.desactiva();
        assertFalse(reactor.getActivat(), "El reactor hauria d'estar desactivat.");
    }

    /**
     * Test del mètode {@link Reactor#getCostOperatiu()}.
     */
    @Test
    public void testGetCostOperatiu() {
        assertEquals(35.0f, reactor.getCostOperatiu(), "El cost operatiu hauria de ser 35.");
    }

    /**
     * Test del mètode {@link Reactor#calculaOutput(float)} quan el reactor està activat.
     */
    @Test
    public void testCalculaOutputActivat() throws CentralUBException {
        reactor.setTemperatura(500);
        reactor.activa();
        float input = 90;
        float expected = 500 + (100 - input) * 10;
        assertEquals(expected, reactor.calculaOutput(input),"L'output hauria de coincidir amb la fórmula donada si està activat.");
    }

    /**
     * Test del mètode {@link Reactor#calculaOutput(float)} quan el reactor està desactivat.
     */
    @Test
    public void testCalculaOutputDesactivat() {
        reactor.setTemperatura(400);
        reactor.desactiva();
        assertEquals(400f, reactor.calculaOutput(50), "L'output hauria de ser igual a la temperatura si està desactivat.");
    }

    /**
     * Test del mètode {@link Reactor#revisa(PaginaIncidencies)} quan la temperatura és excessiva.
     */
    @Test
    public void testRevisaTemperaturaExcessiva() {
        reactor.setTemperatura(1100);
        reactor.revisa(pagina);
        assertFalse(reactor.getActivat(), "El reactor s'hauria de desactivar si la temperatura supera els 1000.");
        assertTrue(pagina.toString().contains("El reactor es va desactivar per superar la temperatura màxima"),
                "S'hauria d'afegir una incidència per temperatura excessiva.");
    }

    /**
     * Test del mètode {@link Reactor#revisa(PaginaIncidencies)} quan la temperatura és normal.
     */
    @Test
    public void testRevisaTemperaturaNormal() throws CentralUBException {
        reactor.setTemperatura(800);
        reactor.activa();
        reactor.revisa(pagina);
        assertFalse(pagina.toString().contains("El reactor es va desactivar per superar la temperatura màxima"),
                "No s'hauria d'afegir cap incidència si la temperatura és segura.");
    }

    /**
     * Test del mètode {@link Reactor#toString()} per verificar la sortida de text.
     */
    @Test
    public void testToString() {
        reactor.setTemperatura(750);
        reactor.desactiva();
        String expected = "Activat= false, Temperatura= 750.0\n";
        assertEquals(expected, reactor.toString(), "La sortida de toString() hauria de ser correcta.");
    }
}
