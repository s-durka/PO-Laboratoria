public class MinJednocechowyPartyjny extends MinJednocechowy {
    private Jednopartyjność mojaPartia;

    public MinJednocechowyPartyjny(int numerCechy, int numerOkręgu, String imięNazwisko, String nazwaPartii) {
        super(numerCechy, numerOkręgu, imięNazwisko);
        this.mojaPartia = new Jednopartyjność(nazwaPartii);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        this.wybraniec = null;
        this.opiniaWybrańca = Integer.MAX_VALUE;
        ListaKandydatówPartii lP = mojaPartia.znajdźListęPartii(lW);
        wybierzZPartii(lP);
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
