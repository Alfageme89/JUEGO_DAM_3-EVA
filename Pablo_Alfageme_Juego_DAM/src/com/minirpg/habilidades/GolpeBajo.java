package com.minirpg.habilidades;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

public class GolpeBajo extends Habilidad{
     public GolpeBajo() {
        super("Golpe Bajo", "Inflige 20 de daño al enemigo.", 5); // 5 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " Apunta tu siguiente ataque para desequilibrar a el " + enemigo.getNombre());
        enemigo.recibirDanio(20,false); // Inflige daño al enemigo
    }
}
