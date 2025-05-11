package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa un reactor dins d'una central nuclear.
 * <p>
 *     El reactor utilitza combustible radioactiu per generar energia en forma de calor,
 *     que es transmet a l’aigua del circuit primari per tal de generar energia tèrmica.
 *     El reactor es pot activar o desactivar segons la seva temperatura i el seu estat operatiu.
 * </p>
 *
 * @author Guillem Calvet
 * @author Edgar Esparza
 * @version 1.0
 * @see InComponent
 * @see CentralUBException
 * @since 1.0
 */
public class Reactor implements InComponent {

    // Atributs
    private boolean activat;
    private float temperatura;

    /**
     * Constructor per a inicialitzar el reactor. Per defecte, el reactor està desactivat
     * i no té temperatura establerta.
     */
    public Reactor() {
        this.activat = false;
        temperatura = 25;
    }

    /**
     * Activa el reactor. L'operació només es realitza si la temperatura actual no supera els 1000 graus.
     * En cas contrari, es llança una excepció de tipus {@link CentralUBException}.
     *
     * @throws CentralUBException Si la temperatura és superior a 1000 graus.
     */
    public void activa() throws CentralUBException {
        if (this.temperatura > 1000) {
            throw new CentralUBException("No es pot iniciar el generador si es supera la temperatura màxima");
        }
        this.activat = true;
    }

    /**
     * Desactiva el reactor.
     */
    public void desactiva() {
        this.activat = false;
    }

    /**
     * Retorna l'estat del reactor (activat o desactivat).
     *
     * @return {@code true} si el reactor està activat, {@code false} si està desactivat.
     */
    public boolean getActivat() {
        return activat;
    }

    /**
     * Revisa la temperatura del reactor i afegeix una incidència a la pàgina de la bitàcola si és necessari.
     * Si la temperatura és superior a 1000 graus, es desactiva el reactor i es registra una incidència.
     *
     * @param p La pàgina d'incidències on s'afegeix la descripció d'incidència.
     */
    public void revisa(PaginaIncidencies p) {
        if (temperatura > 1000) {
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");
        }
        desactiva();
    }

    /**
     * Retorna el cost operatiu del reactor.
     *
     * @return El cost operatiu del reactor en unitats econòmiques.
     */
    public float getCostOperatiu() {
        return 35;
    }

    /**
     * Calcula el "output" del reactor en funció de la temperatura actual i un valor d'entrada.
     * Si el reactor està activat, es retorna el resultat de la temperatura més una correcció basada en la diferència
     * entre 100 i el valor d'entrada multiplicada per 10.
     *
     * @param input El valor d'entrada que afecta el càlcul de l'output.
     * @return El valor de l'output calculat.
     */
    public float calculaOutput(float input) {
        if (activat) {
            return temperatura + (100 - input) * 10;
        }
        return temperatura;
    }

    /**
     * Retorna la temperatura actual del reactor.
     *
     * @return La temperatura del reactor en graus.
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * Estableix la temperatura del reactor.
     *
     * @param temperatura La temperatura a assignar al reactor en graus.
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Retorna una representació en format de text de l'estat del reactor,
     * incloent el seu estat d'activació i la temperatura.
     *
     * @return Una cadena de text amb la informació de l'estat del reactor.
     */
    @Override
    public String toString() {
        return "Activat= " + activat + ", Temperatura= " + temperatura + "\n";
    }
}
