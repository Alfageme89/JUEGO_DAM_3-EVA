package com.minirpg.habilidades;

import com.minirpg.Jugador;
import com.minirpg.Enemigo;

public class ImplosionConcentrada extends Habilidad {
    public ImplosionConcentrada() {
        super("Magia Gravitatoria", "Crea un agujero negro en miniatura que causa gran daño", 40); // 40 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " Crea un vortice gravitatorio que desintegra parte del cuerpo de " + enemigo.getNombre());
        enemigo.recibirDanio(120,true); // Inflige daño al enemigo
    }
}
