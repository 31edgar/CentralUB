package prog2.model;

public class PaginaEstat extends PaginaBitacola {
    // Atributs
    private float insercioBarres;
    private float outputReactor;
    private float outputSistemaRefrigeracio;
    private float outputGeneradorVapor;
    private float outputTurbina;

    // Constructor
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

    // To String
    @Override
    public String toString() {
        return "# Pàgina Estat\n- Dia: " + super.getDia() + "\n- Inserció Barres: " + insercioBarres + " %\n- Output Reactor: " + outputReactor +
                " Graus\n- Output Sistema de Refrigeració: " + outputSistemaRefrigeracio + " Graus\n- Output Generador de Vapor: " + outputGeneradorVapor +
                " Graus\n- Output Turbina: " + outputTurbina + " Unitats de potència\n";
    }
}
