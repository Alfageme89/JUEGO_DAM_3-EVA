package minirpg.personajes;

import minirpg.Jugador;
import minirpg.habilidades.Habilidad;
import minirpg.habilidades.Estocada;
import minirpg.habilidades.CorteRapido; // Supongamos que tienes una habilidad así
import minirpg.habilidades.Escapar;

public class Espadachin extends Jugador {
    public Espadachin(String nombre) {
        super(nombre, "Espadachín");
        setAgilidad(15);
        setAtaque(12);
        definirHabilidades(); // Definir las habilidades
        setVidaMaxima(120);
        setVida(120);
    }

    @Override
    public void definirHabilidades() {
        // Aquí agregas las habilidades propias del Espadachín
        agregarHabilidad(new CorteRapido());
        agregarHabilidad(new Estocada());
        agregarHabilidad(new Escapar());
        // Puedes agregar más habilidades si quieres
    }
}
