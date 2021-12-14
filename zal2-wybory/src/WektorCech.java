public class WektorCech {
    private int[] cechy;

    public WektorCech(int[] cechy) {

        this.cechy = cechy;
        for (int i = 0; i < cechy.length; i++) {
            if (this.cechy[i] > 100) this.cechy[i] = 100;
            else if (this.cechy[i] < -100) this.cechy[i] = -100;
        }
    }

    public int dajCechę(int numerCechy) {
        return cechy[numerCechy - 1];
    }

    public WektorCech clone() {
        return new WektorCech(this.cechy);
    }

    // zakładamy, że wektor w2 ma taki sam wymiar
    public int iloczynSkalarny(WektorCech w2) {
        int ilSk = 0;
        for (int i = 0; i < cechy.length; i++) {
            ilSk += cechy[i] * w2.dajCechę(i+1);
        }
        return ilSk;
    }

    public int sumaModułów() {
        int suma = 0;
        for (int i = 0; i < cechy.length; i++) {
            int moduł = cechy[i] > 0 ? cechy[i] : (-1)*cechy[i];
            suma += moduł;
        }
        return suma;
    }

    // tworzy nowy wektor cech będący sumą dwóch wektorów
    public WektorCech dodaj(WektorCech w2) {
        int[] suma = new int[this.cechy.length];
        for (int i = 0; i < cechy.length; i++) {
            suma[i] += this.cechy[i] + w2.dajCechę(i+1);
        }
        return new WektorCech(suma);
    }
}
