package minirpg.habilidades;

import minirpg.Jugador;
import minirpg.Enemigo;

public class DisparoMagico extends Habilidad {
    public DisparoMagico() {
        super("Disparo Magico", "Inflige 20 de daño al enemigo.", 1); // 20 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " lanza un proyectil hecho de energía a " + enemigo.getNombre());
        enemigo.recibirDanio(20,true); // Inflige daño al enemigo
    }
}
