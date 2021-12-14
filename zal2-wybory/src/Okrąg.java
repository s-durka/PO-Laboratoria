import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Okręg {
    // listy kandydatów partii nie są dane w konstruktorze.
    // partie po kolei wystawiają swoich kandydatów
    private int numerOkręgu;
    private ListaWyborcza listaWyborcza;
    private ArrayList<Wyborca> wyborcy;
    private Komisja komisja;
    private int ilośćPartii;

    public LinkedList<WektorCech> przeprowadźSondaż() {
        LinkedList<WektorCech> opinie = new LinkedList<WektorCech>();
        for (Wyborca w : wyborcy) {
            if (w.wyraźPoglądy() != null) {
                opinie.add(w.wyraźPoglądy());
            }
        }
        return opinie; // klasa WektorCech jest immutable więc zwrócenie ich listy jest okej
    }

    public ListaKandydatówPartii dajListęKandydatówPartii(String nazwaPartii) {
        listaWyborcza.zacznijIterować();
        boolean znaleziona = false;
        ListaKandydatówPartii lk = null;
        while (listaWyborcza.hasNext() && !znaleziona) {
            lk = listaWyborcza.dajNastępnąPartię();
            if (lk.dajNazwęPartii() == nazwaPartii) {
                znaleziona = true;
            }
        }
        return lk;

    }

    public Okręg(int numerOkręgu, ListaWyborcza listaWyborcza, ArrayList<Wyborca> wyborcy, int ilośćPartii) {
        this.numerOkręgu = numerOkręgu;
        this.listaWyborcza = listaWyborcza;
        this.wyborcy = wyborcy;
        this.ilośćPartii = ilośćPartii;
        this.komisja = new Komisja(listaWyborcza);
    }

    public int dajLiczbęWyborców() {
        return wyborcy.size();
    }

    public void przeprowadźDziałanie(WektorCech działanie) {
        for (Wyborca w : wyborcy) {
            w.zmieńPoglądy(działanie);
        }
    }

    public Okręg scal(Okręg o2) {
        ArrayList<Wyborca> wyborcy = new ArrayList<>();
        wyborcy.addAll(this.wyborcy);
        wyborcy.addAll(o2.wyborcy);
        ListaWyborcza nowaLW = this.listaWyborcza.połączListy(o2.listaWyborcza);
        return new Okręg(this.numerOkręgu, nowaLW, wyborcy, this.ilośćPartii);
    }

    public void głosowanie() {
        for (Wyborca wyborca : wyborcy) {
            komisja.zarejestrujGłos(wyborca.wybierzKandydata(listaWyborcza));
        }
    }

    public int dajMandatyPartii(String nazwaPartii) {
        return komisja.dajMandatyPartii(nazwaPartii);
    }

    public void podliczMandaty(MetodaPrzeliczaniaGłosów metodaMandatów) {
        komisja.zamieńGłosyNaMandaty(metodaMandatów);
    }

    // zakładamy, że to Bajtocja wypisze najpierw numer okręgu
    public void wypisGłosowania() {
        komisja.wypiszHistorięGłosów();
        komisja.wypiszWynikiGłosowania();
        komisja.wypiszMandaty();
    }

}
