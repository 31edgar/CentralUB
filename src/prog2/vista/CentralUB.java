/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;

import java.util.*;

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
        menu();
    }
    
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
        String info;
        info = Adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }

    private void menu() {
        // Menu
        Scanner scanner = new Scanner(System.in);
        int opcio = 0;
        int subOpcio = 0;

        while (opcio != 11) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestió Barres de Control");
            System.out.println("2. Gestió Reactor");
            System.out.println("3. Gestió Sistema Refrigeració");
            System.out.println("4. Mostrar Estat Central");
            System.out.println("5. Mostrar Bitàcola");
            System.out.println("6. Mostrar Incidències");
            System.out.println("7. Obtenir Demanda Satisfeta amb Configuració Actual");
            System.out.println("8. Finalitzar Dia");
            System.out.println("9. Guardar Dades");
            System.out.println("10. Carrega Dades");
            System.out.println("11. Sortir");

            try {
                opcio = scanner.nextInt();
            } catch (InputMismatchException e) {
                // netegem la entrada (el cas default del switch tira el missatge)
                scanner.nextLine();
                opcio = -1;
            }

            switch (opcio) {
                case 1:
                    while (subOpcio != 3) {
                        System.out.println("\n - Menú barres de Control: ");
                        System.out.println("1. Obtenir Inserció Barres");
                        System.out.println("2. Establir Inserció Barres");
                        System.out.println("3. Sortir");

                        try {
                            subOpcio = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // netegem la entrada (el cas default del switch tira el missatge)
                            scanner.nextLine();
                            subOpcio = -1;
                        }

                        switch (subOpcio) {
                            case 1:
                                System.out.println("\nInserció actual de les barres: " + Adaptador.getInsercioBarres());
                                break;
                            case 2:
                                System.out.println("\nEscriu el grau d'inserció de les barres (0-100): ");
                                Adaptador.setInsercioBarres(scanner.nextInt());
                                break;
                            case 3:
                                System.out.println("Retornant al menú principal...");
                                break;
                            default:
                                System.out.println("Opció no vàlida. Torna-ho a provar.");
                        }
                    }
                    subOpcio = -1;
                    break;
                case 2:
                    while (subOpcio != 4) {
                        System.out.println("\n - Menú reactor: ");
                        System.out.println("1. Activar Reactor");
                        System.out.println("2. Desactivar Reactor");
                        System.out.println("3. Mostrar Estat");
                        System.out.println("4. Sortir");

                        try {
                            subOpcio = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // netegem la entrada (el cas default del switch tira el missatge)
                            scanner.nextLine();
                            subOpcio = -1;
                        }

                        switch (subOpcio) {
                            case 1:
                                System.out.println("Activant reactor..."); Adaptador.activaReactor();
                                break;
                            case 2:
                                System.out.println("Desactivant reactor..."); Adaptador.desactivaReactor();
                                break;
                            case 3:
                                System.out.println("Estat del reactor: " + Adaptador.mostraReactor());
                                break;
                            case 4:
                                System.out.println("Retornant al menú principal...");
                                break;
                            default:
                                System.out.println("Opció no vàlida. Torna-ho a provar.");
                        }
                    }
                    subOpcio = -1;
                    break;
                case 3:
                    while (subOpcio != 6) {
                        System.out.println("\n - Menú reactor: ");
                        System.out.println("1. Activar Totes les Bombes");
                        System.out.println("2. Desactivar Totes les Bombes");
                        System.out.println("3. Activar Bomba");
                        System.out.println("4. Desactivar Bomba");
                        System.out.println("5. Mostrar Estat");
                        System.out.println("6. Sortir");

                        try {
                            subOpcio = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // netegem la entrada (el cas default del switch tira el missatge)
                            scanner.nextLine();
                            subOpcio = -1;
                        }

                        switch (subOpcio) {
                            case 1:
                                System.out.println("Activant totes les bombes..."); Adaptador.activaBombes();
                                break;
                            case 2:
                                System.out.println("Desactivant totes les bombes..."); Adaptador.desactivaBombes();
                                break;
                            case 3:
                                System.out.println("Id de la bomba a activar: ");
                                Adaptador.activaBomba(scanner.nextInt());
                                break;
                            case 4:
                                System.out.println("Id de la bomba a desactivar: ");
                                Adaptador.desactivaBomba(scanner.nextInt());
                                break;
                            case 5:
                                System.out.println("Estat del reactor: " + Adaptador.mostraSistemaRefrigeracio());
                                break;
                            case 6:
                                System.out.println("Retornant al menú principal...");
                                break;
                            default:
                                System.out.println("Opció no vàlida. Torna-ho a provar.");
                        }
                    }
                    subOpcio = -1;
                    break;
                case 4:
                    System.out.println("Mostrant estat actual de la central: \n" + Adaptador.mostraEstatCentral());
                    break;
                case 5:
                    System.out.println("Mostrant bitàcola actual de la central: \n" + Adaptador.mostraBitacola());
                    break;
                case 6:
                    System.out.println("Mostrant incidències actuals de la central: \n" + Adaptador.mostraIncidencies());
                    break;
                case 7:
                    System.out.println("Mostrant estat econòmic d'avui... \n" + Adaptador.mostraEconomia(demandaPotencia));
                    break;
                case 8:
                    finalitzaDia();
                    break;
                case 9:
                    System.out.println("Senyala on vols guardar l'arxiu: ");
                    // per completar
                    System.out.println("Dades de la central guardades correctament.");
                    break;
                case 10:
                    System.out.println("Senyala la ruta de l'arxiu que vols carregar: ");
                    // per completar
                    System.out.println("Central carregada correctament.");
                    break;
                case 11:
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
            }

        }

    }
}

