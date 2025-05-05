package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que repressenta un sistema de refrigeració
 *<p>
 *     El generador de vapor transmet calor del circuit d’ai gua primari al circuit d’aigua secundari.
 *     Això permet que l’aigua en estat líquid del circuit secundari es converteixi en vapor.
 *     A més, quan es transmet calor del circuit primari al secondari, la temperatura de l’aigua disminueix.
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

public class GeneradorVapor implements InComponent {

    //atributs
    private boolean activat;

    //constructor
    public GeneradorVapor() {}

    //mètodes i getters
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    public void desactiva() {
        this.activat = false;
    }

    public boolean getActivat() {
        return activat;
    }

    public void revisa(PaginaIncidencies p) {
        // implementar
    }

    public float getCostOperatiu() {
        return 25;
    }

    public float calculaOutput(float input) {
        if (activat) {
            return (float) (input*0.9);
        }
        else {return 25;} //temperatura ambient
    }
}
