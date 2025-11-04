
import java.util.*; // en vez de poner uno por uno el scanner se trae toda la carpeta

public class Main {
    public static void main(String[] args) {
//----------------------------------------------------------------------------------------------------------------------
        /* toda esta estructura funciona para que pueda establecer el numeros de las 5 cartas de forma aleatoria, así hace que el juego siempre
        tenga probabilidades de varias manos*/
        Random random = new Random();
        //  Crear un array para guardar 5 cartas (la mano)
        Carta[] mano = new Carta[5];

        // Generar cartas aleatorias y guardarlas en el array
        for (int cartas1 = 0; cartas1 < 5; cartas1++) {
            int valor = random.nextInt(13) + 2; // valores del 2 al 14 (14 = As)
            char[] palos = {'S', 'C', 'H', 'D'}; // guarda los datos resumidos de espadas,corazones,diamentes
            char palo = palos[random.nextInt(4)];   // elige un palo al azar
            mano[cartas1] = new Carta(valor, palo);     // crea la carta y la guarda en el array
        }

        //  Mostrar la mano en consola usando el metodo soString
        System.out.println("Tu mano:");
        for (Carta carta : mano) {
            System.out.println(carta); // acá llama al metodo automaticamente para que se cumple el cambio del valor de las cartas A,J,T,Q,K.
        }
//----------------------------------------------------------------------------------------------------------------------

        //  Verificar si todas las cartas son del mismo palo (Color)
        boolean color = true;
        char primerPalo = mano[0].palos; // tomamos el palo de la primera carta
        for (Carta carta : mano) {
            if (carta.palos != primerPalo) {
                color = false;
                break; // si encontramos un palo diferente, ya no es color
            }
        }

        //  Preparar un array con los valores para verificar escalera y repeticiones
        int[] valores = new int[5];
        for (int rep = 0; rep < 5; rep++) valores[rep] = mano[rep].valores;
        Arrays.sort(valores); // ordenar valores para detectar escalera

        //  Verificar si los valores son consecutivos (Escalera)
        boolean escalera = true;
        for (int i = 0; i < 4; i++) {
            if (valores[i] + 1 != valores[i + 1]) {
                escalera = false;
                break;
            }
        }

        // Contar repeticiones de valores para detectar Poker, Full, Trío, Par
        int[] contador = new int[15];
        for (int v : valores) contador[v]++;

        boolean poker = false;
        boolean full = false;
        boolean trio = false;
        int par = 0;

        for (int valorCartas = 2; valorCartas <= 14; valorCartas++) {
            if (contador[valorCartas] == 4) poker = true;
            if (contador[valorCartas] == 3) trio = true;
            if (contador[valorCartas] == 2) par++;
        }
        if (trio && par == 1) full = true; // trío + un par = Full

        // imprime la jugada en vase se vayan cumpliendo las condiciones
        if (color && escalera)
            System.out.println("La jugada es Escalera de Color.");
        else if (poker)
            System.out.println("La jugada es Poker.");
        else if (full)
            System.out.println("La jugada es Full.");
        else if (color)
            System.out.println("La jugada es Color.");
        else if (escalera)
            System.out.println("La jugada es Escalera.");
        else if (trio)
            System.out.println("La jugada es Trío.");
        else if (par == 2)
            System.out.println("La jugada es un Par doble.");
        else if (par == 1)
            System.out.println("La jugada es Par.");
        else
            System.out.println("La jugada es Carta alta.");
    }
}