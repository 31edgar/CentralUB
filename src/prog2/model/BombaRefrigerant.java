package prog2.model;

import prog2.vista.CentralUBException;

/**
* Classe que representa una bomba refrigerant
*<p>
 *     El sistema de refrigeració està format per una sèrie de bombes refrigerants
 *     que mouen l'aigua calenta generada pel reactor fins al generador de vapor.
 *     D’aquest manera, el sistema de refrigeració extreu el calor
 *     generat pel reactor, i el transporta fins al generador de vapor.
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
    // L'enunciat diu clarament que la classe ha tenir un id i dos booleans.
    // Però després només diu que el constructor rep una variable uniforme
    // Però he posat variable uniforme com a atribut perquè l'hem d'utilitzar a revisa
    private VariableUniforme variableUniforme;

    public BombaRefrigerant(VariableUniforme variableUniforme, int id) {
        this.id = id;
        this.variableUniforme = variableUniforme;
        this.activat = true;
        this.foraDeServei = false;
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
        if (foraDeServei) {p.afegeixIncidencia("La bomba refrigerant " + id + " està fora de servei");}

        // Probabilitat del 25% de fallar el dia següent (la desactivem després d'haver comprovat la incidència).
        if (variableUniforme.seguentValor() <= 25) {
            foraDeServei = true;
        }
    }

    public boolean getForaDeServei() {
        return this.foraDeServei;
    }

    public float getCapacitat() {
        return 250;
    }

    public float getCostOperatiu() {
        return 130;
    }

    @Override
    public String toString() {
        return "Id=" + this.id + ", Activat=" + this.activat + ", Fora de servei=" + this.foraDeServei;
    }





}
