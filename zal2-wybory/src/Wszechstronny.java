public abstract class Wszechstronny extends Wyborca {
    protected WektorCech wagi;

    // używane do przekazywania wyborów przez wskaźniki:
    protected Kandydat wybraniec;
    protected int sumaWybrańca;

    public Wszechstronny(String imięNazwisko, int numerOkręgu, WektorCech wagi) {
        super(imięNazwisko, numerOkręgu);
        this.wagi = wagi;
        this.wybraniec = null;
        this.sumaWybrańca = Integer.MIN_VALUE;
    }

    public void zmieńPoglądy(WektorCech wektorZmiany) {
        this.wagi = this.wagi.dodaj(wektorZmiany);
    }

    public WektorCech wyraźPoglądy() {
        return this.wagi;
    }

    // wybiera faworyta z partii na podstawie sum ważonych
    // i aktualizuje wartość atrybutów wybraniec oraz sumaWybrańca
    protected void wybierzZPartii(ListaKandydatówPartii lP) {
        Kandydat faworytZPartii = null;
        int sumaFaworyta = Integer.MIN_VALUE;
        int liczbaKandydatów = lP.dajLiczbęKandydatów();

        for (Kandydat k : lP) {
            int sumaWażona = k.przedstawPoglądy().iloczynSkalarny(wagi); // liczy sumę ważoną, czyli iloczyn sk. swoich wag i cech kandydata
            if (sumaWażona > sumaFaworyta) {
                sumaFaworyta = sumaWażona;
                faworytZPartii = k;
            }
        }
        if (sumaFaworyta > this.sumaWybrańca) {
            this.sumaWybrańca = sumaFaworyta;
            this.wybraniec = faworytZPartii;
        }
    }
}
