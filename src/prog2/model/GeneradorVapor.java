package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa un generador de vapor en una central nuclear.
 * <p>
 *     El generador de vapor transmet calor del circuit d’aigua primari al circuit d’aigua secundari,
 *     permetent que l’aigua en estat líquid del circuit secundari es converteixi en vapor.
 *     Quan es transmet calor del circuit primari al secundari, la temperatura de l’aigua del circuit primari disminueix.
 * </p>
 * <p>
 *     El generador de vapor pot estar activat o desactivat. Quan està activat, el generador de vapor calcula l'output
 *     com un 90% de l'input. Si està desactivat, l'output serà un valor fix representant la temperatura ambient.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InComponent
 * @see PaginaIncidencies
 * @since 1.0
 */
public class GeneradorVapor implements InComponent {

    // Atributs
    private boolean activat;

    // Constructor
    public GeneradorVapor() {
        this.activat = true;
    }

    /**
     * Activa el generador de vapor. Un cop activat, el generador transmet calor del circuit primari al secundari.
     * @throws CentralUBException Si no es pot activar el generador de vapor.
     */
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    /**
     * Desactiva el generador de vapor. Quan es desactiva, el generador no transmet calor.
     */
    public void desactiva() {
        this.activat = false;
    }

    /**
     * Retorna l'estat actual del generador de vapor.
     * @return `true` si el generador està activat, `false` si està desactivat.
     */
    public boolean getActivat() {
        return activat;
    }

    /**
     * Revisa l'estat del generador de vapor i, si està desactivat, registra una incidència.
     * @param p La pàgina d'incidències on s'afegiran els errors.
     */
    public void revisa(PaginaIncidencies p) {
        if (!this.getActivat()) {
            p.afegeixIncidencia("Generador de Vapor fora de servei");
        }
    }

    /**
     * Retorna el cost operatiu del generador de vapor.
     * @return El cost operatiu del generador de vapor.
     */
    public float getCostOperatiu() {
        return 25;
    }

    /**
     * Calcula l'output del generador de vapor basant-se en l'input rebut.
     * Si el generador està activat, l'output serà el 90% de l'input.
     * Si el generador està desactivat, l'output serà 25 (temperatura ambient).
     * @param input El valor d'entrada per al càlcul de l'output (generalment temperatura de l'aigua).
     * @return El valor de l'output del generador de vapor.
     */
    public float calculaOutput(float input) {
        if (activat) {
            return (float) (input * 0.9);  // Output quan està activat
        } else {
            return 25;  // Temperatura ambient quan està desactivat
        }
    }
}
