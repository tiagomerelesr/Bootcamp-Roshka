

        public class Carta {

        int valores;
        char palos;

        // uso el constructor para inicializar el objeto con sus especificaciones (valores,palos)
        public Carta(int v, char p) {
            valores = v;
            palos = p;
        }

        // utilizo el metodo para covertir estos valores a texto, para que imprima bien la mano en el main
        public String toString() {
            String valPal;  // variable local para convertir el número a texto
            if (valores == 14) valPal = "A";
            else if (valores == 13) valPal = "K";
            else if (valores == 12) valPal = "Q";
            else if (valores == 11) valPal = "J";
            else if (valores == 10) valPal = "T";
            else valPal = Integer.toString(valores);

            return valPal + palos; // es para concatenar valores y palos
        }
    }

