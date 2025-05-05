package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

/**
 * Classe que representa un sistema de refrigeració
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
    private ArrayList<BombaRefrigerant> bombesRefrigerants;

    //constructor
    public SistemaRefrigeracio() {}

    //mètodes
    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        this.bombesRefrigerants.add(b);
    }

    // Activar totes les bombes
    public void activa() throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.activa();
            return;
        }
    }

    // Activar una bomba en específic (id)
    public void activaBomba(int id) throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getId() == id) {
                b.activa();
                return;
            }
        }
    }

    // Desactivar totes les bombes
    public void desactiva() {
        for (BombaRefrigerant b : bombesRefrigerants) {
            b.desactiva();
        }
    }

    // Desactivar una bomba en específic (id)
    public void desactivaBomba(int id) throws CentralUBException {
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getId() == id) {
                b.desactiva();
                return;
            }
        }
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
        for (BombaRefrigerant b : bombesRefrigerants) {b.revisa(p);}
    }

    public float getCostOperatiu() {
        float cost = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            // Queda entès que el cost operatiu és en base a les bombes actives, no a totes les bombes.
            if (b.getActivat()) {cost += b.getCostOperatiu();}
        }
        return cost;
    }

    public float calculaOutput(float input) {
        float output = 0;
        for (BombaRefrigerant b : bombesRefrigerants) {
            if (b.getActivat()) {output += b.getCapacitat();}
        }
        return output;
    }
}
