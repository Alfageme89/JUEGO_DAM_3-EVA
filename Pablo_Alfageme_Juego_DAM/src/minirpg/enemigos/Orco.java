package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Esqueleto: un enemigo básico con resistencia a ataques físicos.
 */
public class Orco extends Enemigo {

    public Orco() {
        super("Orco",75,30,0.2,12,5);
        this.setVida(75);
        this.setAtaque(30);
    }

}
