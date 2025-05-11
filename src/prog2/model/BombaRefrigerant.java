package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa una bomba refrigerant dins del sistema de refrigeració d'una central nuclear.
 * <p>
 *     Cada bomba pot estar activada o desactivada, i pot quedar fora de servei amb una probabilitat del 25%
 *     a cada revisió. La seva funció és moure l’aigua calenta des del reactor fins al generador de vapor.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InBombaRefrigerant
 * @see SistemaRefrigeracio
 * @since 1.0
 */
public class BombaRefrigerant implements InBombaRefrigerant {

    /** Identificador de la bomba */
    private int id;

    /** Indica si la bomba està activada */
    private boolean activat;

    /** Indica si la bomba està fora de servei */
    private boolean foraDeServei;

    /** Variable aleatòria per a simular el comportament de la bomba */
    private VariableUniforme variableUniforme;

    /**
     * Crea una nova bomba refrigerant amb un identificador i una variable aleatòria donada.
     * La bomba s'inicialitza com a activada i operativa.
     *
     * @param variableUniforme la variable aleatòria associada a la bomba
     * @param id l'identificador únic de la bomba
     */
    public BombaRefrigerant(VariableUniforme variableUniforme, int id) {
        this.id = id;
        this.variableUniforme = variableUniforme;
        this.activat = false;
        this.foraDeServei = false;
    }

    /**
     * Retorna l'identificador de la bomba.
     *
     * @return l'id de la bomba
     */
    public int getId() {
        return this.id;
    }

    /**
     * Indica si la bomba està actualment activada.
     *
     * @return {@code true} si la bomba està activada; {@code false} altrament
     */
    public boolean getActivat() {
        return this.activat;
    }

    /**
     * Activa la bomba, si no està fora de servei.
     *
     * @throws CentralUBException si la bomba està fora de servei
     */
    public void activa() throws CentralUBException {
        if (!foraDeServei) {
            activat = true;
        } else {
            throw new CentralUBException("La bomba està fora de servei");
        }
    }

    /**
     * Desactiva la bomba.
     */
    public void desactiva() {
        activat = false;
    }

    /**
     * Revisa l'estat de la bomba i, amb una probabilitat del 25%, la declara fora de servei.
     * Si ja està fora de servei, afegeix una incidència a la pàgina d'incidències.
     *
     * @param p la pàgina d'incidències on afegir possibles errors
     */
    public void revisa(PaginaIncidencies p) {
        if (foraDeServei) {
            p.afegeixIncidencia("La bomba refrigerant " + this.id + " està fora de servei");
        }

        // Probabilitat del 25% de fallar
        if (variableUniforme.seguentValor() <= 25) {
            foraDeServei = true;
        }
    }

    /**
     * Retorna si la bomba està fora de servei.
     *
     * @return {@code true} si la bomba està fora de servei; {@code false} altrament
     */
    public boolean getForaDeServei() {
        return this.foraDeServei;
    }

    /**
     * Retorna la capacitat de la bomba refrigerant.
     *
     * @return capacitat en unitats arbitràries
     */
    public float getCapacitat() {
        return 250;
    }

    /**
     * Retorna el cost operatiu de la bomba refrigerant.
     *
     * @return cost operatiu en unitats monetàries
     */
    public float getCostOperatiu() {
        return 130;
    }

    /**
     * Retorna una representació en forma de cadena de la bomba refrigerant.
     *
     * @return una cadena amb l'estat de la bomba
     */
    @Override
    public String toString() {
        return "Id=" + this.id + ", Activat=" + this.activat + ", Fora de servei=" + this.foraDeServei;
    }
}
