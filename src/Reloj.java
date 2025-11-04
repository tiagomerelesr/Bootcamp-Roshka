//0-esta es mi clase y a esa clase le declare variables
public class Reloj {
    int horas;
    int minutos;
    int segundos;

    //1-acà cree los constructores para la clase
    //1-en el primero ademas de crear el constructor le asigne el valor a cada variable
    public Reloj() {
        horas = 12;
        minutos = 0;
        segundos = 0;
    }

    //
    public Reloj(int h, int m, int s) {
        horas = h % 24;
        minutos = m % 59;
        segundos = s % 59;
    }

    public Reloj(int segundosDesdeLaMedianoche) {
        horas = (segundosDesdeLaMedianoche / 3600) % 24;
        minutos = (segundosDesdeLaMedianoche / 60) % 60;
        segundos = segundosDesdeLaMedianoche % 60;
    }
    //2-Aca hice los metododos
    //2- el set sirve para setear es decir modificar los datos
    void setReloj(int segundosDesdeLaMedianoche) {
    }

    //3-ESTE ES EL FORMATO PARA EL GETTER, para obtener info
    int getHoras() {
        return horas;
    }
    //3-ESTE ES EL FORMATO PARA EL GETTER, para obtener info
    int getMinutos() {
        return minutos;
    }
    //3-ESTE ES EL FORMATO PARA EL GETTER, para obtener info
    int getSegundos() {
        return segundos;
    }

    //4- ESTE ES EL FORMATO PARA EL SETTER, sirve para MODIFICAR/SETTEAR
    void setHoras(int h) {
        horas = h % 24;
    }
    //4- ESTE ES EL FORMATO PARA EL SETTER, sirve para MODIFICAR/SETTEAR
    void setMinutos(int m) {
        minutos = m % 60;
    }
    //4- ESTE ES EL FORMATO PARA EL SETTER, sirve para MODIFICAR/SETTEAR
    void setSegundos(int s) {
        segundos = s % 60;
    }
    //
    void tick() {
        segundos++;
        if (segundos >= 60) {
            segundos = 0;
            minutos++;
            if (minutos >= 60) {
                minutos = 0;
                horas = (horas + 1) & 24;
            }
        }
    }

    void tickDecrement() {
        segundos--;
        if (segundos < 0) {
            segundos = 59;
            minutos--;
            if (minutos < 0) {
                minutos = 59;
                horas = (horas - 1 + 24) % 24;
            }
        }
    }

    void addReloj(Reloj otro) {
        int totalSegundos = (horas * 3600 + minutos * 60 + segundos)
                + (otro.horas * 3600 + otro.minutos * 60 + otro.segundos);
        totalSegundos = totalSegundos % 86400; // dentro de un día
        horas = totalSegundos / 3600;
        minutos = (totalSegundos % 3600) / 60;
        segundos = totalSegundos % 60;
    }

    Reloj restaReloj(Reloj otro) {
        // Convertir ambos relojes a segundos
        int segundosActual = horas * 3600 + minutos * 60 + segundos;
        int segundosOtro = otro.horas * 3600 + otro.minutos * 60 + otro.segundos;

        // Calcular diferencia asegurando resultado positivo
        int diferencia = (segundosActual - segundosOtro + 86400) % 86400;

        // Retornar un nuevo objeto Reloj con esa diferencia
        return new Reloj(diferencia);

        }

    public String toString() {
        String hh = (horas < 10 ? "0" : "") + horas;
        String mm = (minutos < 10 ? "0" : "") + minutos;
        String ss = (segundos < 10 ? "0" : "") + segundos;
        return "[" + hh + ":" + mm + ":" + ss + "]";
    }

}


