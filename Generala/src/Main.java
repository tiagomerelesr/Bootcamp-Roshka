
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Juego!!!");
    // con el scanner el usuario puede poner los datos de los cinco dados que le tocan para hacer las jugadas
        System.out.println("Ingrese el número del dado uno:");
    int dado1 = scanner.nextInt();
        System.out.println("Ingrese el número del dado dos:");
    int dado2 = scanner.nextInt();
        System.out.println("Ingrese el número del dado tres:");
    int dado3 = scanner.nextInt();
        System.out.println("Ingrese el número del dado cuatro:");
    int dado4 = scanner.nextInt();
        System.out.println("Ingrese el número del dado cinco:");
    int dado5 = scanner.nextInt();

    // si se cumple esta condicion tiene que imrimir el mensaje de "Generala"
    if (dado1 == dado2 && dado2 == dado3 && dado3 == dado4 && dado4 == dado5) {
        System.out.println("La jugada es Generala");
    }
    // si se cumple esta condicion tiene que imrimir el mensaje de "POKER"
    else if ((dado1 == dado2 && dado2 == dado3 && dado3 == dado4) ||
             (dado2 == dado3 && dado3 == dado4 && dado4 == dado5) ||
             (dado1 == dado2 && dado2 == dado3 && dado3 == dado5) ||
             (dado1 == dado2 && dado2 == dado4 && dado4 == dado5) ||
             (dado1 == dado3 && dado3 == dado4 && dado4 == dado5)) {
        System.out.println("La jugada es Poker");
    }
    // si se cumple esta condicion tiene que imrimir el mensaje de "full"
    else if ((dado1 == dado2 && dado2 == dado3 && dado4 == dado5 && dado1 != dado4) ||
            (dado1 == dado2 && dado2 == dado4 && dado3 == dado5 && dado1 != dado3) ||
            (dado1 == dado2 && dado2 == dado5 && dado3 == dado4 && dado1 != dado3) ||
            (dado1 == dado3 && dado3 == dado4 && dado2 == dado5 && dado1 != dado2) ||
            (dado1 == dado3 && dado3 == dado5 && dado2 == dado4 && dado1 != dado2) ||
            (dado1 == dado4 && dado4 == dado5 && dado2 == dado3 && dado1 != dado2) ||
            (dado2 == dado3 && dado3 == dado4 && dado1 == dado5 && dado2 != dado1) ||
            (dado2 == dado3 && dado3 == dado5 && dado1 == dado4 && dado2 != dado1) ||
            (dado2 == dado4 && dado4 == dado5 && dado1 == dado3 && dado2 != dado1) ||
            (dado3 == dado4 && dado4 == dado5 && dado1 == dado2 && dado3 != dado1)) {
        System.out.println("La jugada es Full");
    }
    // si se cumple esta condicion tiene que imrimir el mensaje de "ESCALERA"
    else if ((dado1 == 1 && dado2 == 2 && dado3 == 3 && dado4 == 4 && dado5 == 5) ||
            (dado1 == 2 && dado2 == 3 && dado3 == 4 && dado4 == 5 && dado5 == 6) ||
            (dado1 == 3 && dado2 == 4 && dado3 == 5 && dado4 == 6 && dado5 == 1)) {
        System.out.println("La jugada es Escalera");
    }
    // Y si no se cumplen ninguna de las condiciones anteriores, no hay ninguna jugada por ende es "Jugada nada"
    else {
        System.out.println("La jugada es Nada");
        }
    }
}
