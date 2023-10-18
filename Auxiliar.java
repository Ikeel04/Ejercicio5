public class AuxiliarOpuesto extends Jugador {
    private int ataques;
    private int bloqueosEfectivos;
    private int bloqueosFallidos;

    /**
     * Constructor de la clase AuxiliarOpuesto.
     * @param nombre El nombre del jugador.
     * @param pais El país de origen del jugador.
     * @param errores La cantidad de errores del jugador.
     * @param aces La cantidad de aces del jugador.
     * @param totalServicios El total de servicios del jugador.
     * @param ataques La cantidad de ataques del auxiliar/opuesto.
     * @param bloqueosEfectivos La cantidad de bloqueos efectivos del auxiliar/opuesto.
     * @param bloqueosFallidos La cantidad de bloqueos fallidos del auxiliar/opuesto.
     */
    public AuxiliarOpuesto(String nombre, String pais, int errores, int aces, int totalServicios,
                          int ataques, int bloqueosEfectivos, int bloqueosFallidos) {
        super(nombre, pais, errores, aces, totalServicios);
        this.ataques = ataques;
        this.bloqueosEfectivos = bloqueosEfectivos;
        this.bloqueosFallidos = bloqueosFallidos;
    }

    /**
     * Calcula la efectividad del auxiliar/opuesto.
     * @return El valor de efectividad del auxiliar/opuesto.
     */
    @Override
    public float calcularEfectividad() {
        return ((ataques + bloqueosEfectivos - bloqueosFallidos - getErrores()) * 100 /
                (ataques + bloqueosEfectivos + bloqueosFallidos + getErrores())) +
                (getAces() * 100 / getTotalServicios());
    }

}