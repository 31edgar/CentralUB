/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Menu;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    /* ---------------- MATERIAL DEL MENÚ -------------------------------------------------------------------------------------- */

    /* Opcions Menú */
    private enum OpcionsMenuPrincipal {
        GESTIO_BARRES,
        GESTIO_REACTOR,
        GESTIO_SISTEMA_REFRIGERACIO,
        MOSTRA_ESTAT_CENTRAL,
        MOSTRA_BITACOLA,
        MOSTRA_INCIDENCIES,
        OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL,
        FINALITZAR_DIA,
        GUARDAR_DADES,
        CARREGA_DADES,
        SORTIR
    }
    private enum OpcionsSubmenuBarresDeControl {
        OBTENIR_INSERCIO_BARRES,
        ESTABLIR_INSERCIO_BARRES,
        SORTIR
    }
    private enum OpcionsSubmenuReactor {
        ACTIVAR_REACTOR,
        DESACTIVAR_REACTOR,
        MOSTRAR_ESTAT,
        SORTIR
    }
    private enum OpcionsSubmenuSistemaRefrigeracio {
      ACTIVAR_TOTES_BOMBES,
      DESACTIVAR_TOTES_BOMBES,
      ACTIVAR_BOMBA,
      DESACTIVAR_BOMBA,
      MOSTRAR_ESTAT,
      SORTIR
    }

    static private String[] descMenuPrincipal = {
            "Dona pas a un submenú que permet obtenir o establir la inserció de les barres, que ha de ser un nombre entre 0 i 100.",
            "Mostra un submenú per gestionar el reactor.",
            "Mostra un submenú amb opcions per controlar el sistema de refrigeració.",
            "Mostra la pàgina de bitàcola d'estat corresponent al dia actual. Aquesta informació és provisional i només es farà efectiva al finalitzar el dia.",
            "Mostra tot el contingut de la bitàcola fins al dia actual, incloent les pàgines d'estat, econòmiques i d'incidències.",
            "Mostra totes les pàgines d'incidències de la bitàcola fins al dia actual.",
            "Mostra la demanda de potència del dia en curs, la potència generada amb la configuració de la central actual i el percentatge de demanda satisfeta corresponent.",
            "Es duen a terme totes les accions relacionades amb la finalització d'un dia.",
            "Guarda les dades de l'aplicació.",
            "Carrega les dades de l'aplicació.",
            "Surt de l'aplicació."
    };

    static private String[] descSubmenuBarresDeControl = {
      "Mostra per pantalla la inserció de les barres.",
      "Sol·licita a l'usuari el grau d'inserció de les barres.",
      "Torna al menú principal."
    };

    static private String[] descSubmenuReactor = {
      "Permet activar el reactor.",
      "Permet desactivar el reactor.",
      "Mostra si el reactor està activat i la seva temperatura.",
      "Torna al menú principal."
    };

    static private String[] descSubmenuSistemaDeRefrigeracio = {
        "Activa totes les bombes refrigerants.",
        "Desactiva totes les bombes refrigerants.",
        "Donat el seu identificador numèric (entre 0 i 3), permet activar una bomba refrigerant.",
        "Donat l'identificador numèric d'una bomba refrigerant, permet desactivar-la.",
        "Mostra l'estat actual de totes les bombes del sistema de refrigeració.",
        "Torna al menú principal."
    };

    /* ---------------- MATERIAL DEL MENÚ -------------------------------------------------------------------------------------- */
    
    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        // Afegir codi adicional si fos necessari:

    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        Scanner sc = new Scanner(System.in);

        // Creem Menú i Submenús
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menú principal", OpcionsMenuPrincipal.values());

        // Li afegim les seves respectives descripcions
        menu.setDescripcions(descMenuPrincipal);

        OpcionsMenuPrincipal opcio = null;

        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch(opcio){
                case GESTIO_BARRES:
                    try {
                        gestioMenuBarres(sc);
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case GESTIO_REACTOR:
                    try {
                        gestioMenuReactor(sc);
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case GESTIO_SISTEMA_REFRIGERACIO:
                    try {
                        gestioMenuRefrigeracio(sc);
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case MOSTRA_ESTAT_CENTRAL:
                    try {
                        Adaptador.mostraEstatCentral();
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case MOSTRA_BITACOLA:
                    try {
                        Adaptador.mostraBitacola();
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case MOSTRA_INCIDENCIES:
                    try {
                        Adaptador.mostraIncidencies();
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL:
                    try {
                        float[] llista = Adaptador.mostraDemandaSatisfeta(demandaPotencia);
                        System.out.println("- Demanda de potència actual: " + llista[0]);
                        System.out.println("- Potència generada (amb la configuració actual): " + llista[1]);
                        System.out.println("- Percentatge de demanda satisfeta: " + llista[2] + " %");
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case FINALITZAR_DIA:
                    try {
                        finalitzaDia();
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case GUARDAR_DADES:
                    try {
                        guardarDades(sc);
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case CARREGA_DADES:
                    try {
                        carregaDades(sc);
                    } catch (CentralUBException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case SORTIR:
                    System.out.println("Retornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
            }
        } while (opcio != OpcionsMenuPrincipal.SORTIR);
    }

    /* -------------- GESTIÓ DE SUBMENUS ---------------------------------------------------------------------------- */

    private void gestioMenuBarres(Scanner sc) throws CentralUBException{
        // Creem el submenu
        Menu<OpcionsSubmenuBarresDeControl> menu = new Menu<>("Gestió Barres de Control", OpcionsSubmenuBarresDeControl.values());

        // Li assignem la descripcio de les opcions
        menu.setDescripcions(descSubmenuBarresDeControl);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuBarresDeControl opcio = null;
        do {
            // Mostrem opcions
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case OBTENIR_INSERCIO_BARRES:
                    System.out.println("Inserció actual de les barres: " + Adaptador.getInsercioBarres());
                    break;
                case ESTABLIR_INSERCIO_BARRES:
                    System.out.println("Escriu el grau d'inserció de les barres (0-100): ");
                    Adaptador.setInsercioBarres(sc.nextFloat());
                    break;
                case SORTIR:
                    System.out.println("Retornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
            }
        } while (opcio != OpcionsSubmenuBarresDeControl.SORTIR);
    }

    private void gestioMenuReactor(Scanner sc) throws CentralUBException{
        // Creem el submenu
        Menu<OpcionsSubmenuReactor> menu = new Menu<>("Gestió Reactor", OpcionsSubmenuReactor.values());

        // Li assignem la descripcio de les opcions
        menu.setDescripcions(descSubmenuReactor);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuReactor opcio = null;
        do {
            // Mostrem opcions
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case ACTIVAR_REACTOR:
                    System.out.println("Activant reactor...");
                    Adaptador.activaReactor();
                    break;
                case DESACTIVAR_REACTOR:
                    System.out.println("Desactivant reactor...");
                    Adaptador.desactivaReactor();
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println("Estat del reactor: " + Adaptador.mostraReactor());
                    break;
                case SORTIR:
                    System.out.println("Retornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
            }
        } while (opcio != OpcionsSubmenuReactor.SORTIR);
    }

    private void gestioMenuRefrigeracio(Scanner sc) throws CentralUBException{
        // Creem el submenu
        Menu<OpcionsSubmenuSistemaRefrigeracio> menu = new Menu<>("Gestió Sistema Refrigeració", OpcionsSubmenuSistemaRefrigeracio.values());

        // Li assignem la descripcio de les opcions
        menu.setDescripcions(descSubmenuSistemaDeRefrigeracio);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuSistemaRefrigeracio opcio = null;
        do {
            // Mostrem opcions
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case ACTIVAR_TOTES_BOMBES:
                    System.out.println("Activant totes les bombes...");
                    Adaptador.activaBombes();
                    break;
                case DESACTIVAR_TOTES_BOMBES:
                    System.out.println("Desactivant totes les bombes...");
                    Adaptador.desactivaBombes();
                    break;
                case ACTIVAR_BOMBA:
                    System.out.println("Id de la bomba a activar: ");
                    Adaptador.activaBomba(sc.nextInt());
                    break;
                case DESACTIVAR_BOMBA:
                    System.out.println("Id de la bomba a desactivar: ");
                    Adaptador.desactivaBomba(sc.nextInt());
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println("Estat de les bombes:\n" + Adaptador.mostraSistemaRefrigeracio());
                    break;
                case SORTIR:
                    System.out.println("Retornant al menú principal...");
                    break;
            }
        } while (opcio != OpcionsSubmenuSistemaRefrigeracio.SORTIR);
    }

    private void guardarDades(Scanner sc) throws CentralUBException{
        Adaptador.guardaDades(sc.nextLine());
    }

    private void carregaDades(Scanner sc) throws CentralUBException{
        Adaptador.carregaDades(sc.nextLine());
    }

    /* -------------- GESTIÓ DE SUBMENUS ---------------------------------------------------------------------------- */

    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = Adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
}