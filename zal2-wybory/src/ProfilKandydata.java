public class ProfilKandydata {
    // informacje o kandydacie używane w bazie głosów w komisji wyborczej
    private String imięNazwisko;
    private String nazwaPartii;
    private int numerNaLiście;
    private int liczbaGłosów;


    public void dodajGłos() { this.liczbaGłosów += 1; }
    public int dajLiczbęGłosów() { return liczbaGłosów; }
    public void setNazwaPartii(String nazwaPartii) { this.nazwaPartii = nazwaPartii; }
    public void setNumerNaLiście(int numerNaLiście) { this.numerNaLiście = numerNaLiście; }

    public ProfilKandydata(String imięNazwisko, String nazwaPartii, int numerNaLiście) {
        this.imięNazwisko = imięNazwisko;
        this.nazwaPartii = nazwaPartii;
        this.numerNaLiście = numerNaLiście;
        this.liczbaGłosów = 0;
    }

    public ProfilKandydata(Kandydat kandydat) {
        this.imięNazwisko = kandydat.przedstawSię();
        this.nazwaPartii = kandydat.dajNazwęPartii();
        this.numerNaLiście = kandydat.dajSwójNumer();
        this.liczbaGłosów = 0;
    }

    @Override
    public String toString() {
        return this.imięNazwisko + " " + this.nazwaPartii + " " + this.liczbaGłosów;
    }


}
