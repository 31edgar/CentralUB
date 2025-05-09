package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa un reactor
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
        if (this.temperatura > 1000) {throw new CentralUBException("No es pot iniciar el generador si es supera la temperatura màxima");}
        this.activat = true;
    }

    public void desactiva() {
        this.activat = false;
    }

    public boolean getActivat() {
        return activat;
    }

    public void revisa(PaginaIncidencies p) {
        if (temperatura > 1000) {p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");}
        desactiva();
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

    @Override
    public String toString() {
        return "Activat= " + activat + ", Temperatura= " + temperatura + "\n";
    }
}


