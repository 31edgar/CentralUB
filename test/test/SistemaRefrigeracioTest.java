package test;

import prog2.model.InComponent;
import prog2.model.SistemaRefrigeracio;
import prog2.model.BombaRefrigerant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves per a {@link SistemaRefrigeracio}.
 * <p>
 *     Comprova el comportament de l'activació, desactivació, càlcul de cost operatiu
 *     i càlcul d'output en funció de les bombes refrigerants afegides.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see SistemaRefrigeracio
 * @see BombaRefrigerant
 * @see InComponent
 * @since 1.0
 */
class SistemaRefrigeracioTest {

    private SistemaRefrigeracio sistema;
    private BombaRefrigerant bomba1;
    private BombaRefrigerant bomba2;
    private VariableUniforme variableUniforme;

    /**
     * Inicialitza el sistema i dues bombes abans de cada test.
     */
    @BeforeEach
    void setUp() throws CentralUBException {
        sistema = new SistemaRefrigeracio();
        bomba1 = new BombaRefrigerant(variableUniforme, 1);
        bomba2 = new BombaRefrigerant(variableUniforme, 2);
        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);
    }

    /**
     * Comprova que totes les bombes s'activen correctament. {@link SistemaRefrigeracio#activa()}
     */
    @Test
    void testActiva() throws CentralUBException {
        sistema.desactiva();
        sistema.activa();
        assertTrue(bomba1.getActivat());
        assertTrue(bomba2.getActivat());
        assertTrue(sistema.getActivat());
    }

    /**
     * Comprova que es pot activar una bomba específica pel seu id. {@link SistemaRefrigeracio#activaBomba(int)}
     */
    @Test
    void testActivaBomba() throws CentralUBException {
        sistema.desactiva();
        sistema.activaBomba(1);
        assertTrue(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
    }

    /**
     * Comprova que totes les bombes es desactiven correctament. {@link SistemaRefrigeracio#desactiva()}
     */
    @Test
    void testDesactiva() throws CentralUBException {
        sistema.activa();
        sistema.desactiva();
        assertFalse(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
        assertFalse(sistema.getActivat());
    }

    /**
     * Comprova que es pot desactivar una bomba específica pel seu id. {@link SistemaRefrigeracio#desactivaBomba(int)}
     */
    @Test
    void testDesactivaBomba() throws CentralUBException {
        sistema.activa();
        sistema.desactivaBomba(2);
        assertTrue(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
    }

    /**
     * Comprova que el cost operatiu es calcula només amb les bombes actives. {@link SistemaRefrigeracio#getCostOperatiu()}
     */
    @Test
    void testGetCostOperatiu() throws CentralUBException {
        sistema.desactiva();
        sistema.activaBomba(2);
        assertEquals(130, sistema.getCostOperatiu());
    }

    /**
     * Comprova que el càlcul d'output suma les capacitats de les bombes actives. {@link SistemaRefrigeracio#calculaOutput(float)}
     */
    @Test
    void testCalculaOutput() throws CentralUBException {
        sistema.activaBomba(1);
        sistema.activaBomba(2);
        assertEquals(500, sistema.calculaOutput(0));
    }

    /**
     * Comprova que el càlcul d'output és 0 si no hi ha bombes actives. {@link SistemaRefrigeracio#calculaOutput(float)}
     */
    @Test
    void testCalculaOutputSenseBombesActives() {
        sistema.desactiva();
        assertEquals(0, sistema.calculaOutput(0), 0.01);
    }
}
