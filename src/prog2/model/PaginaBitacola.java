package prog2.model;

import java.io.Serializable;

/**
 * Classe abstracta que representa una pàgina de bitàcola en una central nuclear.
 * <p>
 *     Una pàgina de bitàcola conté informació relacionada amb un dia específic de la central,
 *     incloent-hi l'atribut dia que indica el dia per al qual es registren les dades.
 *     Aquesta classe serveix com a base per a altres classes concretes que representen
 *     tipus específics de pàgines de bitàcola.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Bitacola
 * @since 1.0
 */
public abstract class PaginaBitacola implements Serializable {

    // Atributs
    private int dia;

    /**
     * Constructor que inicialitza la pàgina de bitàcola amb el dia indicat.
     * @param dia El dia per al qual es registrarà la pàgina de bitàcola.
     */
    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    /**
     * Retorna el dia associat a aquesta pàgina de bitàcola.
     * @return El dia de la pàgina de bitàcola.
     */
    public int getDia() {
        return this.dia;
    }

    /**
     * Estableix el dia associat a aquesta pàgina de bitàcola.
     * @param dia El dia per al qual es registrarà la pàgina de bitàcola.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }
}
