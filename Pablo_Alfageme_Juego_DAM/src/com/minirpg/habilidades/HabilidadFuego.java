package com.minirpg.habilidades;

import com.minirpg.Jugador;
import com.minirpg.Enemigo;

public class HabilidadFuego extends Habilidad {
    public HabilidadFuego() {
        super("Hechizo de Fuego", "Inflige 30 de daño al enemigo.", 20); // 20 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " lanza un hechizo de fuego contra " + enemigo.getNombre());
        enemigo.recibirDanio(30,true); // Inflige daño al enemigo
    }
}
