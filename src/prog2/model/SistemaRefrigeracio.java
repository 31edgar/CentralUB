package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

/**
 * Classe que representa un sistema de refrigeració dins d'una central nuclear.
 * <p>
 *     El sistema de refrigeració està format per una sèrie de bombes refrigerants
 *     que mouen l'aigua calenta generada pel reactor fins al generador de vapor.
 *     Aquest sistema extreu el calor generat pel reactor i el transporta fins al generador
 *     de vapor per a la seva conversió en energia tèrmica.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InBombaRefrigerant
 * @see SistemaRefrigeracio
 * @since 1.0
 */
public class SistemaRefrigeracio implements InComponent {

    // Atributs
    private ArrayList<BombaRefrigerant> bombesRefrigerants;

    /**
     * Constructor que inicialitza el sistema de refrigeració amb una llista buida de bombes.
     */
    public SistemaRefrigeracio() {
        bombesRefrigerants = new ArrayList<>();
    }

    /**
     * Afegeix una bomba al sistema de refrigeració.
     *
     * @param b La bomba a afegir.
     * @throws CentralUBException Si no es pot afegir la bomba al sistema.
     */
    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        this.bombesRefrigerants.add(b);
    }

    /**
     * Activa totes les bombes del sistema de refrigeració.
     *
     * @throws CentralUBException Si no es pot activar alguna bomba.
     */
    public void activa() throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.activa();
        }
    }

    /**
     * Activa una bomba específica del sistema de refrigeració per la seva ID.
     *
     * @param id L'ID de la bomba a activar.
     * @throws CentralUBException Si no es pot activar la bomba amb aquesta ID.
     */
    public void activaBomba(int id) throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getId() == id) {
                b.activa();
            }
        }
    }

    /**
     * Desactiva totes les bombes del sistema de refrigeració.
     */
    public void desactiva() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.desactiva();
        }
    }

    /**
     * Desactiva una bomba específica del sistema de refrigeració per la seva ID.
     *
     * @param id L'ID de la bomba a desactivar.
     * @throws CentralUBException Si no es pot desactivar la bomba amb aquesta ID.
     */
    public void desactivaBomba(int id) throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getId() == id) {
                b.desactiva();
                return;
            }
        }
    }

    /**
     * Retorna l'estat del sistema de refrigeració. El sistema es considera activat
     * si almenys una bomba està activada.
     *
     * @return {@code true} si alguna bomba està activada, {@code false} si totes estan desactivades.
     */
    public boolean getActivat() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa les bombes del sistema de refrigeració i afegeix incidències a la pàgina de bitàcola
     * si es detecten problemes.
     *
     * @param p La pàgina d'incidències on s'afegeixen les descripcions d'incidències.
     */
    public void revisa(PaginaIncidencies p) {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.revisa(p);
        }
    }

    /**
     * Calcula el cost operatiu del sistema de refrigeració basant-se només en les bombes actives.
     *
     * @return El cost operatiu total del sistema de refrigeració.
     */
    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            // Es considera el cost operatiu només de les bombes activades
            if (b.getActivat()) {
                cost += b.getCostOperatiu();
            }
        }
        return cost;
    }

    /**
     * Calcula el "output" del sistema de refrigeració en funció de les bombes actives.
     * Suma la capacitat de totes les bombes activades per determinar l'output total.
     *
     * @param input Un valor d'entrada que afecta el càlcul de l'output.
     * @return El valor total de l'output del sistema de refrigeració.
     */
    public float calculaOutput(float input) {
        float output = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                output += b.getCapacitat();
            }
        }
        return Math.min(input, output);
    }

    /**
     * Retorna una representació en format de text de l'estat del sistema de refrigeració,
     * incloent la informació de cada bomba.
     *
     * @return Una cadena de text amb la informació del sistema de refrigeració.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (BombaRefrigerant bomba : bombesRefrigerants) {
            sb.append(bomba.toString()).append("\n");
        }

        return sb.toString();
    }
}
