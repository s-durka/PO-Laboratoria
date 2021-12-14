public class Kandydat {
    private int numerOkręgu; // POTRZEBNE ŻELAZNEMU WYBORCY PRZY SCALONYCH OKRĘGACH
    private String nazwaPartii;
    private int numerNaLiście;
    private String imięNazwisko;
    private WektorCech cechy;

    public Kandydat(int numerOkręgu, String nazwaPartii, int numerNaLiście, String imięNazwisko, WektorCech cechy) {
        this.numerOkręgu = numerOkręgu;
        this.nazwaPartii = nazwaPartii;
        this.numerNaLiście = numerNaLiście;
        this.imięNazwisko = imięNazwisko;
        this.cechy = cechy;
    }

    public int dajNumerOkręgu() { return numerOkręgu; }
    public int dajSwójNumer() {
        return numerNaLiście;
    }
    public String przedstawSię() {
        return imięNazwisko;
    }
    public String dajNazwęPartii() {
        return nazwaPartii;
    }
    public WektorCech przedstawPoglądy() { return cechy; }



}
