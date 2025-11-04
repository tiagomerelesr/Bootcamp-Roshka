import java.util.*;

public class Cartas {

    Cartas (String completo) {
        this.valores = String.valueOf(completo.charAt(0));
        this.palos = String.valueOf(completo.charAt(1));
    }
    String valorPalo() {
        return this.valores + this.palos;
    }

    String valores;
    String palos;

}
