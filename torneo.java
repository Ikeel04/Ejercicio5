import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Torneo {
    private List<Jugador> jugadoresInscritos;

    /**
     * Constructor de la clase Torneo.
     */
    public Torneo() {
        jugadoresInscritos = new ArrayList<>();
    }

    /**
     * Agrega un jugador a la lista de jugadores inscritos.
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        jugadoresInscritos.add(jugador);
    }

    /**
     * Muestra todos los jugadores inscritos en el torneo.
     */
    public void mostrarJugadoresInscritos() {
        for (Jugador jugador : jugadoresInscritos) {
            System.out.println(jugador.getNombre());
        }
    }

    /**
     * Muestra los 3 mejores líberos en función de su efectividad.
     */
    public void mostrarTopLibreros() {
        List<Libero> liberos = new ArrayList<>();
        for (Jugador jugador : jugadoresInscritos) {
            if (jugador instanceof Libero) {
                liberos.add((Libero) jugador);
            }
        }

        Collections.sort(libreros, Comparator.comparing(Libero::calcularEfectividad).reversed());

        int contador = 0;
        for (Libero libero : liberos) {
            if (contador < 3) {
                System.out.println(libero.getNombre() + " - Efectividad: " + libero.calcularEfectividad());
                contador++;
            } else {
                break;
            }
        }
    }

    /**
     * Cuenta la cantidad de pasadores con más del 80% de efectividad.
     * @return La cantidad de pasadores efectivos.
     */
    public int contarPasadoresEfectivos() {
        int contador = 0;
        for (Jugador jugador : jugadoresInscritos) {
            if (jugador instanceof Pasador && jugador.calcularEfectividad() > 80.0f) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Guarda el catálogo de jugadores en un archivo CSV.
     * @param fileName Nombre del archivo CSV.
     */
    public void guardarCatalogoCSV(String fileName) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            for (Jugador jugador : jugadoresInscritos) {
                String[] data = jugadorToCSV(jugador);
                writer.writeNext(data);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al guardar el catálogo en el archivo CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Carga el catálogo de jugadores desde un archivo CSV.
     * @param fileName Nombre del archivo CSV.
     */
    public void cargarCatalogoCSV(String fileName) {
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Jugador jugador = jugadorFromCSV(nextLine);
                if (jugador != null) {
                    jugadoresInscritos.add(jugador);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el catálogo desde el archivo CSV.");
            e.printStackTrace();
        }
    }

    // Convierte un objeto Jugador a una representación en CSV
    private String[] jugadorToCSV(Jugador jugador) {
        if (jugador instanceof Libero) {
            Libero libero = (Libero) jugador;
            return new String[] {
                "Libero",
                jugador.getNombre(),
                jugador.getPais(),
                String.valueOf(jugador.getErrores()),
                String.valueOf(jugador.getAces()),
                String.valueOf(jugador.getTotalServicios()),
                String.valueOf(libero.getRecibosEfectivos())
            };
        } else if (jugador instanceof Pasador) {
            Pasador pasador = (Pasador) jugador;
            return new String[] {
                "Pasador",
                jugador.getNombre(),
                jugador.getPais(),
                String.valueOf(jugador.getErrores()),
                String.valueOf(jugador.getAces()),
                String.valueOf(jugador.getTotalServicios()),
                String.valueOf(pasador.getPases()),
                String.valueOf(pasador.getFintas())
            };
        } else if (jugador instanceof AuxiliarOpuesto) {
            AuxiliarOpuesto auxiliarOpuesto = (AuxiliarOpuesto) jugador;
            return new String[] {
                "AuxiliarOpuesto",
                jugador.getNombre(),
                jugador.getPais(),
                String.valueOf(jugador.getErrores()),
                String.valueOf(jugador.getAces()),
                String.valueOf(jugador.getTotalServicios()),
                String.valueOf(auxiliarOpuesto.getAtaques()),
                String.valueOf(auxiliarOpuesto.getBloqueosEfectivos()),
                String.valueOf(auxiliarOpuesto.getBloqueosFallidos())
            };
        }
        return null;
    }

    // Convierte una representación en CSV a un objeto Jugador
    private Jugador jugadorFromCSV(String[] data) {
        if (data.length < 7) {
            return null; // Datos incompletos
        }
        String tipo = data[0];
        String nombre = data[1];
        String pais = data[2];
        int errores = Integer.parseInt(data[3]);
        int aces = Integer.parseInt(data[4]);
        int totalServicios = Integer.parseInt(data[5]);

        switch (tipo) {
            case "Libero":
                int recibosEfectivos = Integer.parseInt(data[6]);
                return new Libero(nombre, pais, errores, aces, totalServicios, recibosEfectivos);
            case "Pasador":
                int pases = Integer.parseInt(data[6]);
                int fintas = Integer.parseInt(data[7]);
                return new Pasador(nombre, pais, errores, aces, totalServicios, pases, fintas);
            case "AuxiliarOpuesto":
                int ataques = Integer.parseInt(data[6]);
                int bloqueosEfectivos = Integer.parseInt(data[7]);
                int bloqueosFallidos = Integer.parseInt(data[8]);
                return new AuxiliarOpuesto(nombre, pais, errores, aces, totalServicios, ataques, bloqueosEfectivos, bloqueosFallidos);
            default:
                return null;
    }
}