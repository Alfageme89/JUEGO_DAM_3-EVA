package minirpg;

import minirpg.lore.Lore;
import minirpg.partida.Partida;
import minirpg.Enemigo;
import minirpg.Jugador;
import minirpg.enemigos.*;
import minirpg.enemigos.Jefes.Jefe;
import minirpg.basedatos.PartidasDB;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Lore lore = new Lore();
    private Historial historial = new Historial();
    private int nivelesSuperados = 0;

    /**
     * Entry point del programa. Crea una instancia de Main y llama
     * a su metodo run() para empezar el juego.
     * @param args No se utiliza.
     */
    public static void main(String[] args) {
        new Main().run();
    }

     /**
     * Muestra el menú principal del juego y maneja la interacción con el usuario.
     * Permite crear una nueva partida, ver el historial, cargar una partida guardada,
     * listar partidas en la base de datos o salir del juego. El menú se repite hasta
     * que el usuario elija salir.
     */
    private void run() {
        mostrarTitulo();
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    iniciarPartida();
                    break;
                case "2":
                    historial.mostrarHistorial();
                    break;
                case "3":
                    historial.cargarHistorial();
                    break;
                case "4":
                    PartidasDB.listarPartida();
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    /**
     * Muestra el título del juego en la consola.
     */
    private void mostrarTitulo() {
        System.out.println("   /------------------/ ");
        System.out.println("  / THE LAST DUNGEON / ");
        System.out.println(" /------------------/ ");
    }

    /**
     * Muestra el menú de opciones para que el jugador elija una acción.
     */
    private void mostrarMenu() {
        System.out.println("\nADÉNTRATE EN LA MAZMORRA\n");
        System.out.println("1. Iniciar Partida");
        System.out.println("2. Mostrar Historial");
        System.out.println("3. Cargar Historial");
        System.out.println("4. Cargar BBDD");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    /**
     * Inicia una nueva partida, solicita el nombre del jugador, selecciona
     * su clase y comienza la mazmora. El jugador lucha contra enemigos
     * y jefes hasta que muere o completa la mazmora.
     */
    private void iniciarPartida() {
        nivelesSuperados = 0;
        System.out.print("Introduce tu nombre: ");
        String nick = sc.nextLine();
        Jugador jugador = lore.seleccionarClase(nick);
        if (jugador == null) {
            System.out.println("Clase no válida. Volviendo al menú...");
            return;
        }

        // Creamos y arrancamos la Partida
        Partida partida = new Partida(jugador.getNombre());
        System.out.println("Al entrar a la mazmorra encuentras salas vacías hasta que en un pasillo desolado...");

        int ronda = 1;
        while (jugador.getVida() > 0) {
            // ¿Es jefe o enemigo normal?
            Jefe jefe = GestorProgreso.obtenerJefe(nivelesSuperados + 1);
            Enemigo enemigo = (jefe != null)
                    ? jefe
                    : GestorProgreso.obtenerEnemigo(nivelesSuperados);

            combate(jugador, enemigo);

            if (jugador.getVida() > 0) {
                System.out.println("\nEl " + enemigo.getNombre()
                        + " se desploma sin vida. Has sobrevivido a este encuentro.\nSuspiras de alivio.");
                descanso(jugador, ronda);
                ronda++;
                nivelesSuperados++;
            } else {
                System.out.println("\nTu visión se apaga súbitamente...");
                System.out.println("\nHas perecido en las Mazmorras del Olvido. Tu viaje llega a su fin.\n");
                break;
            }
        }

        // Finalizamos y guardamos en historial
        int puntos = nivelesSuperados * 10;
        partida.finalizar(puntos);
        historial.agregarPartida(partida);
        PartidasDB.insertarPartida(partida);
        System.out.println("Has Perecido tras enfrentarte a " + nivelesSuperados + " enemigos\nPuntos: " + puntos);
    }

    /**
     * Realiza el combate entre el jugador y un enemigo.
     * El combate continua hasta que uno de los dos muere.
     *
     * @param jugador El jugador que participa en el combate.
     * @param enemigo El enemigo contra el que lucha el jugador.
     */
    private void combate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\u001B[31m\n¡Un " + enemigo.getNombre() + " ha aparecido!\u001B[37m");
        boolean defendiendo = false;

        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            System.out.println("\u001B[34m\nTus HP: " + jugador.getVida() + "/" + jugador.getVidaMaxima() +
                    " | HP del enemigo: " + enemigo.getVida() + "\u001B[37m");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar habilidad");
            System.out.print("Elige tu acción: ");
            String accion = sc.nextLine();

            switch (accion) {
                case "1":
                    enemigo.recibirDanio(jugador.getAtaque(), true);
                    System.out.println("\n¡Atacas al enemigo!\n");
                    break;
                case "2":
                    defendiendo = true;
                    System.out.println("\nTe preparas para defender...\n");
                    break;
                case "3":
                    jugador.mostrarHabilidades();
                    System.out.print("Elige una habilidad: ");
                    int sel = sc.nextInt();
                    sc.nextLine(); // limpiar buffer
                    jugador.usarHabilidadConMana(sel, enemigo);
                    break;
                default:
                    System.out.println("Acción no válida");
            }

            // Turno enemigo
            if (enemigo.getVida() > 0) {
                if (defendiendo) {
                    jugador.recibirDanio(enemigo.getAtaque() / 2);
                    System.out.println("A duras penas te cubres del ataque");
                    defendiendo = false;
                } else {
                    jugador.recibirDanio(enemigo.getAtaque());
                    System.out.println("\u001B[31m¡El enemigo te ataca!\u001B[37m");
                }
            }
        }

        // Subir de nivel si era jefe
        if (enemigo.getVida() <= 0 && enemigo instanceof Jefe) {
            System.out.println(
                    "\nEl enemigo se arrodilla y sucumbe a sus heridas.\n" +
                            "Te sientes reconfortado tras semejante batalla.\n" +
                            "Haber salido de esta te da fuerzas para continuar.");
            subirNivelGeneral(jugador);
        }
    }

    /**
     * Aumenta los atributos del jugador cuando sube de nivel.
     * Se incrementan ataque, defensa, vida máxima y maná máximo.
     *
     * @param jugador El jugador que sube de nivel.
     */
    private void subirNivelGeneral(Jugador jugador) {
        jugador.setNivel(jugador.getNivel() + 1);
        jugador.setAtaque(jugador.getAtaque() + 2);
        jugador.setDefensa(jugador.getDefensa() + 2);
        jugador.setVidaMaxima(jugador.getVidaMaxima() + 10);
        jugador.setVida(jugador.getVidaMaxima());
        jugador.setManaMaximo(jugador.getManaMaximo() + 5);
        jugador.setMana(jugador.getManaMaximo());

        System.out.println("\u001B[33m\n¡Has subido de nivel!\u001B[37m");
        System.out.println("Nivel actual: " + jugador.getNivel());
        System.out.println("- Ataque: " + jugador.getAtaque());
        System.out.println("- Defensa: " + jugador.getDefensa());
        System.out.println("- Vida máxima: " + jugador.getVidaMaxima());
        System.out.println("- Maná máximo: " + jugador.getManaMaximo());
    }

    /**
     * Permite al jugador descansar y tomar una decisión
     * sobre qué acción realizar para recuperar recursos.
     *
     * @param jugador El jugador que está descansando.
     * @param ronda   El número de ronda en el que se encuentra.
     */
    private void descanso(Jugador jugador, int ronda) {
        boolean opcionvalida = false;
        do {
            System.out.println("\u001B[32m\n-- Descansas tras el combate --");
            System.out.println("enfrentamientos completados: " + ronda);
            System.out.println("1. Subir de nivel");
            System.out.println("2. Curarse");
            System.out.println("3. Meditar");
            System.out.print("Elige tu acción: \u001B[37m");
            String accion = sc.nextLine();

            switch (accion) {
                case "1":
                    subirNivel(jugador);
                    opcionvalida = true;
                    break;
                case "2":
                    curarse(jugador);
                    opcionvalida = true;
                    break;
                case "3":
                    meditar(jugador);
                    opcionvalida = true;
                    break;
                default:
                    System.out.println("\u001B[31mAcción no válida.\u001B[37m");
            }
        } while (!opcionvalida);
    }

    /**
     * Permite al jugador subir de nivel y mejorar sus atributos.
     *
     * @param jugador El jugador que sube de nivel.
     */
    private void subirNivel(Jugador jugador) {
        System.out.println("Elige qué atributo mejorar:");
        System.out.println("1. Vida (+10)");
        System.out.println("2. Ataque (+2)");
        System.out.println("3. Defensa (+3)");
        System.out.println("4. Agilidad (+2) (SISTEMA DE AGILIDAD NO IMPLEMENTADO)");
        System.out.print("Elige un atributo: ");
        String eleccion = sc.nextLine();

        switch (eleccion) {
            case "1":
                jugador.setVidaMaxima(jugador.getVidaMaxima() + 10); // Aumentamos la vida máxima
                break;
            case "2":
                jugador.setAtaque(jugador.getAtaque() + 2);
                break;
            case "3":
                jugador.setDefensa(jugador.getDefensa() + 3);
                break;
            case "4":
                jugador.setAgilidad(jugador.getAgilidad() + 2);
                break;
            default:
                System.out.println("Acción no válida.");
        }
    }

    /**
     * Permite al jugador recuperar vida. Si la vida restante es mayor que 0,
     * el jugador se cura la menor cantidad entre 50 y la vida restante.
     * Si la vida restante es 0, se muestra un mensaje indicando que ya
     * tiene la vida al máximo.
     * 
     * @param jugador El jugador que se cura.
     */
    private void curarse(Jugador jugador) {
        int vidaMaxima = jugador.getVidaMaxima(); // Obtén la vida máxima del jugador
        int vidaRestante = vidaMaxima - jugador.getVida(); // Calcula cuánto le falta para llegar a su vida máxima

        if (vidaRestante > 0) {
            // Si la vida restante es mayor que 0, el jugador puede curarse
            int curacion = Math.min(50, vidaRestante); // No se cura más de 50 ni más de la vida restante
            jugador.setVida(jugador.getVida() + curacion);
            System.out.println("Te curas y recuperas " + curacion + " puntos de vida.");
        } else {
            System.out.println("Ya tienes la vida al máximo. No puedes curarte más.");
        }
    }

    /**
     * Permite al jugador meditar para recuperar maná.
     * Incrementa el maná del jugador en 20 puntos.
     *
     * @param jugador El jugador que medita.
     */

    private void meditar(Jugador jugador) {
        jugador.setMana(jugador.getMana() + 20); // Meditar recupera 20 puntos de mana
        System.out.println("Meditas y recuperas 20 puntos de mana.");
    }
}


