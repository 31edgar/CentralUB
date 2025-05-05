package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que repressenta un sistema de refrigeració
 *<p>
 *     Fa servir combustible radioactiu per generar energia en forma de
 *     calor que es transmet a l’aigua del circuit primari.
 *</p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @since 1.0
 *
 */

public class Reactor implements InComponent{

    //atributs
    private boolean activat;
    private float temperatura;

    //constructor
    public Reactor() {}

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
        // IMPLEMENTAR
    }

    public float getCostOperatiu() {
        return 35;
    }

    public float calculaOutput(float input) {
        if (activat) {return temperatura + (100-input)*10;}
        return temperatura;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
}
