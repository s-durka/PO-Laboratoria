public class MaksJednocechowyPartyjny extends MaksJednocechowy {
    private Jednopartyjność mojaPartia;

    public MaksJednocechowyPartyjny(int numerCechy, String imięNazwisko, int numerOkręgu, String nazwaPartii) {
        super(numerCechy, numerOkręgu, imięNazwisko);
        this.mojaPartia = new Jednopartyjność(nazwaPartii);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        this.wybraniec = null;
        this.opiniaWybrańca = Integer.MIN_VALUE;
        ListaKandydatówPartii lP = mojaPartia.znajdźListęPartii(lW);
        wybierzZPartii(lP);
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
