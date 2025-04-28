package com.minirpg.personajes;

import com.minirpg.Jugador;
import com.minirpg.habilidades.Habilidad;
import com.minirpg.habilidades.TiroCabeza;
import com.minirpg.habilidades.Escapar;
import com.minirpg.habilidades.GolpeBajo;

public class Cazador extends Jugador {
    public Cazador(String nombre) {
        super(nombre, "Cazador");
        setAgilidad(20);   // El Cazador tiene una buena agilidad
        setAtaque(10);     // El Cazador tiene un ataque adecuado
        definirHabilidades();
    }

   @Override
    public void definirHabilidades() {
        // Aquí agregas las habilidades propias del Espadachín
        agregarHabilidad(new TiroCabeza());
        agregarHabilidad(new GolpeBajo());
        agregarHabilidad(new Escapar());
        // Puedes agregar más habilidades si quieres
    }
}
