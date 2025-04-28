package minirpg.habilidades;

import minirpg.Enemigo;
import minirpg.Jugador;

public class MuerteInstantanea extends Habilidad {
    public MuerteInstantanea() {
        super("Asesinato Instantaneo", "Causa la muerte de objetivos con menos de 150 de vida", 50); // 50 es el coste de mana
    }

    @Override
    public void ejecutar(Jugador jugador, Enemigo enemigo) {
        System.out.println(jugador.getNombre() + " Se posiciona detrás de " + enemigo.getNombre()+" e inflinge daño masivo");
        enemigo.recibirDanio(150,true); // Inflige daño al enemigo
    }
}
