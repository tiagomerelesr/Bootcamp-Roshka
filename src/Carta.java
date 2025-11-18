public class Carta {

    int valores;
    char palos;

    public Carta(int v, char p) {
        this.valores = v;
        this.palos = p;
    }

    @Override
    public String toString() {
        String valPal;
        if (this.valores == 14) valPal = "A";
        else if (this.valores == 13) valPal = "K";
        else if (this.valores == 12) valPal = "Q";
        else if (this.valores == 11) valPal = "J";
        else if (this.valores == 10) valPal = "T";
        else valPal = Integer.toString(this.valores);

        return valPal + this.palos;
    }
}

