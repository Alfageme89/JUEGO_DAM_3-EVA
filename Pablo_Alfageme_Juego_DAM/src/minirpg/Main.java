package minirpg;

import minirpg.lore.Lore;
import minirpg.partida.Partida;
import minirpg.Enemigo;
import minirpg.Jugador;
import minirpg.enemigos.*;
import minirpg.enemigos.Jefes.Jefe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Lore lore = new Lore();
    private Historial historial = new Historial();
    private int nivelesSuperados = 0;

    public static void main(String[] args) {
        new Main().run();
    }

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
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void mostrarTitulo() {
        System.out.println("   /------------------/ ");
        System.out.println("  / THE LAST DUNGEON / ");
        System.out.println(" /------------------/ ");
    }

    private void mostrarMenu() {
        System.out.println("\nADÉNTRATE EN LA MAZMORRA\n");
        System.out.println("1. Iniciar Partida");
        System.out.println("2. Mostrar Historial");
        System.out.println("3. Cargar Historial");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

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
                System.out.println("\nEl " + enemigo.getNombre() + " se desploma sin vida. Has sobrevivido a este encuentro.\nSuspiras de alivio.");
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
        System.out.println("Has Perecido tras enfrentarte a " + nivelesSuperados + " enemigos\nPuntos: " + puntos);
    }

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
                "Haber salido de esta te da fuerzas para continuar."
            );
            subirNivelGeneral(jugador);
        }
    }

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

    private void descanso(Jugador jugador, int ronda) {
        System.out.println("\nTe tomas un momento para recuperar el aliento antes de continuar...\n");
        jugador.setVida(Math.min(jugador.getVida() + 15, jugador.getVidaMaxima()));
        jugador.setMana(Math.min(jugador.getMana() + 10, jugador.getManaMaximo()));
        System.out.println("Has recuperado algo de vida y maná.\n");
    }
}
