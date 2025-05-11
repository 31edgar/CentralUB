package test;

import prog2.model.VariableUniforme;
import prog2.model.BombaRefrigerant;
import prog2.model.PaginaIncidencies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test per verificar el funcionament de la classe BombaRefrigerant. {@link BombaRefrigerant}
 * <p>
 * Aquesta classe conté els tests unitaris per comprovar els mètodes principals
 * de la classe BombaRefrigerant, incloent l'activació, desactivació i revisió
 * de l'estat de la bomba.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see BombaRefrigerant
 * @see VariableUniforme
 * @see PaginaIncidencies
 * @since 1.0
 */
public class BombaRefrigerantTest {

    private BombaRefrigerant bomba;
    private VariableUniformeMock variableMock;
    private PaginaIncidencies paginaIncidencies;

    /**
     * Configuració inicial per a cada test.
     * <p>
     * S'executa abans de cada mètode de test i inicialitza una nova BombaRefrigerant
     * amb una VariableUniforme mockeada i una pàgina d'incidències buida.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        variableMock = new VariableUniformeMock(12345);
        bomba = new BombaRefrigerant(variableMock, 1);
        paginaIncidencies = new PaginaIncidencies(1);
    }

    /**
     * Test per verificar el constructor i els getters bàsics.
     * <p>
     * Comprova que el constructor inicialitza correctament els valors
     * i que els getters retornen els valors esperats.
     * </p>
     */
    @Test
    public void testConstructorIGetters() {
        assertEquals(1, bomba.getId());
        assertFalse(bomba.getActivat());
        assertFalse(bomba.getForaDeServei());
        assertEquals(250, bomba.getCapacitat());
        assertEquals(130, bomba.getCostOperatiu());
    }

    /**
     * Test per verificar l'activació correcta de la bomba. {@link BombaRefrigerant#activa()}
     * <p>
     * Comprova que el mètode activa() canvia l'estat de la bomba
     * quan no està fora de servei.
     * </p>
     */
    @Test
    public void testActiva() throws CentralUBException {
        bomba.desactiva();
        bomba.activa();
        assertTrue(bomba.getActivat());
    }

    /**
     * Test per verificar que no es pot activar una bomba fora de servei. {@link BombaRefrigerant#revisa(PaginaIncidencies)} {@link BombaRefrigerant#activa()}
     * <p>
     * Comprova que es llança una excepció quan s'intenta activar
     * una bomba marcada com a fora de servei.
     * </p>
     */
    @Test
    public void testActivaForaDeServei() {
        bomba.desactiva();
        variableMock.setSeguentValor(10); // Forcem que quedi fora de servei
        bomba.revisa(paginaIncidencies);

        assertThrows(CentralUBException.class, () -> bomba.activa());
    }

    /**
     * Test per verificar la desactivació correcta de la bomba. {@link BombaRefrigerant#desactiva()}
     * <p>
     * Comprova que el mètode desactiva() canvia l'estat de la bomba.
     * </p>
     */
    @Test
    public void testDesactiva() {
        bomba.desactiva();
        assertFalse(bomba.getActivat());
    }

    /**
     * Test per verificar la revisió quan la bomba està operativa. {@link BombaRefrigerant#desactiva()}
     * <p>
     * Comprova que no s'afegeix cap incidència quan la bomba
     * està en bon estat.
     * </p>
     */
    @Test
    public void testRevisaOperativa() {
        variableMock.setSeguentValor(30); // Valor >25 perquè no falli
        bomba.revisa(paginaIncidencies);

        assertFalse(bomba.getForaDeServei());
        assertFalse(paginaIncidencies.toString().contains("fora de servei"));
    }

    /**
     * Test per verificar la revisió quan la bomba falla. {@link BombaRefrigerant#revisa(PaginaIncidencies)}
     * <p>
     * Comprova que s'afegeix una incidència quan la bomba
     * passa a estar fora de servei.
     * </p>
     */
    @Test
    public void testRevisaFalla() {
        variableMock.setSeguentValor(20); // Valor <=25 per forçar fallada al dia següent
        bomba.revisa(paginaIncidencies); // Primera crida per a que la bomba passi a estar fora de servei
        bomba.revisa(paginaIncidencies); // Segona crida per a que s'afegeixi la incidència (dia següent)

        assertTrue(bomba.getForaDeServei());
        assertTrue(paginaIncidencies.toString().contains("fora de servei"));
    }

    /**
     * Test per verificar la representació en String de la bomba. {@link BombaRefrigerant#toString()}
     * <p>
     * Comprova que el mètode toString() retorna una cadena
     * amb la informació esperada.
     * </p>
     */
    @Test
    public void testToString() {
        String resultat = bomba.toString();
        assertTrue(resultat.contains("Id=1"));
        assertTrue(resultat.contains("Activat=false"));
        assertTrue(resultat.contains("Fora de servei=false"));
    }

    /**
     * Classe mock per a VariableUniforme per controlar els valors de prova.
     */
    private static class VariableUniformeMock extends VariableUniforme {
        private int nextValue;

        public VariableUniformeMock(long seed) {
            super(seed);
        }

        public void setSeguentValor(int value) {
            this.nextValue = value;
        }

        @Override
        public int seguentValor() {
            return nextValue;
        }
    }
}