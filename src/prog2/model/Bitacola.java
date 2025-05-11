package prog2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa una bitàcola que conté diferents pàgines
 * amb informació rellevant sobre el funcionament d'una central nuclear.
 * <p>
 * Les pàgines poden ser de diferents tipus (econòmica, estat, incidències).
 * Aquesta classe permet afegir pàgines i obtenir un llistat filtrat
 * només amb les pàgines d'incidències.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @since 1.0
 */
public class Bitacola implements InBitacola {

    /** Llista de pàgines de la bitàcola */
    ArrayList<PaginaBitacola> pagines;

    /**
     * Crea una nova bitàcola buida.
     */
    public Bitacola() {
        pagines = new ArrayList<>();
    }

    /**
     * Afegeix una pàgina a la bitàcola.
     *
     * @param p la pàgina a afegir
     */
    public void afegeixPagina(PaginaBitacola p) {
        pagines.add(p);
    }

    /**
     * Retorna una llista amb totes les pàgines d'incidències
     * contingudes en la bitàcola.
     *
     * @return llista de {@code PaginaIncidencies}
     */
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> incidencies = new ArrayList<>();

        // Iterem per totes les pàgines, però només guardarem el contingut de les pàgines d'incidències
        for (PaginaBitacola pagina : pagines) {
            if (pagina instanceof PaginaIncidencies paginaIncidencies) {
                incidencies.add(paginaIncidencies);
            }
        }

        return incidencies;
    }

    /**
     * Retorna una representació en forma de cadena de totes les pàgines
     * de la bitàcola, depenent del seu tipus concret.
     *
     * @return la cadena amb la representació de totes les pàgines
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (PaginaBitacola p : pagines) {
            if (p instanceof PaginaEconomica pEconomica) {
                sb.append(pEconomica);
            }
            else if (p instanceof  PaginaEstat pEstat) {
                sb.append(pEstat);
            }
            else if (p instanceof PaginaIncidencies pIncidencies){
                sb.append(pIncidencies);
            }
        }

        return sb.toString();
    }
}
