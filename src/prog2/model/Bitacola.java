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

        for (PaginaBitacola pagina : pagines) {
            if (pagina instanceof PaginaIncidencies paginaIncidencies) {
                incidencies.add(paginaIncidencies);
            }
        }

        return incidencies;
    }


}
