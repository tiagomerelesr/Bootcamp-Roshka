import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        String secreto;

        do {
            secreto = "";
            while (secreto.length() < 4) {
                int d = r.nextInt(10);
                if (secreto.isEmpty() && d == 0) continue;
                if (!secreto.contains("" + d)) secreto += d;
            }
        } while (secreto.length() != 4);

        System.out.println("Bienvenidos al juego Toros y Vacas:");

        while (true) {
            System.out.println("Ingrese un numero de 4 digitos:");
            String intento = sc.nextLine();

            if (intento.length() != 4
                    || !intento.matches("\\d{4}")
                    || new HashSet<>(Arrays.asList(intento.split(""))).size() != 4) {
                System.out.println("Número inválido. Debe tener 4 dígitos sin repetir.");
                continue;
            }

            int toros = 0, vacas = 0;

            for (int i = 0; i < 4; i++) {
                if (secreto.charAt(i) == intento.charAt(i)) toros++;
                else if (secreto.contains("" + intento.charAt(i))) vacas++;
            }

            System.out.println(vacas + " vacas, " + toros + " toros");

            if (toros == 4) {
                System.out.println("¡Felicidades! El número secreto era: " + secreto);
                break;
            }
        }
        sc.close();
    }

}