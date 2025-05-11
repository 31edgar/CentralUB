package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.List;

/**
 * Aquesta classe modela les dades d'una central nuclear.
 * <p>
 *  Modela el control del reactor, sistema de refrigeració, generador de vapor,
 *  turbina i bitàcola. Permet gestionar l'estat de la central i
 *  calcular l'economia i el rendiment de la producció d'energia.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Dades
 * @see InDades
 * @see Reactor
 * @see SistemaRefrigeracio
 * @see GeneradorVapor
 * @see Turbina
 * @see Bitacola
 * @since 1.0
 */
public class Dades implements InDades, Serializable {
    // Constants
    public final static long VAR_UNIF_SEED = 123;
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

    /**
     * Constructor per inicialitzar els atributs de la classe.
     * Inicialitza els components de la central (reactor, sistema de refrigeració,
     * generador de vapor, turbina i bitàcola). Afegeix bombes de refrigeració
     * al sistema de refrigeració.
     */
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

    /**
     * Obté el grau d'inserció de les barres de control.
     * @return Grau d'inserció.
     */
    public float getInsercioBarres() {
        return this.insercioBarres;
    }

    /**
     * Estableix el grau d'inserció de les barres de control.
     * Llança una excepció si el grau d'inserció no és vàlid.
     * @param insercioBarres Grau d'inserció (entre 0 i 100).
     * @throws CentralUBException Si el grau d'inserció no és vàlid.
     */
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres < 0 || insercioBarres > 100) {
            throw new CentralUBException("No es permet fixar un grau d’inserció fora de l’interval 0-100");
        }

        this.insercioBarres = insercioBarres;
    }

    // Reactor

    /**
     * Activa el reactor.
     * @throws CentralUBException Si hi ha un problema activant el reactor.
     */
    public void activaReactor() throws CentralUBException {
        reactor.activa();
    }

    /**
     * Desactiva el reactor.
     */
    public void desactivaReactor() {
        reactor.desactiva();
    }

    /**
     * Mostra l'estat actual del reactor.
     * @return L'objecte reactor amb el seu estat actual.
     */
    public Reactor mostraReactor() {
        return this.reactor;
    }

    // Sistema de Refrigeració

    /**
     * Activa les bombes de refrigeració del sistema.
     * @throws CentralUBException Si hi ha un problema activant les bombes.
     */
    public void activaBombes() throws CentralUBException {
        sistemaRefrigeracio.activa();
    }

    /**
     * Desactiva les bombes de refrigeració del sistema.
     */
    public void desactivaBombes() {
        sistemaRefrigeracio.desactiva();
    }

    /**
     * Activa una bomba de refrigeració específica.
     * @param id Identificador de la bomba a activar.
     * @throws CentralUBException Si hi ha un problema activant la bomba.
     */
    public void activaBomba(int id) throws CentralUBException {
        sistemaRefrigeracio.activaBomba(id);
    }

    /**
     * Desactiva una bomba de refrigeració específica.
     * @param id Identificador de la bomba a desactivar.
     */
    public void desactivaBomba(int id) {
        sistemaRefrigeracio.desactivaBomba(id);
    }

    /**
     * Mostra l'estat actual del sistema de refrigeració.
     * @return L'objecte sistemaRefrigeracio amb el seu estat actual.
     */
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return this.sistemaRefrigeracio;
    }

    // Bitàcola

    /**
     * Calcula la potència generada per la central.
     * @return La potència generada per la turbina.
     */
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }

    /**
     * Calcula si la demanda de potència està satisfeta, indicant el percentatge de satisfacció.
     * @param demandaPotencia Potència demandada.
     * @return Un array amb la demanda de potència, la potència generada i el percentatge de satisfacció.
     */
    public float[] demandaSatisfeta(float demandaPotencia) {
        float potencia = calculaPotencia(), percentatge = (potencia/demandaPotencia) * 100;
        return new float[]{demandaPotencia, calculaPotencia(), percentatge};
    }

    /**
     * Obté els guanys acumulats de la central.
     * @return Els guanys acumulats.
     */
    public float getGuanysAcumulats() {
        return this.guanysAcumulats;
    }

    /**
     * Mostra l'estat actual de la central, incloent el grau d'inserció i la potència generada.
     * @return Una instància de {@link PaginaEstat} amb l'estat actual.
     */
    public PaginaEstat mostraEstat() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputSistemaRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGeneradorVapor = generadorVapor.calculaOutput(outputSistemaRefrigeracio);
        float outputTurbina = turbina.calculaOutput(outputGeneradorVapor);

        return new PaginaEstat(dia, insercioBarres, outputReactor, outputSistemaRefrigeracio, outputGeneradorVapor, outputTurbina);
    }

    /**
     * Mostra la bitàcola de la central.
     * @return L'objecte bitacola amb totes les pàgines d'incidències registrades.
     */
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    /**
     * Mostra les incidències registrades en la bitàcola.
     * @return Una llista d'incidències.
     */
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

    /**
     * Actualitza l'economia de la central, calculant els guanys, penalitzacions,
     * costos operatius i guanys acumulats. Retorna una pàgina econòmica amb els resultats.
     * @param demandaPotencia Potència demandada.
     * @return Una instància de {@link PaginaEconomica} amb els resultats econòmics actuals.
     */
    public PaginaEconomica actualitzaEconomia(float demandaPotencia){
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
     * Refrigera el reactor ajustant la seva temperatura segons el sistema de refrigeració.
     */
    private void refrigeraReactor() {
        float temperatura = reactor.calculaOutput(insercioBarres) - sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres));
        reactor.setTemperatura(Math.max(25, temperatura));
    }

    /**
     * Revisa els components de la central per detectar incidències.
     * Registra les incidències a la pàgina proporcionada.
     * @param paginaIncidencies La pàgina d'incidències a actualitzar.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        generadorVapor.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);
    }

    /**
     * Finalitza un dia de simulació, actualitzant l'economia, estat de la central,
     * incidències i refrigerant el reactor. Incrementa el dia i guarda la informació
     * de la bitàcola.
     * @param demandaPotencia Potència demandada durant el dia.
     * @return Una nova instància de {@link Bitacola} amb les pàgines generades
     *         durant el dia.
     */
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
