public class ŻelaznyElektoratKandydata extends Wyborca {
    private Jednopartyjność mojaPartia; // przechowuje nazwę ulubionej partii i udostępnia metodę szukającą jej listy wyborczej
    private int numerKandydata;

    public ŻelaznyElektoratKandydata(String imięNazwisko, int numerOkręgu, String nazwaPartii, int numerKandydata) {
        super(imięNazwisko, numerOkręgu);
        this.mojaPartia = new Jednopartyjność(nazwaPartii);
        this.numerKandydata = numerKandydata;
    }

    public WektorCech wyraźPoglądy() { return null; }
    public void zmieńPoglądy(WektorCech wektorZmian) { } // nie ma żadnych wag więc nie ma czego zmieniać

    // wyborca wie na kogo głosuje, ale
    // musi znaleźć kandydata na liście żeby zidentyfikować jego imię i nazwisko
    public Głos wybierzKandydata(ListaWyborcza lW) {
        ListaKandydatówPartii lP = mojaPartia.znajdźListęPartii(lW);
        for(Kandydat k: lP){
            if (k.dajSwójNumer() == this.numerKandydata && k.dajNumerOkręgu() == this.numerOkręgu) {
                return new Głos(k, this.imięNazwisko);
            }
        }
        return null;
    }
}