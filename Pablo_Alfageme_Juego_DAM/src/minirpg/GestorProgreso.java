package minirpg;

import java.util.*;
import minirpg.Enemigo;
import minirpg.enemigos.*;
import minirpg.enemigos.Jefes.Armadura;
import minirpg.enemigos.Jefes.Jefe;
import minirpg.enemigos.Jefes.SenorDeLosMuertos;

public class GestorProgreso {

    private static final Map<Integer, List<Enemigo>> enemigosPorStage = new HashMap<>();

    static {
        // Stage 1: Niveles 1-10
        List<Enemigo> stage1 = List.of(
            new Slime(),
            new RataGigante(),
            new Esqueleto(),
            new Ghoul(),
            new Saqueador()
        );

        // Stage 2: Niveles 11-20
        List<Enemigo> stage2 = List.of(
            new GranLobo(),
            new Saqueador(),
            new Chaman(),
            new Orco()
        );

        // Stage 3: Niveles 21+
        List<Enemigo> stage3 = List.of(
            new Troll(),
            new Vampiro(),
            new Troll(),      // puedes variar estos duplicados o añadir otros
            new Chaman()      // según tu diseño
        );

        enemigosPorStage.put(1, stage1);
        enemigosPorStage.put(2, stage2);
        enemigosPorStage.put(3, stage3);
    }

    /** Devuelve un enemigo aleatorio adecuado al nivel que llevas superado */
    public static Enemigo obtenerEnemigo(int nivelesSuperados) {
        int stage = calcularStage(nivelesSuperados);
        List<Enemigo> lista = enemigosPorStage.get(stage);
        return lista.get(new Random().nextInt(lista.size()));
    }

    /** Calcula a qué “stage” perteneces según nivelesSuperados */
    private static int calcularStage(int nivelesSuperados) {
        if (nivelesSuperados < 10) {
            return 1; // niveles 0–9 => stage 1
        } else if (nivelesSuperados < 20) {
            return 2; // niveles 10–19 => stage 2
        } else {
            return 3; // 20+ => stage 3
        }
    }

    /**  
     * Devuelve el jefe de nivel cuando corresponda, o null si no es un nivel de jefe  
     * Jefes fijos en 10 y 20; cada múltiplo de 10 posterior potencia al SeñorDeLosMuertos.  
     */
    public static Jefe obtenerJefe(int nivelesSuperados) {
        if (nivelesSuperados == 10) {
            return new Armadura();
        }
        if (nivelesSuperados == 20) {
            return new SenorDeLosMuertos();
        }
        if (nivelesSuperados > 20 && nivelesSuperados % 10 == 0) {
            int potencia = (nivelesSuperados / 10) - 2; 
            SenorDeLosMuertos rep = new SenorDeLosMuertos();
            rep.aumentarStats(potencia);
            return rep;
        }
        return null;
    }
}
