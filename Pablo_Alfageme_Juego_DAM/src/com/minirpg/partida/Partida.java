package com.minirpg.partida;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registra los datos de una partida jugada.
 */
public class Partida {
    private String jugador;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private boolean gano;
    private int puntos;

    /**
     * Crea un registro de partida.
     * @param jugador Nombre del jugador
     */
    public Partida(String jugador) {
        this.jugador = jugador;
        this.inicio = LocalDateTime.now();
    }

    public Partida(LocalDateTime inicio, LocalDateTime fin, String personaje, String resultado, int puntos) {
        this.inicio = inicio;
        this.fin = fin;
        this.jugador = personaje;
        this.gano = resultado.equalsIgnoreCase("gano");
        this.puntos = puntos;
    }

    /**
     * Marca el final de la partida y asigna resultado.
     * @param gano true si ganó, false si perdió
     * @param puntos puntos obtenidos
     */
    public void finalizar(boolean gano, int puntos) {
        this.fin = LocalDateTime.now();
        this.gano = gano;
        this.puntos = puntos;
    }

    public String getFecha() {
        return inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public LocalDateTime convertirFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        return LocalDateTime.parse(fecha, formatter);
    }

    public String getDuracion() {
        Duration d = Duration.between(inicio, fin);
        long h = d.toHours();
        long m = d.toMinutesPart();
        long s = d.toSecondsPart();
        return String.format("%02d:%02d:%02d", h, m, s);
    }
    public String calcularFechaFin() {
        Duration d = Duration.between(inicio, fin);
        long h = d.toHours();
        long m = d.toMinutesPart();
        long s = d.toSecondsPart();
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    // Getters para campos privados
    public String getJugador() {
        return jugador;
    }

    public boolean isGano() {
        return gano;
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return String.join(",", getFecha(), getDuracion(), jugador, (gano?"GANO":"PERDIO"), String.valueOf(puntos));
    }
}
