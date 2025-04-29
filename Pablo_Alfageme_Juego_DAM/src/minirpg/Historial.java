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

    public void mostrarHistorial() {
        if (partidas.isEmpty()) {
            System.out.println("No hay partidas en el historial.");
        } else {
            partidas.forEach(System.out::println);
        }
    }

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

    private void guardarPartida(Partida partida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(partida.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}
