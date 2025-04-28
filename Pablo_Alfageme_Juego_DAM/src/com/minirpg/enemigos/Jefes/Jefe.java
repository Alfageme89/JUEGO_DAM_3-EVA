package com.minirpg.enemigos.Jefes;

import com.minirpg.Enemigo;

public class Jefe extends Enemigo {

    public Jefe(String nombre, int vida, int ataque, double probabilidadEvasion, int resistenciaMagica, int resistenciaFisica) {
        super(nombre, vida, ataque, probabilidadEvasion, resistenciaMagica, resistenciaFisica);
    }

    // Método para aumentar los stats del jefe en función de un factor de crecimiento
    public void aumentarStats(int factorCrecimiento) {
        // Aumentar vida y ataque con base en el factor de crecimiento
        int incrementoVida = factorCrecimiento * 10;  // Ejemplo, 50 puntos de vida por nivel
        int incrementoAtaque = factorCrecimiento * 5;  // Ejemplo, 20 puntos de ataque por nivel
        this.setVida(this.getVida() + incrementoVida);
        this.setAtaque(this.getAtaque() + incrementoAtaque);
    }
}
