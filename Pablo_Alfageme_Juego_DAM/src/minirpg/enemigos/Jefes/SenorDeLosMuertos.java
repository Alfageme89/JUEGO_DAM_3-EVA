package minirpg.enemigos.Jefes;



/**
 * Enemigo Señor de los Muertos: un jefe con poder mágico.
 */
public class SenorDeLosMuertos extends Jefe {

    public SenorDeLosMuertos() {
        super("Señor de los Muertos",1000,50,0.05,20,40);
        this.setVida(1000);
        this.setAtaque(50);
    }

    // Métodos adicionales o habilidades del Señor de los Muertos
    public void invocarAliado() {
        // El Señor de los Muertos puede invocar un aliado muerto (esto es solo un ejemplo).
        System.out.println("¡El Señor de los Muertos invoca un aliado para que lo ayude en la batalla!");
    }

    public void resucitar() {
        // El Señor de los Muertos puede resucitar una vez durante la batalla.
        if (this.getVida() <= 0) {
            this.setVida(100); // Resucita con 100 de vida
            System.out.println("¡El Señor de los Muertos resucita con 100 puntos de vida!");
        }
    }
}
