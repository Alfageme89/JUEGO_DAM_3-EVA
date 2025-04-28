package minirpg;

import minirpg.habilidades.Habilidad;
import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {
    private String nombre;
    private String clase;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;
    private int mana;
    private int manaMaximo; // <- Agregado
    private int agilidad;
    private int nivel; // <- Agregado
    private List<Habilidad> habilidades;

    public Jugador(String nombre, String clase) {
        this.nombre = nombre;
        this.clase = clase;
        this.vida = 100;  // Vida base inicial
        this.vidaMaxima = 100; // Vida máxima por defecto
        this.ataque = 20;
        this.defensa = 10;
        this.mana = 100;
        this.manaMaximo = 100; // Inicial igual que mana
        this.agilidad = 10;
        this.nivel = 1; // Nivel inicial
        this.habilidades = new ArrayList<>();
    }

    public abstract void definirHabilidades();

    public void recibirDanio(int cantidad) {
        int danioRecibido = cantidad - this.defensa;
        this.vida -= Math.max(danioRecibido, 0);
    }

    public void mostrarHabilidades() {
        System.out.println("Selecciona una habilidad:");
        for (int i = 0; i < habilidades.size(); i++) {
            System.out.println((i + 1) + ". " + habilidades.get(i).getNombre() + " - " + habilidades.get(i).getDescripcion());
        }
    }

    public void usarHabilidad(int seleccion, Enemigo enemigo) {
        if (seleccion > 0 && seleccion <= habilidades.size()) {
            Habilidad habilidad = habilidades.get(seleccion - 1);
            if (this.mana >= habilidad.getCosteMana()) {
                habilidad.ejecutar(this, enemigo);
                this.mana -= habilidad.getCosteMana();
                System.out.println("Mana restante: " + this.mana);
            } else {
                System.out.println("No tienes suficiente mana para usar esa habilidad.");
            }
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public void usarHabilidadConMana(int seleccion, Enemigo enemigo) {
        if (seleccion > 0 && seleccion <= habilidades.size()) {
            Habilidad habilidad = habilidades.get(seleccion - 1);
            if (this.mana >= habilidad.getCosteMana()) {
                habilidad.ejecutar(this, enemigo);
                this.mana -= habilidad.getCosteMana();
                System.out.println("¡Usaste " + habilidad.getNombre() + "! Mana restante: " + this.mana);
            } else {
                System.out.println("¡No tienes suficiente mana para usar esa habilidad!");
            }
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public void agregarHabilidad(Habilidad habilidad) {
        habilidades.add(habilidad);
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getClase() { return clase; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getMana() { return mana; }
    public int getManaMaximo() { return manaMaximo; } // <- Nuevo getter
    public int getAgilidad() { return agilidad; }
    public int getNivel() { return nivel; } // <- Nuevo getter
    public List<Habilidad> getHabilidades() { return habilidades; }

    public int getVidaMaxima() { return vidaMaxima; }

    public Habilidad getHabilidad() {
        if (!habilidades.isEmpty()) {
            return habilidades.get(0);
        } else {
            return null;
        }
    }

    // Setters
    public void setVida(int vida) { this.vida = Math.min(vida, vidaMaxima); }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setMana(int mana) { this.mana = Math.min(mana, manaMaximo); } // Limita al manaMaximo
    public void setManaMaximo(int manaMaximo) { this.manaMaximo = manaMaximo; } // <- Nuevo setter
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }
    public void setAgilidad(int agilidad) { this.agilidad = agilidad; }
    public void setNivel(int nivel) { this.nivel = nivel; } // <- Nuevo setter
}
