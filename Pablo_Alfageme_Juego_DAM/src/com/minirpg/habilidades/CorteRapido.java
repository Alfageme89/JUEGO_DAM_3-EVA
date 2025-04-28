package com.minirpg.habilidades;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

public class CorteRapido extends Habilidad{
     public CorteRapido() {
        super("Filo veloz", "Inflige 25 de daño al enemigo.", 5); // 5 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " ejecuta un corte veloz " + enemigo.getNombre());
        enemigo.recibirDanio(25,false); // Inflige daño al enemigo
    }
}
