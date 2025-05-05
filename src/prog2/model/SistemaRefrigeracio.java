package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

/**
 * Classe que repressenta un sistema de refrigeració
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

public class SistemaRefrigeracio implements InComponent {

    //atributs
    private boolean activat;
    private ArrayList<BombaRefrigerant> bombesRefrigerants;

    //constructor
    public SistemaRefrigeracio() {}

    //mètodes
    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        this.bombesRefrigerants.add(b);
    }

    public void activa() throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.activa();
        }
        this.activat = true;
    }

    public void desactiva() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.desactiva();
        }
        this.activat = false;
    }

    public boolean getActivat() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {
                return true;
            }
        }
        return false;
    }

    public void revisa(PaginaIncidencies p) {
        //BombaRefrigerant.revisa();
    }

    public float getCostOperatiu() {
        return 130*bombesRefrigerants.size();
        // Falta implementar el 25% de possibilitats d'inhabilitar una bomba. Entenc que es fa a PaginaIncidencies.
    }

    public float calculaOutput(float input) {
        // N = nombre de bombes actives
        int N = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {N++;}
        }
        return Math.min(input, 250*N);
    }
}
