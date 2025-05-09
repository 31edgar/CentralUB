package prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola{
    // Atributs
    ArrayList<PaginaBitacola> pagines;

    // Constructor
    public Bitacola() {
        pagines = new ArrayList<>();
    }

    // Afegeix Pàgina
    public void afegeixPagina(PaginaBitacola p) {
        pagines.add(p);
    }

    // Get incidencies
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Iterem per totes les pàgines, però hem de veure de què tipus són i fer un cast per cada tipus
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
