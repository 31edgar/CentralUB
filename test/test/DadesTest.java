package test;

import prog2.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Classe de test per verificar el funcionament de la classe {@link Dades}.
 * <p>
 * Aquesta classe conté els tests unitaris per comprovar els mètodes principals
 * de la classe Dades, que gestiona tot l'estat i operacions de la central nuclear.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Dades
 * @see InDades
 * @see Reactor
 * @see SistemaRefrigeracio
 * @see GeneradorVapor
 * @see Turbina
 * @see Bitacola
 * @since 1.0
 */
public class DadesTest {

    private Dades dades;

    /**
     * Configuració inicial per a cada test.
     * <p>
     * S'executa abans de cada mètode de test i inicialitza un objecte Dades
     * amb els valors per defecte.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        dades = new Dades();
    }

    /**
     * Test per verificar la inicialització correcta dels atributs.
     */
    @Test
    public void testInicialitzacio() {
        assertEquals(100, dades.getInsercioBarres());
        assertFalse(dades.mostraReactor().getActivat());
        assertFalse(dades.mostraSistemaRefrigeracio().getActivat());
        assertTrue(dades.mostraBitacola().getIncidencies().isEmpty());
        assertEquals(1, dades.mostraEstat().getDia());
    }

    /**
     * Test per verificar el setter d'inserció de barres amb valors vàlids. {@link Dades#setInsercioBarres(float)}
     */
    @Test
    public void testSetInsercioBarresValid() throws CentralUBException {
        dades.setInsercioBarres(50);
        assertEquals(50, dades.getInsercioBarres());
    }

    /**
     * Test per verificar el setter d'inserció de barres amb valors invàlids. {@link Dades#setInsercioBarres(float)}
     */
    @Test
    public void testSetInsercioBarresInvalid() {
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(-1));
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(101));
    }

    /**
     * Test per verificar l'activació i desactivació del reactor. {@link Dades#activaReactor()} {@link Dades#desactivaReactor()}
     */
    @Test
    public void testActivaDesactivaReactor() throws CentralUBException {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());

        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    /**
     * Test per verificar l'activació i desactivació del sistema de refrigeració. {@link Dades#activaBombes()} {@link Dades#desactivaBombes()}
     */
    @Test
    public void testActivaDesactivaBombes() throws CentralUBException {
        dades.activaBombes();
        assertTrue(dades.mostraSistemaRefrigeracio().getActivat());

        dades.desactivaBombes();
        assertFalse(dades.mostraSistemaRefrigeracio().getActivat());
    }

    /**
     * Test per verificar el càlcul de potència. {@link Dades#calculaPotencia()}
     */
    @Test
    public void testCalculaPotencia() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        float potencia = dades.calculaPotencia();
        assertTrue(potencia > 0);
    }

    /**
     * Test per verificar el càlcul de demanda satisfeta. {@link Dades#demandaSatisfeta(float)}
     */
    @Test
    public void testDemandaSatisfeta() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        float[] resultat = dades.demandaSatisfeta(100);
        assertEquals(3, resultat.length);
        assertEquals(100, resultat[0]);
        assertTrue(resultat[1] > 0); // Potència calculada
        assertTrue(resultat[2] > 0); // Percentatge
    }

    /**
     * Test per verificar l'actualització de l'economia. {@link Dades#actualitzaEconomia(float)}
     */
    @Test
    public void testActualitzaEconomia() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        PaginaEconomica pagina = dades.actualitzaEconomia(100);
        assertNotNull(pagina);
        assertTrue(pagina.getBenefici() >= 0);
        assertTrue(pagina.getCostosOperatius() > 0);
    }

    /**
     * Test per verificar la finalització d'un dia. {@link Dades#finalitzaDia(float)}
     */
    @Test
    public void testFinalitzaDia() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        Bitacola resultat = dades.finalitzaDia(100);
        assertNotNull(resultat);
        assertEquals(3, resultat.getIncidencies().size() + 2); // 1 econòmica + 1 estat + incidències

        // Verifiquem que s'ha incrementat el dia
        assertEquals(2, dades.mostraEstat().getDia());
    }

    /**
     * Test per verificar la refrigeració del reactor.
     */
    @Test
    public void testRefrigeraReactor() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        float temperaturaInicial = dades.mostraReactor().getTemperatura();
        dades.finalitzaDia(100);
        float temperaturaFinal = dades.mostraReactor().getTemperatura();

        assertTrue(temperaturaFinal < temperaturaInicial);
    }

    /**
     * Test per verificar la revisió dels components.
     */
    @Test
    public void testRevisaComponents() throws CentralUBException {
        dades.activaReactor();
        dades.activaBombes();

        Bitacola resultat = dades.finalitzaDia(100);
        List<PaginaIncidencies> incidencies = dades.mostraIncidencies();

        // Comprovem que s'han revisat tots els components
        // (el nombre exacte dependrà de la implementació i valors aleatoris)
        assertFalse(incidencies.isEmpty());
    }
}