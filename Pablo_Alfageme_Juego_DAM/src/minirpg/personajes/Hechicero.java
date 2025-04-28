package minirpg.personajes;

import minirpg.Jugador;
import minirpg.habilidades.Escapar;
import minirpg.habilidades.Habilidad;
import minirpg.habilidades.HabilidadFuego;
import minirpg.habilidades.ImplosionConcentrada;

public class Hechicero extends Jugador {
    public Hechicero(String nombre) {
        super(nombre, "Hechicero");
        setAtaque(2);
        definirHabilidades();  // Definir las habilidades del Hechicero
        
    }

    @Override
    public void definirHabilidades() {
        // Definimos las habilidades para el Hechicero
        agregarHabilidad(new HabilidadFuego()); // Agregar habilidad de fuego
        agregarHabilidad(new ImplosionConcentrada());
        agregarHabilidad(new Escapar());
        // Agregar otras habilidades aquí si es necesario
    }
}
