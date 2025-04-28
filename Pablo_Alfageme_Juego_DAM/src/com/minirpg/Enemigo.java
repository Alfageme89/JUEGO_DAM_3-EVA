package com.minirpg;

public class Enemigo {
    private String nombre;
    private int vida;
    private int ataque;
    private double probabilidadEvasion; // 0.0 a 1.0 (10% sería 0.1)
    private int resistenciaMagica; // porcentaje de reducción mágica
    private int resistenciaFisica; // porcentaje de reducción física

    public Enemigo(String nombre, int vida, int ataque, double probabilidadEvasion, int resistenciaMagica, int resistenciaFisica) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.probabilidadEvasion = probabilidadEvasion;
        this.resistenciaMagica = resistenciaMagica;
        this.resistenciaFisica = resistenciaFisica;
    }

    public void recibirDanio(int cantidad, boolean esMagico) {
        // Antes de recibir daño, comprobar evasión
        if (Math.random() < probabilidadEvasion) {
            System.out.println(nombre + " evadió el ataque!");
            return;
        }

        // Aplicar resistencias
        int danioReducido = cantidad;
        if (esMagico) {
            danioReducido = cantidad - (cantidad * resistenciaMagica / 100);
        } else {
            danioReducido = cantidad - (cantidad * resistenciaFisica / 100);
        }

        this.vida -= danioReducido;
        if (this.vida < 0) this.vida = 0;

        System.out.println(nombre + " recibió " + danioReducido + " de daño. Vida restante: " + vida);
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public double getProbabilidadEvasion() {
        return probabilidadEvasion;
    }

    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    public int getResistenciaFisica() {
        return resistenciaFisica;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}
