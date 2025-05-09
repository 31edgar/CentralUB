package prog2.adaptador;

import java.io.*;

import prog2.model.Dades;
import prog2.vista.*;

public class Adaptador {

    // creem una instància de Dades i un atribut per a carregar una central
    private static Dades dades = new Dades();

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
    public static float[] mostraDemandaSatisfeta(float demandaPotencia){
        return dades.demandaSatisfeta(demandaPotencia);
    }

    // tractament de dades

    /**
     * Guarda l'estat actual de la central en un fitxer.
     *
     * @param camiDesti Ruta del fitxer on es guardarà l'estat
     * @throws CentralUBException Si hi ha un error en el procés de guardat
     */
    public static void guardaDades(String camiDesti) throws CentralUBException {
        File fitxer = new File(camiDesti);

        try {
            FileOutputStream fout = new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(dades);
            fout.close();
        } catch (Exception e) {
            throw new CentralUBException("No s'ha pogut guardar la central.");
        }
    }
    /**
     * Carrega un estat de la central des d'un fitxer.
     *
     * @param camiOrigen Ruta del fitxer a carregar
     * @throws CentralUBException Si hi ha un error en el procés de càrrega
     */
    public static void carregaDades(String camiOrigen) throws CentralUBException {
        try {
            FileInputStream fin = new FileInputStream(camiOrigen);
            ObjectInputStream ois = new ObjectInputStream(fin);
            dades = (Dades)ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

