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

    public String toString() {
        return ("# Pàgina Incidencies + \n" + "- Dia: " + dia + "\n" + incidencies.toString() + "\n");
    }
}
