package com.minirpg.enemigos;

import com.minirpg.Enemigo;

/**
 * Enemigo Esqueleto: un enemigo básico con resistencia a ataques físicos.
 */
public class Esqueleto extends Enemigo {

    public Esqueleto() {
        super("Esqueleto",50,10,0.1,1,9);
        this.setVida(50);
        this.setAtaque(10);
    }

}
