package test;

import prog2.model.Bitacola;
import prog2.model.PaginaBitacola;
import prog2.model.PaginaIncidencies;
import prog2.model.PaginaEconomica;
import prog2.model.PaginaEstat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Classe de test per verificar el funcionament de la classe {@link Bitacola}.
 * <p>
 * Aquesta classe conté els tests unitaris per comprovar els mètodes principals
 * de la classe Bitacola, incloent l'afegiment de pàgines i la recuperació
 * d'incidències.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Bitacola
 * @see PaginaBitacola
 * @see PaginaIncidencies
 * @see PaginaEstat
 * @see PaginaEconomica
 * @since 1.0
 */
public class BitacolaTest {

    private Bitacola bitacola;
    private PaginaIncidencies paginaIncidencies;
    private PaginaEconomica paginaEconomica;
    private PaginaEstat paginaEstat;

    /**
     * Configuració inicial per a cada test.
     * <p>
     * S'executa abans de cada mètode de test i inicialitza una nova Bitacola
     * amb diferents tipus de pàgines.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        bitacola = new Bitacola();
        paginaIncidencies = new PaginaIncidencies(1);
        paginaEconomica = new PaginaEconomica(2, 250, 225, 90, 225, 0, 77, 77);
        paginaEstat = new PaginaEstat(3, 85, 160, 154, 125, 128);
    }

    /**
     * Test per verificar l'afegiment correcte de pàgines a la bitàcola. {@link Bitacola#afegeixPagina(PaginaBitacola)}
     * <p>
     * Comprova que després d'afegir pàgines, la quantitat total és la correcta.
     * </p>
     */
    @Test
    public void testAfegeixPagina() {
        // Comprovem que inicialment està buida
        assertEquals(0, bitacola.getIncidencies().size());

        // Afegim una pàgina d'incidències
        bitacola.afegeixPagina(paginaIncidencies);
        assertEquals(1, bitacola.getIncidencies().size());

        // Afegim una pàgina econòmica (no ha d'afectar el compte d'incidències)
        bitacola.afegeixPagina(paginaEconomica);
        assertEquals(1, bitacola.getIncidencies().size());

        // Afegim una altra pàgina d'incidències
        bitacola.afegeixPagina(new PaginaIncidencies(7));
        assertEquals(2, bitacola.getIncidencies().size());
    }

    /**
     * Test per verificar la recuperació correcta de les incidències. {@link Bitacola#getIncidencies()}
     * <p>
     * Comprova que només es retornen les pàgines d'incidències i que es manté
     * l'ordre d'inserció.
     * </p>
     */
    @Test
    public void testGetIncidencies() {
        // Afegim diferents tipus de pàgines
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);

        PaginaIncidencies pi1 = new PaginaIncidencies(5);
        PaginaIncidencies pi2 = new PaginaIncidencies(13);

        pi1.afegeixIncidencia("Incidencia 1");
        pi2.afegeixIncidencia("Incidencia 2");

        bitacola.afegeixPagina(pi1);
        bitacola.afegeixPagina(pi2);

        // Obtenim la llista d'incidències
        List<PaginaIncidencies> incidencies = bitacola.getIncidencies();

        // Comprovem que només hi ha 2 incidències
        assertEquals(2, incidencies.size());

        // Comprovem que les descripcions
        assertTrue(incidencies.get(0).toString().contains("Incidencia 1"));
        assertTrue(incidencies.get(1).toString().contains("Incidencia 2"));
    }

    /**
     * Test per verificar el comportament amb una bitàcola buida. {@link Bitacola#getIncidencies()} {@link Bitacola#toString()}
     * <p>
     * Comprova que els mètodes funcionen correctament quan no hi ha pàgines
     * afegides a la bitàcola.
     * </p>
     */
    @Test
    public void testBitacolaBuida() {
        // Comprovem que no hi ha incidències
        assertEquals(0, bitacola.getIncidencies().size());

        // Comprovem que el toString retorna una cadena buida
        assertEquals("", bitacola.toString());
    }
}