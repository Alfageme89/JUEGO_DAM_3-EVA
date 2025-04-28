package minirpg.personajes;

import minirpg.Jugador;
import minirpg.habilidades.Habilidad;
import minirpg.habilidades.GolpeBajo;
import minirpg.habilidades.Escapar;
import minirpg.habilidades.MuerteInstantanea;

public class Asesino extends Jugador {
    public Asesino(String nombre) {
        super(nombre, "Asesino");
        setAgilidad(25);   // El Asesino es muy ágil
        setAtaque(15);     // El Asesino tiene un ataque moderado
        setVidaMaxima(75);  // Vida máxima personalizada para Asesino
        setVida(75);        // Establecer vida inicial del Asesino
        definirHabilidades();
    }

    @Override
    public void definirHabilidades() {
        // Aquí agregas las habilidades propias del Asesino
        agregarHabilidad(new MuerteInstantanea());
        agregarHabilidad(new GolpeBajo());
        agregarHabilidad(new Escapar());
        // Puedes agregar más habilidades si quieres
    }
}
