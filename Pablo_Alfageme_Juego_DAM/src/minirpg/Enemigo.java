package minirpg;

/**
 * Representa un enemigo en el juego con atributos básicos como vida, ataque,
 * evasión y resistencias a daño físico y mágico.
 */
public class Enemigo {
    private String nombre;
    private int vida;
    private int ataque;
    private double probabilidadEvasion; // 0.0 a 1.0 (10% sería 0.1)
    private int resistenciaMagica; // porcentaje de reducción mágica
    private int resistenciaFisica; // porcentaje de reducción física

    /**
     * Crea un nuevo enemigo con los atributos especificados.
     *
     * @param nombre              Nombre del enemigo.
     * @param vida                Vida inicial.
     * @param ataque              Valor de ataque.
     * @param probabilidadEvasion Probabilidad de evadir un ataque (0.0 a 1.0).
     * @param resistenciaMagica   Reducción porcentual del daño mágico.
     * @param resistenciaFisica   Reducción porcentual del daño físico.
     */
    public Enemigo(String nombre, int vida, int ataque, double probabilidadEvasion, int resistenciaMagica, int resistenciaFisica) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.probabilidadEvasion = probabilidadEvasion;
        this.resistenciaMagica = resistenciaMagica;
        this.resistenciaFisica = resistenciaFisica;
    }

    /**
     * Aplica daño al enemigo teniendo en cuenta evasión y resistencias.
     *
     * @param cantidad  Cantidad base de daño recibido.
     * @param esMagico  Indica si el daño es mágico (true) o físico (false).
     */
    public void recibirDanio(int cantidad, boolean esMagico) {
        if (Math.random() < probabilidadEvasion) {
            System.out.println(nombre + " evadió el ataque!");
            return;
        }

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

    /**
     * @return Nombre del enemigo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Vida actual del enemigo.
     */
    public int getVida() {
        return vida;
    }

    /**
     * @return Valor de ataque del enemigo.
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * @return Probabilidad de evasión del enemigo (0.0 a 1.0).
     */
    public double getProbabilidadEvasion() {
        return probabilidadEvasion;
    }

    /**
     * @return Porcentaje de resistencia mágica del enemigo.
     */
    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    /**
     * @return Porcentaje de resistencia física del enemigo.
     */
    public int getResistenciaFisica() {
        return resistenciaFisica;
    }

    /**
     * Establece la vida actual del enemigo.
     *
     * @param vida Nueva cantidad de vida.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Establece el valor de ataque del enemigo.
     *
     * @param ataque Nuevo valor de ataque.
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}
