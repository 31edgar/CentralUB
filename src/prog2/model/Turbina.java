package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que repressenta un sistema de refrigeració
 *<p>
 *     El vapor generat pel generador es fa servir per moure la turbina,
 *     generant energia elèctrica.
 *</p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @since 1.0
 *
 */

public class Turbina implements InComponent {

    //atributs
    private boolean activat;

    //constructor
    public Turbina() {}

    //mètodes i getters
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    public void desactiva() {
        this.activat = false;
    }

    public boolean getActivat() {
        return this.activat;
    }

    public void revisa(PaginaIncidencies p) {
        // implementar
    }

    public float getCostOperatiu() {
        return 20;
    }

    public float calculaOutput(float input) {
        if (activat && input<100){return 0;}
        else if (activat && input>=100){return input*2;}
        return 0;
    }
}
