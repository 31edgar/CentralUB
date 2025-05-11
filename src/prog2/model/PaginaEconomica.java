package prog2.model;

import java.io.Serializable;

/**
 * Classe que representa una pàgina econòmica dins de la bitàcola de la central nuclear.
 * <p>
 *     Aquesta classe registra informació econòmica relacionada amb el funcionament de la central nuclear
 *     durant un dia específic. Inclou dades sobre la demanda de potència, la potència generada, els beneficis,
 *     les penalitzacions per excés de producció, els costos operatius i els guanys acumulats.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see Bitacola
 * @see PaginaBitacola
 * @since 1.0
 */
public class PaginaEconomica extends PaginaBitacola implements Serializable {

    // Atributs
    private float demandaPotencia;
    private float potenciaGenerada;
    private float percentatgeDemandaSatisfeta;
    private float benefici;
    private float penalitzacioExces;
    private float costosOperatius;
    private float guanysAcumulats;

    /**
     * Constructor que inicialitza una pàgina econòmica amb les dades corresponents
     * al dia, la demanda de potència, la potència generada, el percentatge de demanda satisfeta,
     * els beneficis, les penalitzacions per excés de producció, els costos operatius i els guanys acumulats.
     *
     * @param dia El dia de la pàgina econòmica.
     * @param demandaPotencia La demanda de potència actual.
     * @param potenciaGenerada La potència generada per la central.
     * @param percentatgeDemandaSatisfeta Percentatge de la demanda de potència satisfeta.
     * @param benefici Beneficis generats en aquest dia.
     * @param penalitzacioExces Penalització per excés de producció.
     * @param costosOperatius Costos operatius associats a la producció d'energia.
     * @param guanysAcumulats Guanys acumulats fins a aquest dia.
     */
    public PaginaEconomica(int dia, float demandaPotencia, float potenciaGenerada, float percentatgeDemandaSatisfeta, float benefici,
                           float penalitzacioExces, float costosOperatius, float guanysAcumulats) {
        super(dia);
        this.demandaPotencia = demandaPotencia;
        this.potenciaGenerada = potenciaGenerada;
        this.percentatgeDemandaSatisfeta = percentatgeDemandaSatisfeta;
        this.benefici = benefici;
        this.penalitzacioExces = penalitzacioExces;
        this.costosOperatius = costosOperatius;
        this.guanysAcumulats = guanysAcumulats;
    }

    // Getters
    public float getDemandaPotencia() {
        return this.demandaPotencia;
    }

    public float getPotenciaGenerada() {
        return this.potenciaGenerada;
    }

    public float getPercentatgeDemandaSatisfeta() {
        return this.percentatgeDemandaSatisfeta;
    }

    public float getBenefici() {
        return this.benefici;
    }

    public float getPenalitzacioExces() {
        return this.penalitzacioExces;
    }

    public float getCostosOperatius() {
        return this.costosOperatius;
    }

    public float getGuanysAcumulats() {
        return this.guanysAcumulats;
    }

    /**
     * Retorna una representació en format de text de la pàgina econòmica.
     * Aquesta representació inclou informació detallada sobre el dia, la demanda de potència,
     * la potència generada, la satisfacció de la demanda, els beneficis, les penalitzacions,
     * els costos operatius i els guanys acumulats.
     *
     * @return Una cadena de text amb la informació de la pàgina econòmica.
     */
    @Override
    public String toString() {
        return "# Pàgina Econòmica\n- Dia: " + super.getDia() + "\n- Demanda de Potència: " + demandaPotencia + "\n- Potència generada: " + potenciaGenerada +
                "\n- Demanda de Potència Satisfeta: " + percentatgeDemandaSatisfeta + "%\n- Beneficis: " + benefici + " Unitats Econòmiques\n- Penalització Excés Producció: " +
                penalitzacioExces + " Unitats Econòmiques\n- Cost Operatiu: " + costosOperatius + " Unitats Econòmiques\n- Guanys acumulats: " + guanysAcumulats + " Unitats Econòmiques\n";
    }
}
