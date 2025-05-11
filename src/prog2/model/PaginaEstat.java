package prog2.model;

import java.io.Serializable;

/**
 * Classe que representa una pàgina d'estat dins de la bitàcola de la central nuclear.
 * <p>
 *     Aquesta classe registra informació relacionada amb l'estat de la central durant un dia específic,
 *     incloent dades sobre la inserció de barres, els outputs del reactor, el sistema de refrigeració,
 *     el generador de vapor i la turbina.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Bitacola
 * @see PaginaBitacola
 * @since 1.0
 * @see PaginaBitacola
 */
public class PaginaEstat extends PaginaBitacola implements Serializable {

    // Atributs
    private float insercioBarres;
    private float outputReactor;
    private float outputSistemaRefrigeracio;
    private float outputGeneradorVapor;
    private float outputTurbina;

    /**
     * Constructor que inicialitza una pàgina d'estat amb les dades corresponents
     * al dia, la inserció de barres, els outputs del reactor, sistema de refrigeració,
     * generador de vapor i turbina.
     *
     * @param dia El dia de la pàgina d'estat.
     * @param insercioBarres El percentatge de inserció de barres.
     * @param outputReactor L'output del reactor en graus.
     * @param outputSistemaRefrigeracio L'output del sistema de refrigeració en graus.
     * @param outputGeneradorVapor L'output del generador de vapor en graus.
     * @param outputTurbina L'output de la turbina en unitats de potència.
     */
    public PaginaEstat(int dia, float insercioBarres, float outputReactor, float outputSistemaRefrigeracio, float outputGeneradorVapor, float outputTurbina) {
        super(dia);
        this.insercioBarres = insercioBarres;
        this.outputReactor = outputReactor;
        this.outputSistemaRefrigeracio = outputSistemaRefrigeracio;
        this.outputGeneradorVapor = outputGeneradorVapor;
        this.outputTurbina = outputTurbina;
    }

    // Getters
    public float getInsercioBarres() {
        return this.insercioBarres;
    }

    public float getOutputReactor() {
        return this.outputReactor;
    }

    public float getOutputSistemaRefrigeracio() {
        return this.outputSistemaRefrigeracio;
    }

    public float getOutputGeneradorVapor() {
        return this.outputGeneradorVapor;
    }

    public float getOutputTurbina() {
        return this.outputTurbina;
    }

    /**
     * Retorna una representació en format de text de la pàgina d'estat.
     * Aquesta representació inclou informació detallada sobre el dia, la inserció de barres,
     * i els outputs del reactor, sistema de refrigeració, generador de vapor i turbina.
     *
     * @return Una cadena de text amb la informació de la pàgina d'estat.
     */
    @Override
    public String toString() {
        return "# Pàgina Estat\n- Dia: " + super.getDia() + "\n- Inserció Barres: " + insercioBarres + " %\n- Output Reactor: " + outputReactor +
                " Graus\n- Output Sistema de Refrigeració: " + outputSistemaRefrigeracio + " Graus\n- Output Generador de Vapor: " + outputGeneradorVapor +
                " Graus\n- Output Turbina: " + outputTurbina + " Unitats de potència\n";
    }
}
