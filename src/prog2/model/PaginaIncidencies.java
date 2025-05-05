package prog2.model;


import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola{

    // atributs
    int dia;
    ArrayList<String> incidencies;

    // constructor
    public PaginaIncidencies(int dia) {
        super(dia);
    }

    // mètodes
    public void afegeixIncidencia(String descIncidencia){
        incidencies.add("- Descripció Incidència: " + descIncidencia);
    }

    // toString
    @Override
    public String toString() {
        String str = "";

        for (String incidencia : incidencies) {
            str += incidencia + "\n";
        }

        return "# Pàgina Incidències\n- Dia: " + dia + "\n" + str;
    }
}
