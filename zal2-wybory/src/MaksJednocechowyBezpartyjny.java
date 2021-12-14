public class MaksJednocechowyBezpartyjny extends MaksJednocechowy {
    public MaksJednocechowyBezpartyjny(int numerCechy, int numerOkręgu, String imięNazwisko) {
        super(numerCechy, numerOkręgu, imięNazwisko);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        this.wybraniec = null;
        this.opiniaWybrańca = Integer.MIN_VALUE;
        lW.zacznijIterować();
        while (lW.hasNext()) {
            ListaKandydatówPartii lP = lW.dajNastępnąPartię();
            wybierzZPartii(lP);
        }
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
