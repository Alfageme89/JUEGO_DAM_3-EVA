package minirpg.basedatos;

import minirpg.partida.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;

public class PartidasDB {

    /**
     * Inserta una partida en la base de datos.
     * @param partida la Partida a insertar
     */
    public static void insertarPartida(Partida partida) {
        String sql = "INSERT INTO partidas (fecha, duracion, jugador, resultado, puntos) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setTimestamp(1, Timestamp.valueOf(partida.getInicio()));
            st.setLong(2, partida.getDuracion() != null ? partida.getDuracion().getSeconds() : 0);
            st.setString(3, partida.getJugador());
            st.setString(4, partida.isGano() ? "GANO" : "PERDIO");
            st.setInt(5, partida.getPuntos());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar Partida: " + e.getMessage());
        }
    }

    /**
     * Muestra la lista de partidas guardadas en la base de datos.
     * <p>
     * La lista se muestra en la consola con el formato:
     * <pre>
     * --Partidas--
     * fecha, duracion, jugador, resultado, puntos
     * ...
     * </pre>
     * <p>
     * Si ocurre un error al ejecutar la consulta, se muestra un mensaje de error.
     */
    public static void listarPartida() {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidas";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                Duration duracion = Duration.ofSeconds(rs.getLong("duracion"));
                String jugador = rs.getString("jugador");
                String resultado = rs.getString("resultado");
                int puntos = rs.getInt("puntos");

                boolean gano = resultado.equalsIgnoreCase("GANO");
                Partida partida = new Partida(fecha, duracion, jugador, gano, puntos);

                lista.add(partida);
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar SELECT: " + e.getMessage());
        }
        System.out.println("--Partidas--");
        lista.forEach(partida -> System.out.println(partida.toString()));

    }

    

}
