public class WszechstronnyBezpartyjny extends Wszechstronny {

    public WszechstronnyBezpartyjny(String imięNazwisko, int numerOkręgu, WektorCech wagi) {
        super(imięNazwisko, numerOkręgu, wagi);
    }

    public Głos wybierzKandydata(ListaWyborcza lW) {
        this.wybraniec = null;
        this.sumaWybrańca = Integer.MIN_VALUE;
        lW.zacznijIterować();
        while (lW.hasNext()) {
            ListaKandydatówPartii lP = lW.dajNastępnąPartię();
            wybierzZPartii(lP); // ustala wartość this.wybraniec na wybranego kandydata
        }
        return new Głos(this.wybraniec, this.imięNazwisko);
    }
}
