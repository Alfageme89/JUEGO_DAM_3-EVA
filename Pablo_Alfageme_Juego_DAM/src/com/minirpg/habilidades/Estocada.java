package com.minirpg.habilidades;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

public class Estocada extends Habilidad{
     public Estocada() {
        super("Estocada", "Inflige 70 de daño al enemigo.con unaprobabalidad de exito de 35%", 15); // 15 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " apunta y ejecuta una estocada " + enemigo.getNombre());
        enemigo.recibirDanio(70,false); // Inflige daño al enemigo
    }
}
