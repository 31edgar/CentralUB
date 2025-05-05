/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades{
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    // Atributs
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;

    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }

    // Getters i Setters
    public float getInsercioBarres() {
        return this.insercioBarres;
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres < 0 || insercioBarres > 100) {
            throw new CentralUBException("No es permet fixar un grau d’inserció fora de l’interval 0-100");
        }

        this.insercioBarres = insercioBarres;
    }

    // Reactor
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    public void desactivaReactor() {
        reactor.desactiva();
    }

    public Reactor mostraReactor() {
        return this.reactor;
    }

    // Sistema de Refrigeració
    public void activaBomba(int id) throws CentralUBException {
        sistemaRefrigeracio.activaBomba(id);
    }

    public void desactivaBomba(int id) {
        sistemaRefrigeracio.desactivaBomba(id);
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return this.sistemaRefrigeracio;
    }

    // Bitàcola
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }

    public float getGuanysAcumulats() {
        return this.guanysAcumulats;
    }

    public PaginaEstat mostraEstat() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputSistemaRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGeneradorVapor = generadorVapor.calculaOutput(outputSistemaRefrigeracio);
        float outputTurbina = turbina.calculaOutput(outputGeneradorVapor);

        return new PaginaEstat(dia, insercioBarres, outputReactor, outputSistemaRefrigeracio, outputGeneradorVapor, outputTurbina);
    }

    public Bitacola mostraBitacola() {
        return bitacola;
    }

    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float potencia = calculaPotencia() * PREU_UNITAT_POTENCIA;
        float benefici = Math.min(potencia, demandaPotencia);
        float penalitzacio = (potencia > demandaPotencia) ? PENALITZACIO_EXCES_POTENCIA : 0;
        float costOperatiu = 5 + reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() + generadorVapor.getCostOperatiu() + turbina.getCostOperatiu(); // Comença a 5 per les barres de control
        float percentatgeDemandaSatisfeta = (potencia/demandaPotencia) * 100;

        // Actualitzem els guanys acumulats
        guanysAcumulats += benefici - penalitzacio - costOperatiu;

        return new PaginaEconomica(dia, demandaPotencia, benefici, percentatgeDemandaSatisfeta, potencia, penalitzacio, costOperatiu, guanysAcumulats);
    }

    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
          float temperatura = reactor.calculaOutput(insercioBarres) - sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres));
          reactor.setTemperatura(temperatura);
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
          reactor.revisa(paginaIncidencies);
          sistemaRefrigeracio.revisa(paginaIncidencies);
          generadorVapor.revisa(paginaIncidencies);
          turbina.revisa(paginaIncidencies);
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
}
