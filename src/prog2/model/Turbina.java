package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

/**
 * Classe que representa una turbina dins d'una central nuclear.
 * <p>
 *     El vapor generat pel generador de vapor es fa servir per moure la turbina,
 *     generant energia elèctrica. La turbina pot estar activada o desactivada,
 *     i el seu output es calcula en funció de l'input del sistema.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InComponent
 * @see CentralUBException
 * @since 1.0
 */
public class Turbina implements InComponent, Serializable {

    // Atributs
    private boolean activat;

    /**
     * Constructor que inicialitza la turbina com a activada per defecte.
     */
    public Turbina() {
        this.activat = true;
    }

    /**
     * Activa la turbina.
     *
     * @throws CentralUBException Si no es pot activar la turbina per algun motiu.
     */
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    /**
     * Desactiva la turbina.
     */
    public void desactiva() {
        this.activat = false;
    }

    /**
     * Retorna l'estat actual de la turbina (activada o desactivada).
     *
     * @return {@code true} si la turbina està activada, {@code false} si està desactivada.
     */
    public boolean getActivat() {
        return this.activat;
    }

    /**
     * Revisa l'estat de la turbina i afegeix incidències a la pàgina de bitàcola
     * si la turbina no està activada.
     *
     * @param p La pàgina d'incidències on s'afegeixen les descripcions d'incidències.
     */
    public void revisa(PaginaIncidencies p) {
        if (!this.getActivat()) {
            p.afegeixIncidencia("Turbina fora de servei");
        }
    }

    /**
     * Retorna el cost operatiu de la turbina.
     *
     * @return El cost operatiu de la turbina.
     */
    public float getCostOperatiu() {
        return 20;
    }

    /**
     * Calcula el "output" de la turbina en funció de l'input.
     * Si la turbina està activada i l'input és menor que 100, l'output serà 0.
     * Si la turbina està activada i l'input és major o igual a 100, l'output serà el doble de l'input.
     * Si la turbina està desactivada, l'output serà 0.
     *
     * @param input El valor d'input del sistema.
     * @return El valor de l'output calculat de la turbina.
     */
    public float calculaOutput(float input) {
        if (activat && input < 100) {
            return 0;
        } else if (activat && input >= 100) {
            return input * 2;
        }
        return 0;
    }
}
