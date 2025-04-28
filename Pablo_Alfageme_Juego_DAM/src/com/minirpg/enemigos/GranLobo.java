package com.minirpg.enemigos;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

/**
 * Enemigo Gran Lobo: un enemigo ágil que ataca varias veces.
 */
public class GranLobo extends Enemigo {

    public GranLobo() {
        super("Gran Lobo", 50, 10, 0.3, 10, 10);
        this.setVida(70);
        this.setAtaque(14);
    }

    // Método personalizado de Gran Lobo (sin @Override)
    public void ataqueRapido(Jugador jugador) {
        int dañoTotal = 0;
        for (int i = 0; i < 3; i++) {
            jugador.recibirDanio(this.getAtaque());
            dañoTotal += this.getAtaque();
        }
        System.out.println("El Gran Lobo ataca rápidamente, causando un daño total de " + dañoTotal + " puntos.");
    }
}
