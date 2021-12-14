public class Jednopartyjność {
    // klasa przechowuje nazwę ulubionej partii i udostępnia metodę szukającą jej listy wyborczej
    private String nazwaPartii;

    public Jednopartyjność(String nazwaPartii) {
        this.nazwaPartii = nazwaPartii;
    }

    public ListaKandydatówPartii znajdźListęPartii(ListaWyborcza lW) {
        lW.zacznijIterować();
        ListaKandydatówPartii l = lW.dajNastępnąPartię();
        while (!this.nazwaPartii.equals(l.dajNazwęPartii()) && lW.hasNext()) {
            l = lW.dajNastępnąPartię();
        }
        return l;
    }
}
