import java.util.Scanner;

public class RelojDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese los segundos desde la medianoche:");
        int segundoUsuarios = sc.nextInt();

        Reloj reloj1 = new Reloj(segundoUsuarios);
        System.out.println("Hora inicial:" + reloj1);

        System.out.println("\nAvanzando 10 segundos:");
        for (int i = 1; i <= 10; i++){
            reloj1.tick();
            System.out.println("Tick " + 1 + ":" + reloj1);
        }

        Reloj reloj2 = new Reloj(3600);

        Reloj diferencia = reloj1.restaReloj(reloj2);
        System.out.println("\nDiferencia entre reloj1 y reloj2: " + diferencia);

        sc.close();
    }
}
