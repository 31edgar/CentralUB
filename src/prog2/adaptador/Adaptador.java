package prog2.adaptador;

import java.io.*;

import prog2.model.Dades;
import prog2.model.PaginaEstat;
import prog2.vista.*;

public class Adaptador implements Serializable {

    // creem una instància de Dades i un atribut per a carregar una central
    private static final Dades dades = new Dades();

    // mètodes
    public static String finalitzaDia(float demandaPotencia) {
        return dades.finalitzaDia(demandaPotencia).toString();
    }

        // barres
    public static float getInsercioBarres() {
        return dades.getInsercioBarres();
    }
    public static void setInsercioBarres(float insercioBarres) {
        dades.setInsercioBarres(insercioBarres);
    }

        // reactor
    public static void activaReactor(){
        dades.activaReactor();
    }
    public static void desactivaReactor(){
        dades.desactivaReactor();
    }
    public static String mostraReactor(){
        return dades.mostraReactor().toString();
    }

        // sistema de refrigeració
    public static void activaBombes(){
        dades.activaBombes();
    }
    public static void desactivaBombes(){
        dades.desactivaBombes();
    }
    public static void activaBomba(int id){
        dades.activaBomba(id);
    }
    public static void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }
    public static String mostraSistemaRefrigeracio(){
        return dades.mostraSistemaRefrigeracio().toString();
    }

        // central
    public static String mostraEstatCentral(){
        return dades.mostraEstat().toString();
    }
    public static String mostraBitacola(){
        return dades.mostraBitacola().toString();
    }
    public static String mostraIncidencies(){
        return dades.mostraIncidencies().toString();
    }
    public static String mostraEconomia(float demandaPotencia){
        return dades.actualitzaEconomia(demandaPotencia).toString();
    }

    // tractament de dades (s'ha de modificar i completar)
    /**
     * Guarda l'estat actual de la central en un fitxer.
     *
     * @param camiDesti Ruta del fitxer on es guardarà l'estat
     * @throws CentralUBException Si hi ha un error en el procés de guardat
     */
    public static void guardaDades(CentralUB centralUB, String camiDesti) throws CentralUBException {
        File fitxer = new File(camiDesti);

        try {
            FileOutputStream fout = new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(centralUB);
            fout.close();
        } catch (IOException e) {
            throw new CentralUBException("Error al guardar l'arxiu: " + e.getMessage());
        }
    }
    /**
     * Carrega un estat de la central des d'un fitxer.
     *
     * @param camiOrigen Ruta del fitxer a carregar
     * @throws CentralUBException Si hi ha un error en el procés de càrrega
     */
    public static CentralUB carregaDades(String camiOrigen) throws CentralUBException {
        try {
            FileInputStream fin = new FileInputStream(camiOrigen);
            ObjectInputStream ois = new ObjectInputStream(fin);
            CentralUB centralCarregada = (CentralUB)ois.readObject();
            fin.close();
            return centralCarregada;
        } catch (Exception e) {
            throw new CentralUBException("Error al guardar l'arxiu");
        }
    }

}

