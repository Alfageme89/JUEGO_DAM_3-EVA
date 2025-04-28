package minirpg;

import minirpg.lore.Lore;
import minirpg.partida.Partida;
import minirpg.Enemigo;
import minirpg.Jugador;
import minirpg.habilidades.Habilidad;
import minirpg.habilidades.HabilidadFuego;
import minirpg.enemigos.*;
import minirpg.enemigos.Jefes.Armadura;
import minirpg.enemigos.Jefes.SenorDeLosMuertos;
import minirpg.enemigos.Jefes.Jefe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3300/minirpg";
    private static final String USUARIO = "root";
    private static final String CLAVE = "2234";

    private ArrayList<Partida> historial = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Lore lore = new Lore(); // Instanciamos Lore para usar el método seleccionarClase
    public int nivelesSuperados = 0;

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
                    mostrarHistorial();
                    break;
                case "3":
                    cargarHistorial();
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
        Jugador jugador = lore.seleccionarClase(nick); // Llamamos al método desde Lore
        if (jugador == null) {
            System.out.println("Clase no válida. Volviendo al menú...");
            return;
        }
        Partida partida = new Partida(jugador.getNombre());
        System.out.println("Al entrar a la mazmorra encuentras salas vacías hasta que en un pasillo desolado...");

        // Bucle de combates y descanso
        int ronda = 1;
        while (jugador.getVida() > 0) {
            Enemigo enemigo = generarEnemigoAleatorio();  // Generar enemigo aleatorio aquí

            combate(jugador, enemigo);

            // Si el jugador sobrevive
            if (jugador.getVida() > 0) {
                System.out.println("\nEl " + enemigo.getNombre()
                        + " se desploma sin vida. Has sobrevivido a este encuentro.\nsuspiras de alivio.");
                descanso(jugador, ronda);
                ronda++;
                Jefe jefe = GestorProgreso.obtenerJefe(nivelesSuperados + 1);
                if (jefe != null) {
                    enemigo = jefe;
                }
                nivelesSuperados++;
            } else {
                System.out.println("\nTu visión se apaga subitamente...");
                System.out.println("\nHas perecido en las Mazmorras del Olvido. Tu viaje llega a su fin.\n");
                break;
            }
        }

        // Calculamos puntos y guardamos la partida
        int puntos = nivelesSuperados * 10; // Ejemplo, podríamos hacerlo más complejo
        partida.finalizar(nivelesSuperados > 0, puntos);
        historial.add(partida);
        System.out.println("Has Perecido tras enfrentarte a " + nivelesSuperados + " enemigos\n Puntos: " + puntos);
        guardarPartida(partida);
    }

    private Enemigo generarEnemigoAleatorio() {
        // Probabilidades de aparición para cada tipo de enemigo
        int probabilidad = random.nextInt(100); // Obtiene un número entre 0 y 99

        if (probabilidad < 30) { // 30% de que salga un Esqueleto
            return new Esqueleto();
        } else if (probabilidad < 50) { // 20% de que salga un Ghoul
            return new Ghoul();
        } else if (probabilidad < 65) { // 15% de que salga un Vampiro
            return new Vampiro();
        } else if (probabilidad < 80) { // 15% de que salga un Troll
            return new Troll();
        } else if (probabilidad < 90) { // 10% de que salga un GranLobo
            return new GranLobo();
        } else if (probabilidad < 95) { // 5% de que salga un Armadura
            return new Armadura();
        } else { // 5% de que salga un SeñorDeLosMuertos
            return new SenorDeLosMuertos();
        }
    }

    private void combate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\u001B[31m\n"+"¡Un " + enemigo.getNombre() + " ha aparecido!\u001B[37m");

        boolean defendiendo = false; // para saber si el jugador defendió en el turno anterior

        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            System.out.println("\u001B[34m\nTus HP: " + jugador.getVida() + "/" + jugador.getVidaMaxima() + " | HP del enemigo: "
                    + enemigo.getVida()+"\u001B[37m");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar habilidad");
            System.out.print("Elige tu acción: ");
            String accion = sc.nextLine();

            switch (accion) {
                case "1":
                    enemigo.recibirDanio(jugador.getAtaque(), true);
                    System.out.println("¡Atacas al enemigo!");
                    break;
                case "2":
                    defendiendo = true;
                    System.out.println("Te preparas para defender...");
                    break;
                case "3":
                    jugador.mostrarHabilidades();
                    System.out.print("Elige una habilidad: ");
                    int seleccionHabilidad = sc.nextInt(); // Lee la selección de habilidad
                    sc.nextLine(); // Limpiamos el buffer de entrada, para que no afecte a la siguiente línea
                    jugador.usarHabilidadConMana(seleccionHabilidad, enemigo); // Usamos la nueva función para comprobar
                                                                               // mana
                    break;
                default:
                    System.out.println("Acción no válida");
            }

            // Turno del enemigo si sigue vivo
            if (enemigo.getVida() > 0) {
                if (defendiendo) {
                    jugador.recibirDanio(enemigo.getAtaque() / 2); // daño reducido a la mitad
                    System.out.println("A duras penas te cubres del ataque");
                    defendiendo = false; // se resetea la defensa para el siguiente turno
                } else {
                    jugador.recibirDanio(enemigo.getAtaque());
                    System.out.println("\u001B[31m¡El enemigo te ataca!\u001B[37m");
                }
            }
        }
        if (enemigo.getVida() <= 0) {
            if (enemigo instanceof SenorDeLosMuertos || enemigo instanceof Armadura) { // Jefes especiales
                System.out.println("\nEl enemigo se arrodilla y sucumbe a sus heridas.\nTe sientes reconfortado tras semejante batalla.\nHaber salido de esta te da fuerzas para continuar.");
                subirNivelGeneral(jugador);

            }
        }
    }

    private void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay partidas en el historial.");
        } else {
            historial.forEach(System.out::println);
        }
    }

    private void cargarHistorial() {
        System.out.println("(Cargar partidas de fichero o base de datos - aún no implementado)");
    }

    private void guardarPartida(Partida partida) {
        // Guardar en fichero
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("partidas.txt", true))) {
            bw.write(partida.toString() + "\n");
        } catch (IOException ex) {
            System.out.println("Error al guardar en fichero: " + ex.getMessage());
        }
    }

    private void subirNivelGeneral(Jugador jugador) {
        jugador.setNivel(jugador.getNivel() + 1);
        jugador.setAtaque(jugador.getAtaque() + 2); // Mejora de ataque
        jugador.setDefensa(jugador.getDefensa() + 2); // Mejora de defensa
        jugador.setVidaMaxima(jugador.getVidaMaxima() + 10); // Aumenta vida máxima
        jugador.setVida(jugador.getVidaMaxima()); // Al subir de nivel, se cura completamente
        jugador.setManaMaximo(jugador.getManaMaximo() + 5); // También sube el maná máximo
        jugador.setMana(jugador.getManaMaximo()); // Y se rellena el maná

        System.out.println("\u001B[33m\n¡Has subido de nivel!\u001B[37m");
        System.out.println("Nivel actual: " + jugador.getNivel());
        System.out.println("Tus estadísticas han mejorado:");
        System.out.println("- Ataque: " + jugador.getAtaque());
        System.out.println("- Defensa: " + jugador.getDefensa());
        System.out.println("- Vida máxima: " + jugador.getVidaMaxima());
        System.out.println("- Maná máximo: " + jugador.getManaMaximo());
    }

    private void descanso(Jugador jugador, int ronda) {
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
                break;
            case "2":
                curarse(jugador);
                break;
            case "3":
                meditar(jugador);
                break;
            default:
                System.out.println("\\u001B[31mAcción no válida.\\u001B[37m");
        }
    }

    private void subirNivel(Jugador jugador) {
        System.out.println("Elige qué atributo mejorar:");
        System.out.println("1. Vida (+20)");
        System.out.println("2. Ataque (+5)");
        System.out.println("3. Defensa (+3)");
        System.out.println("4. Agilidad (+2)");
        System.out.print("Elige un atributo: ");
        String eleccion = sc.nextLine();

        switch (eleccion) {
            case "1":
                jugador.setVidaMaxima(jugador.getVidaMaxima() + 20); // Aumentamos la vida máxima
                break;
            case "2":
                jugador.setAtaque(jugador.getAtaque() + 5);
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

    private void meditar(Jugador jugador) {
        jugador.setMana(jugador.getMana() + 20); // Meditar recupera 20 puntos de mana
        System.out.println("Meditas y recuperas 20 puntos de mana.");
    }
}
