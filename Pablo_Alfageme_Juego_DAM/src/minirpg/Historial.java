package minirpg;

import minirpg.partida.Partida;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Partida> partidas = new ArrayList<>();
    private static final String ARCHIVO = "historial_partidas.txt";

    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        guardarPartida(partida);
    }

    /**
     * Muestra el historial de partidas. Si no hay partidas en el historial,
     * se muestra un mensaje informando de que no hay partidas. En caso
     * contrario, se muestra cada una de las partidas, con su fecha y hora
     * de inicio, duraci n, jugador, resultado y puntos logrados.
     */
    public void mostrarHistorial() {
        if (partidas.isEmpty()) {
            System.out.println("No hay partidas en el historial.");
        } else {
            partidas.forEach(System.out::println);
        }
    }

    /**
     * Carga el historial de partidas desde un archivo de texto.
     * El archivo se encuentra en la ruta especificada por la constante ARCHIVO.
     * El archivo debe contener una partida por l nea, con el formato:
     * <ul>
     * <li>Fecha y hora de inicio en formato "yyyy-MM-dd HH:mm:ss"</li>
     * <li>Duraci n de la partida en formato "HH:mm:ss"</li>
     * <li>Nombre del jugador</li>
     * <li>"GANO" si el jugador gan  o "PERDIO" en caso contrario</li>
     * <li>N mero de puntos logrados</li>
     * </ul>
     * Si el archivo no existe o no se puede leer, se muestra un mensaje de error.
     */
    public void cargarHistorial() {
        partidas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Partida partida = Partida.fromString(linea); // asegúrate de tener este método implementado
                partidas.add(partida);
            }
            System.out.println("Historial cargado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el historial: " + e.getMessage());
        }
    }

    /**
     * Guarda una partida en el archivo de historial.
     * El archivo se especifica por la constante ARCHIVO y se abre en modo de
     * adición para no sobrescribir las partidas existentes. Cada partida se
     * guarda en una nueva línea utilizando su representación de texto.
     * Si ocurre un error de escritura, se muestra un mensaje de error.
     *
     * @param partida la partida que se va a guardar en el archivo
     */

    private void guardarPartida(Partida partida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(partida.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}
