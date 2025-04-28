package com.minirpg.basedatos;

import com.minirpg.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.minirpg.partida.Partida;

public class PartidasDB {

        public static void insertarPartida(Partida partida) {
            String sql = "INSERT INTO partidas (fecha, duracion, jugador, resultado, puntos) VALUES (?, ?, ?, ?, ?)";
    
            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement st = conn.prepareStatement(sql)) {
    
                    st.setTimestamp(1,Timestamp.valueOf(partida.getFecha()));
                    st.setTimestamp(2, Timestamp.valueOf(partida.getDuracion()));
                    st.setString(3, partida.getJugador());
                    st.setString(4, partida.isGano() ? "GANO" : "PERDIO");
                    st.setInt(5, partida.getPuntos());
                    st.executeUpdate();
    
            } catch (SQLException e) {
                System.out.println("Error al insertar Partida: " + e.getMessage());
            }
        }

          public static List<Partida> listarPartida (){
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidas;";

        try(Connection conn =ConexionBD.obtenerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
                while (rs.next()){
                    // int id = rs.getInt("id");
                    LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                    LocalDateTime duracion = rs.getTimestamp("duracion").toLocalDateTime();
                    String personaje = rs.getString("personaje");
                    String resultado = rs.getString("resultado");
                    int puntos = rs.getInt("puntos");

                    Partida partida = new Partida (fecha,duracion,personaje,resultado,puntos);

                    lista.add((partida));

                }

        }catch(SQLException e){
            System.out.println("Error al ejecutar"+e.getMessage());

        }
        return lista;
    }
        
}
