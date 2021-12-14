public abstract class MinJednocechowy extends Wyborca {
    protected int numerCechy;

    // używane jako wskaźniki zapisujące dotychczasowe wybory podczas głosowania:
    protected Kandydat wybraniec;
    protected int opiniaWybrańca;

    public MinJednocechowy(int numerCechy, int numerOkręgu, String imięNazwisko) {
        super(imięNazwisko, numerOkręgu);
        this.numerCechy = numerCechy;
        this.wybraniec = null;
        this.opiniaWybrańca = Integer.MAX_VALUE;
    }
    public WektorCech wyraźPoglądy() { return null; }
    public void zmieńPoglądy(WektorCech wektorZmian) { } // nie ma żadnych wag więc nie ma czego zmieniać

    // aktualizuje atrybut "wybraniec" i "opiniaWybrańca"
    protected void wybierzZPartii(ListaKandydatówPartii lP) {
        int liczbaKandydatów = lP.dajLiczbęKandydatów();

        for (Kandydat k : lP) {
            int opiniaKandydata = k.przedstawPoglądy().dajCechę(this.numerCechy);
            if (opiniaKandydata < this.opiniaWybrańca) {
                this.wybraniec = k;
                this.opiniaWybrańca = opiniaKandydata;
            }
        }
    }
}
