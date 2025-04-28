package com.minirpg.personajes;

import com.minirpg.Jugador;
import com.minirpg.habilidades.Habilidad;
import com.minirpg.habilidades.Estocada;
import com.minirpg.habilidades.CorteRapido; // Supongamos que tienes una habilidad así
import com.minirpg.habilidades.Escapar;

public class Espadachin extends Jugador {
    public Espadachin(String nombre) {
        super(nombre, "Espadachín");
        setAgilidad(15);
        setAtaque(12);
        definirHabilidades(); // Definir las habilidades
        setVidaMaxima(110);
        setVida(110);
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
