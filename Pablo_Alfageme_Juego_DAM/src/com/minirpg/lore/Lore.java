package com.minirpg.lore;

import java.util.Scanner;

import com.minirpg.Jugador;
import com.minirpg.personajes.Asesino;
import com.minirpg.personajes.Cazador;
import com.minirpg.personajes.Espadachin;
import com.minirpg.personajes.Hechicero;
import com.minirpg.Main;

public class Lore {

    private Scanner sc = new Scanner(System.in); // Añadir el escáner aquí para poder usarlo
    
    public Jugador seleccionarClase(String nick) {
        System.out.println("¿Qué clase escoges?");
        System.out.println("1. Aprendiz de Hechicero");
        System.out.println("2. Aprendiz de Espadachín");
        System.out.println("3. Aprendiz de Asesino");
        System.out.println("4. Aprendiz de Cazador");
        System.out.print("Opción: ");
    
        String eleccion = sc.nextLine();
        Jugador jugador = null;
    
        switch (eleccion) {
            case "1":
                mostrarLoreHechicero();
                jugador = new Hechicero(nick);
                break;
            case "2":
                mostrarLoreEspadachin();
                jugador = new Espadachin(nick);
                break;
            case "3":
                mostrarLoreAsesino();
                jugador = new Asesino(nick);
                break;
            case "4":
                mostrarLoreCazador();
                jugador = new Cazador(nick);
                break;
            default:
                System.out.println("Clase no válida. Volviendo al menú...");
                break;
        }
    
        return jugador;
    }

    // Métodos que muestran el lore de cada clase

    private void mostrarLoreHechicero() {
        System.out.println("\n-------------------------");
        System.out.println("  Elige el poder arcano...");
        System.out.println("\nNacido en un mundo lleno de magia, el camino del Hechicero no es para los débiles.");
        System.out.println("Desde niño, sentiste la conexión con las energías místicas que fluyen en el universo.");
        System.out.println("A través de años de estudio, lograste acceder a los secretos olvidados de los hechizos.");
        System.out.println("Sin embargo, algo oscuro ha comenzado a corromper el equilibrio mágico en tu tierra.");
        System.out.println("\nLos antiguos grimorios hablan de una amenaza proveniente de las profundidades de las mazmorras,");
        System.out.println("un poder que está desestabilizando la magia en el mundo. El conocimiento que buscas se encuentra allí,");
        System.out.println("pero para alcanzarlo, debes enfrentar los horrores que se esconden en su oscuridad.");
        System.out.println("\n-------------------------");
    }
    
    private void mostrarLoreEspadachin() {
        System.out.println("\n-------------------------");
        System.out.println("  Elige el acero...");
        System.out.println("\nNacido en las aldeas del reino, creciste bajo la tutela de un maestro espadachín legendario.");
        System.out.println("A medida que tus años de entrenamiento pasaban, tu habilidad con la espada se convirtió en tu sello distintivo.");
        System.out.println("Pero ahora, una amenaza ha surgido en los confines del reino.");
        System.out.println("\nEl enemigo, un ejército de criaturas monstruosas, ha comenzado a invadir las mazmorras bajo la montaña.");
        System.out.println("Tu hogar, una aldea tranquila, está en peligro, y las fuerzas del reino necesitan espadachines para luchar.");
        System.out.println("Tú, con tu espada en mano, te has adentrado en las mazmorras para poner fin a esta amenaza.");
        System.out.println("\n-------------------------");
    }
    
    private void mostrarLoreAsesino() {
        System.out.println("\n-------------------------");
        System.out.println("  Elige la Sombra...");
        System.out.println("\nTe adentras en un mundo donde la muerte no es solo un destino, sino una herramienta.");
        System.out.println("El arte de desaparecer en las sombras es lo que define a los Asesinos.");
        System.out.println("En las oscuras calles de Nox, te entrenaste para ser un depredador silencioso.");
        System.out.println("\nAhora, una antigua sociedad secreta te ha contactado. Las mazmorras del reino están infestadas por seres oscuros");
        System.out.println("y se rumorea que los secretos de una conspiración que amenaza al trono se esconden en su interior.");
        System.out.println("Tu misión es infiltrarte, recolectar información y eliminar a aquellos que se interponen en el camino de la verdad.");
        System.out.println("\n-------------------------");
    }
    
    private void mostrarLoreCazador() {
        System.out.println("\n-------------------------");
        System.out.println("  Elige el arco...");
        System.out.println("\nCriado en los bosques y valles del reino, el Cazador es un maestro en el uso del arco y las trampas.");
        System.out.println("Tu vida siempre fue tranquila, hasta que una criatura legendaria comenzó a acechar la región.");
        System.out.println("\nEl rey ha ofrecido una recompensa por su caza, y se cree que la bestia ha sido vista en las mazmorras.");
        System.out.println("El rey ha llamado a los mejores cazadores, y tú has decidido unirte a la expedición.");
        System.out.println("La caza en las mazmorras será peligrosa, pero tu arco y tu instinto te guiarán en la oscuridad.");
        System.out.println("\n-------------------------");
    }
    
}
