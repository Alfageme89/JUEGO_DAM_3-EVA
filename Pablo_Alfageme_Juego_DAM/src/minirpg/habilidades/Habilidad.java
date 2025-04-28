package minirpg.habilidades;

import minirpg.Jugador;
import minirpg.Enemigo;

public abstract class Habilidad {
    private String nombre;
    private String descripcion;
    private int costeMana;

    public Habilidad(String nombre, String descripcion, int costeMana) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costeMana = costeMana;
    }

    public abstract void ejecutar(Jugador jugador, Enemigo enemigo); // Método que será sobrecargado en clases concretas

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCosteMana() {
        return costeMana;
    }
}
