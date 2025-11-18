import java.util.*;

public class Poker {

    public static void main(String[] args) {
        Random random = new Random();

        // Generar dos manos
        Carta[] mano1 = generarMano(random);
        Carta[] mano2 = generarMano(random);

        System.out.println("Mano 1:");
        imprimirMano(mano1);

        System.out.println("\nMano 2:");
        imprimirMano(mano2);

        // Evaluar ambas manos
        int puntaje1 = evaluarMano(mano1);
        int puntaje2 = evaluarMano(mano2);

        System.out.println("\nJugada Mano 1 = " + nombreJugada(puntaje1));
        System.out.println("Jugada Mano 2 = " + nombreJugada(puntaje2));

        if (puntaje1 > puntaje2) {
            System.out.println("\n===> GANA LA MANO 1");
        } else if (puntaje2 > puntaje1) {
            System.out.println("\n===> GANA LA MANO 2");
        } else {
            System.out.println("\n===> EMPATE");
        }
    }

    // Generar mano aleatoria
    static Carta[] generarMano(Random random) {
        Carta[] mano = new Carta[5];
        char[] palos = {'S', 'C', 'H', 'D'};

        for (int i = 0; i < 5; i++) {
            int valor = random.nextInt(13) + 2; // 2–14
            char palo = palos[random.nextInt(4)];
            mano[i] = new Carta(valor, palo);
        }
        return mano;
    }

    // Imprimir mano
    static void imprimirMano(Carta[] mano) {
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }

    // Evaluar la mano
    static int evaluarMano(Carta[] mano) {

        boolean color = true;
        char paloBase = mano[0].palos;

        for (Carta c : mano) {
            if (c.palos != paloBase) {
                color = false;
                break;
            }
        }

        int[] valores = new int[5];
        for (int i = 0; i < 5; i++) valores[i] = mano[i].valores;
        Arrays.sort(valores);

        boolean escalera = true;
        for (int i = 0; i < 4; i++) {
            if (valores[i] + 1 != valores[i + 1]) {
                escalera = false;
                break;
            }
        }

        int[] contador = new int[15];
        for (int v : valores) contador[v]++;

        boolean poker = false;
        boolean trio = false;
        int pares = 0;

        for (int v = 2; v <= 14; v++) {
            if (contador[v] == 4) poker = true;
            if (contador[v] == 3) trio = true;
            if (contador[v] == 2) pares++;
        }

        boolean full = (trio && pares == 1);

        if (color && escalera) return 8;
        if (poker) return 7;
        if (full) return 6;
        if (color) return 5;
        if (escalera) return 4;
        if (trio) return 3;
        if (pares == 2) return 2;
        if (pares == 1) return 1;
        return 0;
    }

    static String nombreJugada(int p) {
        return switch (p) {
            case 8 -> "Escalera de Color";
            case 7 -> "Poker";
            case 6 -> "Full";
            case 5 -> "Color";
            case 4 -> "Escalera";
            case 3 -> "Trío";
            case 2 -> "Doble Par";
            case 1 -> "Par";
            default -> "Carta Alta";
        };
    }
}