public class MinJednocechowyBezpartyjny extends MinJednocechowy {
    public MinJednocechowyBezpartyjny(int numerCechy, int numerOkręgu, String imięNazwisko) {
        super(numerCechy, numerOkręgu, imięNazwisko);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        this.wybraniec = null;
        this.opiniaWybrańca = Integer.MAX_VALUE;
        lW.zacznijIterować();
        while (lW.hasNext()) {
            ListaKandydatówPartii lP = lW.dajNastępnąPartię();
            wybierzZPartii(lP);
        }
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
