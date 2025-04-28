package com.minirpg.enemigos;

import com.minirpg.Enemigo;
import com.minirpg.Jugador;

public class Chaman extends Enemigo {

    public Chaman() {
        super("Chamán Goblin", 50, 70, 0.05, 1, 1);
        this.setVida(50);
        this.setAtaque(70);
       // this.setDefensa(10);  // Defensa base
    }

    // // Habilidad para aumentar las resistencias del Chamán
    // public void AumentoResistencias() {
    //     int resistenciaExtra = 5;  // Cantidad con la que aumenta la defensa
    //     this.setDefensa(this.getDefensa() + resistenciaExtra);  // Aumenta la defensa del Chamán
    //     System.out.println("El Chamán Goblin aumenta sus resistencias. Su defensa ahora es: " + this.getDefensa());
    // }

    // @Override
    // public void atacar(Jugador jugador) {
    //     // Implementar el ataque normal del chamán si lo deseas
    //     jugador.recibirDanio(this.getAtaque());
    //     System.out.println("El Chamán Goblin ataca al jugador con " + this.getAtaque() + " de daño.");
    // }
}
