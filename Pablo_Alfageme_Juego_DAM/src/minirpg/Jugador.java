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
        this.defensa = 5;
        this.mana = 100;
        this.manaMaximo = 100; // Inicial igual que mana
        this.agilidad = 10;
        this.nivel = 1; // Nivel inicial
        this.habilidades = new ArrayList<>();
    }

    public abstract void definirHabilidades();

    /**
     * Recibe da o del enemigo y aplica defensa.
     * 
     * @param cantidad  Cantidad base de da o recibido.
     */
    public void recibirDanio(int cantidad) {
        int danioRecibido = cantidad - this.defensa;
        this.vida -= Math.max(danioRecibido, 0);
    }

    /**
     * Muestra las habilidades del jugador en la consola y permite
     * seleccionar una para usar.
     */
    public void mostrarHabilidades() {
        System.out.println("Selecciona una habilidad:");
        for (int i = 0; i < habilidades.size(); i++) {
            System.out.println((i + 1) + ". " + habilidades.get(i).getNombre() + " - " + habilidades.get(i).getDescripcion());
        }
    }

    /**
     * Utiliza una habilidad seleccionada del jugador contra un enemigo, 
     * descontando el costo de mana correspondiente si es posible.
     * 
     * @param seleccion  El índice de la habilidad a usar, comenzando desde 1.
     * @param enemigo    El enemigo objetivo sobre el cual se ejecutará la habilidad.
     * 
     * Imprime un mensaje si el jugador no tiene suficiente mana o si la selección es inválida.
     */

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

    /**
     * Utiliza una habilidad seleccionada del jugador contra un enemigo,
     * descontando el costo de mana correspondiente si es posible.
     * 
     * @param seleccion  El índice de la habilidad a usar, comenzando desde 1.
     * @param enemigo    El enemigo objetivo sobre el cual se ejecutará la habilidad.
     * 
     * Imprime un mensaje si el jugador no tiene suficiente mana o si la selección es inválida.
     */
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

    /**
     * Añade una habilidad al inventario del jugador.
     * 
     * @param habilidad  La habilidad a agregar.
     */
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

    /**
     * Obtiene la primera habilidad del inventario del jugador.
     * Si el inventario está vacío, devuelve null.
     * @return La primera habilidad del inventario o null si no hay habilidades.
     */
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
