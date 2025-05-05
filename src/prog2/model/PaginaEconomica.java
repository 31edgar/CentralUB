package prog2.model;

// toString

public class PaginaEconomica extends PaginaBitacola{
    // Atributs
    private float demandaPotencia;
    private float potenciaGenerada;
    private float percentatgeDemandaSatisfeta;
    private float benefici;
    private float penalitzacioExces;
    private float costosOperatius;
    private float guanysAcumulats;

    // Constructor
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

    // To String
    @Override
    public String toString() {
        return "# Pàgina Econòmica\n- Dia: " + super.getDia() + "\n- Demanda de Potència: " + demandaPotencia + "\n- Potència generada: " + potenciaGenerada +
                "\n- Demanda de Potència Satisfeta: " + percentatgeDemandaSatisfeta + "%\n- Beneficis: " + benefici + " Unitats Econòmiques\n- Penalització Excés Producció: " +
                penalitzacioExces + " Unitats Econòmiques\n- Cost Operatiu: " + costosOperatius + "Unitats Econòmiques\n- Guanys acumulats: " + guanysAcumulats + "Unitats Econòmiques\n";
    }
}
