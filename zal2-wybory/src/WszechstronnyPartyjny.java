public class WszechstronnyPartyjny extends Wszechstronny {
    private String nazwaPartii;
    private Jednopartyjność mojaPartia;

    public WszechstronnyPartyjny(String nazwaPartii, int numerOkręgu, String imięNazwisko, WektorCech wagi) {
        super(imięNazwisko, numerOkręgu, wagi);
        this.mojaPartia = new Jednopartyjność(nazwaPartii);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        ListaKandydatówPartii lP = mojaPartia.znajdźListęPartii(lW);
        wybierzZPartii(lP);
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
