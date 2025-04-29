package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo RataGigante: un enemigo básico con resistencia a ataques físicos.
 */
public class RataGigante extends Enemigo {

    public RataGigante() {
        super("Rata gigante",25,10,0.4,1,9);
        this.setVida(25);
        this.setAtaque(20);
    }

}
