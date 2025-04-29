package minirpg.enemigos.Jefes;

/**
 * Enemigo Armadura: un enemigo con alta defensa que refleja parte del daño recibido.
 */
public class Armadura extends Jefe {

    public Armadura() {
        super("Armadura Encantada", 180, 30, 0.1, 50, 10);
        this.setVida(180);
        this.setAtaque(30); // Muy bajo ataque directo
    }

    // Método personalizado de Armadura (sin @Override)
    public void recibirDanio(int danio) {
        int danioReflejado = (int) (danio * 0.2);
        this.setVida(this.getVida() - danio);
        System.out.println("La Armadura refleja " + danioReflejado + " de daño al atacante.");
    }
}
