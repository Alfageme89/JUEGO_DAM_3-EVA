package com.minirpg.habilidades;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

public class TiroCabeza extends Habilidad{
     public TiroCabeza() {
        super("Disparo a la Cabeza", "Inflige 90 de daño al enemigo con un 20% de acierto.", 25); // 25 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " Apunta y dispara una flecha a la cabeza del  " + enemigo.getNombre());
        enemigo.recibirDanio(90,false); // Inflige daño al enemigo
    }
}
