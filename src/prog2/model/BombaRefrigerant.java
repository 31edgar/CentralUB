package prog2.model;

import prog2.vista.CentralUBException;

/**
* Classe que repressenta una bomba refrigerant
*<p>
 *     El sistema de refrigeració està format per una sèrie de bombes refrigerants
 *     que mouen l'aigua calenta generada pel reactor fins al generador de vapor.
 *     D’aquest manera, el sistema de refrigeració extreu el calor
 *     generat pel reactor, i el transporta fins al generador de vapor.
 *
*</p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InBombaRefrigerant
 * @see SistemaRefrigeracio
 * @since 1.0
*
 */

public class BombaRefrigerant implements InBombaRefrigerant{
    // Atributs
    private int id;
    private boolean activat, foraDeServei;

    public BombaRefrigerant(int id, VariableUniforme variableUniforme) {
        this.id = id;

    }

    // Getters
    public int getId() {
        return this.id;
    }

    public boolean getActivat() {
        return this.activat;
    }

    // Altres mètodes
    public void activa() throws CentralUBException {
        if (!foraDeServei) {
            activat = true;
        } else {
            throw new CentralUBException("La bomba està fora de servei");
        }
    }

    public void desactiva() {
        activat = false;
    }

    public void revisa (PaginaIncidencies p) {
        // CAL IMPLEMENTAR

    }

    public boolean getForaDeServei() {
        return this.foraDeServei;
    }

    public float getCapacitat() {
        // CAL IMPLEMENTAR
    }

    public float getCostOperatiu() {
        // CAL IMPLEMENTAR
    }

    @Override
    public String toString() {
        return "Id=" + this.id + ", Activat=" + this.activat + ", Fora de servei=" + this.foraDeServei;
    }





}
