import java.util.Random;

public class ŻelaznyElektoratPartyjny extends Wyborca {
    private Jednopartyjność mojaPartia;

    public ŻelaznyElektoratPartyjny(String imięNazwisko, int numerOkręgu, String nazwaPartii) {
        super(imięNazwisko, numerOkręgu);
        this.mojaPartia = new Jednopartyjność(nazwaPartii);
    }

    public WektorCech wyraźPoglądy() { return null; }
    public void zmieńPoglądy(WektorCech wektorZmian) { } // nie ma żadnych wag więc nie ma czego zmieniać

    public Głos wybierzKandydata(ListaWyborcza lW) {
        ListaKandydatówPartii lP = mojaPartia.znajdźListęPartii(lW);

        Random r = new Random();
        int indeks = r.nextInt(lP.dajLiczbęKandydatów()); // losuje liczbę od 0 do liczby kandydatów - 1
        int i = 0;
        for (Kandydat k : lP) {
            if(i == indeks)
                return new Głos(k, this.imięNazwisko);
            i++;
        }
        return null;
    }
}
