package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Esqueleto: un enemigo básico con resistencia a ataques físicos.
 */
public class Slime extends Enemigo {

    public Slime() {
        super("Cúmulo de moco consciente(Slime)",10,15,0,90,90);
        this.setVida(10);
        this.setAtaque(15);
    }

}
