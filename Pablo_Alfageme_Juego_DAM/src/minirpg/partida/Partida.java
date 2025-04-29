package minirpg.partida;

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

    public Partida(String jugador) {
        this.jugador = jugador;
        this.inicio = LocalDateTime.now();
    }

    public Partida(LocalDateTime inicio, LocalDateTime fin, String jugador, String resultado, int puntos) {
        this.inicio = inicio;
        this.fin = fin;
        this.jugador = jugador;
        this.gano = resultado.equalsIgnoreCase("gano");
        this.puntos = puntos;
    }

   public void finalizar(int puntos) {
    this.fin = LocalDateTime.now();
    this.puntos = puntos;
    this.gano = puntos > 100;
}


    public String getFecha() {
        return inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getDuracion() {
        if (fin == null)
            return "En curso";
        Duration d = Duration.between(inicio, fin);
        long h = d.toHours();
        long m = d.toMinutesPart();
        long s = d.toSecondsPart();
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public String getJugador() {
        return jugador;
    }

    public boolean isGano() {
        return gano;
    }

    public int getPuntos() {
        return puntos;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return String.join(",", getFecha(), getDuracion(), jugador, (gano ? "GANO" : "PERDIO"), String.valueOf(puntos));
    }

    public static Partida fromString(String data) {
        String[] partes = data.split(",");
        if (partes.length != 5) {
            throw new IllegalArgumentException("Formato inválido de partida: " + data);
        }
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(partes[0], formatter);
    
        // Parsear duración
        String[] durParts = partes[1].split(":");
        int horas = Integer.parseInt(durParts[0]);
        int minutos = Integer.parseInt(durParts[1]);
        int segundos = Integer.parseInt(durParts[2]);
        Duration duracion = Duration.ofHours(horas).plusMinutes(minutos).plusSeconds(segundos);
        LocalDateTime fin = inicio.plus(duracion);
    
        String jugador = partes[2];
        String resultado = partes[3];
        int puntos = Integer.parseInt(partes[4]);
    
        return new Partida(inicio, fin, jugador, resultado, puntos);
    }
    

}
