import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Jugador {
   private String nombre;
   private String pais;
   private int errores;
   private int aces;
   private int totalServicios;

   /**
    * Constructor de la clase Jugador.
    * @param nombre El nombre del jugador.
    * @param pais El país de origen del jugador.
    * @param errores La cantidad de errores del jugador.
    * @param aces La cantidad de aces del jugador.
    * @param totalServicios El total de servicios del jugador.
    */
   public Jugador(String nombre, String pais, int errores, int aces, int totalServicios) {
       this.nombre = nombre;
       this.pais = pais;
       this.errores = errores;
       this.aces = aces;
       this.totalServicios = totalServicios;
   }
}