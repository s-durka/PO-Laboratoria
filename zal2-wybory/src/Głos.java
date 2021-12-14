public class Głos {
    private String nazwaPartii;
    private int numerNaLiście;
    private String imięKandydata;
    private String imięWyborcy;

    public String dajNazwęPartii() {
        return nazwaPartii;
    }
    public int dajNumerNaLiście() {
        return numerNaLiście;
    }


    //    public Głos(String nazwaPartii, int numerNaLiście) {
//        this.nazwaPartii = nazwaPartii;
//        this.numerNaLiście = numerNaLiście;
//    }

    public Głos(Kandydat k, String imięWyborcy) {
        this.nazwaPartii = k.dajNazwęPartii();
        this.numerNaLiście = k.dajSwójNumer();
        this.imięKandydata = k.przedstawSię();
        this.imięWyborcy = imięWyborcy;
    }

    @Override
    public String toString() {
        return this.imięWyborcy + "  " + this.imięKandydata;
    }


}
