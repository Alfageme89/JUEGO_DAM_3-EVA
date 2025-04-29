package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Esqueleto: un enemigo básico con resistencia a ataques físicos.
 */
public class Esqueleto extends Enemigo {

    public Esqueleto() {
        super("Esqueleto",50,20,0.1,1,9);
        this.setVida(50);
        this.setAtaque(20);
    }

}
