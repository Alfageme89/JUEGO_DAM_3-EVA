package minirpg.enemigos;

import minirpg.Enemigo;

/**
 * Enemigo Vampiro: un enemigo con habilidades de regeneración y alto daño.
 */
public class Vampiro extends Enemigo {

    public Vampiro() {
        super("Vampiro",100,18,0,10,-10);
        this.setVida(100);
        this.setAtaque(18);
    }

    // Métodos adicionales o habilidades del Vampiro
    public void regenerar() {
        // El Vampiro regenera vida en cada turno.
        this.setVida(this.getVida() + 10);
        System.out.println("El Vampiro se regenera y recupera 10 puntos de vida.");
    }
}
