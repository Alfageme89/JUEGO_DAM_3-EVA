package minirpg.personajes;

import minirpg.Jugador;
import minirpg.habilidades.Habilidad;
import minirpg.habilidades.TiroCabeza;
import minirpg.habilidades.Escapar;
import minirpg.habilidades.GolpeBajo;

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
