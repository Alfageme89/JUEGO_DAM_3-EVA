package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Esqueleto: un enemigo básico con resistencia a ataques físicos.
 */
public class Saqueador extends Enemigo {

    public Saqueador() {
        super("Saqueador",70,28,0.1,9,9);
        this.setVida(70);
        this.setAtaque(28);
    }

}

