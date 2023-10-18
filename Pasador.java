public class Pasador extends Jugador {
    private int pases;
    private int fintas;

    /**
     * Constructor de la clase Pasador.
     * @param nombre El nombre del jugador.
     * @param pais El país de origen del jugador.
     * @param errores La cantidad de errores del jugador.
     * @param aces La cantidad de aces del jugador.
     * @param totalServicios El total de servicios del jugador.
     * @param pases La cantidad de pases del pasador.
     * @param fintas La cantidad de fintas del pasador.
     */
    public Pasador(String nombre, String pais, int errores, int aces, int totalServicios, int pases, int fintas) {
        super(nombre, pais, errores, aces, totalServicios);
        this.pases = pases;
        this.fintas = fintas;
    }

    /**
     * Calcula la efectividad del pasador.
     * @return El valor de efectividad del pasador.
     */
    @Override
    public float calcularEfectividad() {
        return ((pases + fintas - getErrores()) * 100 / (pases + fintas + getErrores())) +
                (getAces() * 100 / getTotalServicios());
    }

}