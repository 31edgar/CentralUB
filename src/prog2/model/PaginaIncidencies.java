package prog2.model;

import java.util.ArrayList;

/**
 * Classe que representa una pàgina d'incidències dins de la bitàcola de la central nuclear.
 * <p>
 *     Aquesta classe manté una llista d'incidències que es produeixen en la central durant un dia específic.
 *     Permet afegir noves incidències a la llista i obtenir una representació textual d'elles.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Bitacola
 * @see PaginaBitacola
 * @since 1.0
 */
public class PaginaIncidencies extends PaginaBitacola {

    // Atributs
    private ArrayList<String> incidencies;

    /**
     * Constructor que inicialitza una pàgina d'incidències amb el dia corresponent.
     * Inicialitza també la llista d'incidències com a una nova llista buida.
     *
     * @param dia El dia de la pàgina d'incidències.
     */
    public PaginaIncidencies(int dia) {
        super(dia);
        incidencies = new ArrayList<>();
    }

    /**
     * Afegeix una nova incidència a la llista d'incidències.
     *
     * @param descIncidencia Descripció de la incidència a afegir.
     */
    public void afegeixIncidencia(String descIncidencia) {
        incidencies.add("- Descripció Incidència: " + descIncidencia);
    }

    /**
     * Retorna una representació en format de text de la pàgina d'incidències.
     * Aquesta representació inclou el dia i totes les incidències registrades.
     *
     * @return Una cadena de text amb la informació de la pàgina d'incidències.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (String incidencia : incidencies) {
            str.append(incidencia).append("\n");
        }

        return "# Pàgina Incidències\n- Dia: " + super.getDia() + "\n" + str.toString();
    }
}
