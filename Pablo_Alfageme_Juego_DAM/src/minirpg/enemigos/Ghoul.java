package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Ghoul: un enemigo ágil y rápido, pero frágil.
 */
public class Ghoul extends Enemigo {

    public Ghoul() {
        super("Ghoul",40,18,0.35,1,1);
        this.setVida(40);
        this.setAtaque(18);
    }

    // Métodos adicionales o habilidades del Ghoul
    public void evadir() {
        // Aquí podrías agregar lógica de evasión si el enemigo tiene un chance de esquivar ataques.
        System.out.println("El Ghoul intenta evadir el ataque.");
    }
}
