public class Libero extends Jugador {
    private int recibosEfectivos;

    /**
     * Constructor de la clase Libero.
     * @param nombre El nombre del jugador.
     * @param pais El país de origen del jugador.
     * @param errores La cantidad de errores del jugador.
     * @param aces La cantidad de aces del jugador.
     * @param totalServicios El total de servicios del jugador.
     * @param recibosEfectivos La cantidad de recibos efectivos del líbero.
     */
    public Libero(String nombre, String pais, int errores, int aces, int totalServicios, int recibosEfectivos) {
        super(nombre, pais, errores, aces, totalServicios);
        this.recibosEfectivos = recibosEfectivos;
    }

    /**
     * Calcula la efectividad del líbero.
     * @return El valor de efectividad del líbero.
     */
    @Override
    public float calcularEfectividad() {
        return ((recibosEfectivos - getErrores()) * 100 / (recibosEfectivos + getErrores())) +
                (getAces() * 100 / getTotalServicios());
    }
}
