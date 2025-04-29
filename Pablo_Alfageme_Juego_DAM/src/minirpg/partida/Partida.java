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
    private Duration duracion;
    private boolean gano;
    private int puntos;

    public Partida(String jugador) {
        this.jugador = jugador;
        this.inicio = LocalDateTime.now();
    }

    public Partida(LocalDateTime inicio, Duration duracion, String jugador, boolean gano, int puntos) {
        this.inicio = inicio;
        this.duracion = duracion;
        this.jugador = jugador;
        this.gano = gano;
        this.puntos = puntos;
    }


    /**
     * Finaliza la partida y registra el n mero de puntos logrados.
     * @param puntos el n mero de puntos logrados
     */
    public void finalizar(int puntos) {
        LocalDateTime fin = LocalDateTime.now();
        this.duracion = Duration.between(inicio, fin);
        this.puntos = puntos;
        this.gano = puntos > 100;
    }


    /**
     * Devuelve la fecha y hora en que se inici  la partida.
     * @return la fecha y hora de inicio de la partida
     */
    public LocalDateTime getInicio() {
        return inicio;
    }

    /**
     * Devuelve la duración de la partida.
     * @return la duración de la partida como un objeto Duration
     */
    public Duration getDuracion() {
        return duracion;
    }

    /**
     * Devuelve el nombre del jugador que jug  esta partida.
     * @return el nombre del jugador
     */
    public String getJugador() {
        return jugador;
    }

    /**
     * Devuelve si el jugador ganó la partida.
     * @return true si el jugador ganó, false en caso contrario
     */
    public boolean isGano() {
        return gano;
    }

    /**
     * Devuelve el n mero de puntos logrados en la partida.
     * @return el n mero de puntos logrados
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Devuelve la fecha y hora de inicio de la partida en formato "yyyy-MM-dd HH:mm:ss".
     * @return la fecha y hora de inicio de la partida formateada
     */
    public String getFechaFormateada() {
        return inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Devuelve la duración de la partida en formato "HH:mm:ss", o "En curso" si no se ha finalizado.
     * @return la duración de la partida formateada
     */
    public String getDuracionFormateada() {
        if (duracion == null) return "En curso";
        long h = duracion.toHours();
        long m = duracion.toMinutesPart();
        long s = duracion.toSecondsPart();
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    /**
     * Devuelve una representaci n de texto de la partida, compuesta por:
     * <ul>
     * <li>Fecha y hora de inicio en formato "yyyy-MM-dd HH:mm:ss"</li>
     * <li>Duraci n de la partida en formato "HH:mm:ss", o "En curso" si no se ha finalizado</li>
     * <li>Nombre del jugador</li>
     * <li>"GANO" si el jugador gan  o "PERDIO" en caso contrario</li>
     * <li>N mero de puntos logrados</li>
     * </ul>
     * @return una representaci n de texto de la partida
     */
    @Override
    public String toString() {
        return String.join(",", getFechaFormateada(), getDuracionFormateada(), jugador, (gano ? "GANO" : "PERDIO"), String.valueOf(puntos));
    }

    /**
     * Crea una Partida a partir de una cadena de texto.
     * La cadena debe tener el siguiente formato:
     * <ul>
     * <li>Fecha y hora de inicio en formato "yyyy-MM-dd HH:mm:ss"</li>
     * <li>Duraci n de la partida en formato "HH:mm:ss"</li>
     * <li>Nombre del jugador</li>
     * <li>"GANO" si el jugador gan  o "PERDIO" en caso contrario</li>
     * <li>N mero de puntos logrados</li>
     * </ul>
     * @param data la cadena que representa la Partida
     * @return una Partida creada a partir de la cadena de texto
     * @throws IllegalArgumentException si el formato de la cadena es inv lido
     */
    public static Partida fromString(String data) {
        String[] partes = data.split(",");
        if (partes.length != 5) {
            throw new IllegalArgumentException("Formato inválido de partida: " + data);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(partes[0], formatter);

        String[] durParts = partes[1].split(":");
        int horas = Integer.parseInt(durParts[0]);
        int minutos = Integer.parseInt(durParts[1]);
        int segundos = Integer.parseInt(durParts[2]);
        Duration duracion = Duration.ofHours(horas).plusMinutes(minutos).plusSeconds(segundos);

        String jugador = partes[2];
        boolean gano = partes[3].equalsIgnoreCase("GANO");
        int puntos = Integer.parseInt(partes[4]);

        return new Partida(inicio, duracion, jugador, gano, puntos);
    }
}
