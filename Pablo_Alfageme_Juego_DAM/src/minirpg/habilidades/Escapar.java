package minirpg.habilidades;

import minirpg.Enemigo;
import minirpg.Jugador;

public class Escapar extends Habilidad {
    public Escapar() {
        super("Escapar!!", "Elude el combate y huye a toda prisa a costa de todo tu maná", 100); // 100 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " Escapa a toda velocidad de " + enemigo.getNombre()+"\n La huida es exitosa.");
        enemigo.recibirDanio(1000000,true); // Inflige daño al enemigo
    }
}
