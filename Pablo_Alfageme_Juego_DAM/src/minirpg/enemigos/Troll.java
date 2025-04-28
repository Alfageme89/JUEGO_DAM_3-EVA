package minirpg.enemigos;

import minirpg.Enemigo;
import minirpg.Jugador;
/**
 * Enemigo Troll: un enemigo muy fuerte con mucha vida.
 */
public class Troll extends Enemigo {

    public Troll() {
        super("Troll",150,25,0,15,0);
        this.setVida(150);
        this.setAtaque(25);
    }

    // Métodos adicionales o habilidades del Troll
    public void golpePesado(Jugador jugador) {
        // El Troll tiene un golpe poderoso que causa mucho daño.
        int danio = this.getAtaque() * 2;
        jugador.recibirDanio(danio);
        System.out.println("El Troll lanza un golpe pesado y causa " + danio + " de daño.");
    }
}
