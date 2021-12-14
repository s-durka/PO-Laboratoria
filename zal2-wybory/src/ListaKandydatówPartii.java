import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListaKandydatówPartii implements Iterable<Kandydat> {
    // lista kandydatów w danym okręgu

    private String nazwaPartii;
    private ArrayList<Kandydat> kandydaci;
    private int liczbaKandydatów;
    private int numerOkręgu;

    public ListaKandydatówPartii(String nazwaPartii, int numerOkręgu, Kandydat[] kandydaci) {
        this.nazwaPartii = nazwaPartii;
        this.liczbaKandydatów = kandydaci.length;
        this.kandydaci = new ArrayList<Kandydat>(Arrays.asList(kandydaci));
        this.numerOkręgu = numerOkręgu;

    }

    public int dajLiczbęKandydatów() { return liczbaKandydatów; }
    public String dajNazwęPartii() { return nazwaPartii; }

    public ListaKandydatówPartii scal(ListaKandydatówPartii l2){
        Kandydat[] nowyKandydaci = new Kandydat[this.kandydaci.size() + l2.kandydaci.size()];
        int k = 0;
        for(int i = 0; i < this.kandydaci.size(); i++)
            nowyKandydaci[k++] = this.kandydaci.get(i);

        for(int i = 0; i < l2.kandydaci.size(); i++)
            nowyKandydaci[k++] = l2.kandydaci.get(i);

        return new ListaKandydatówPartii(nazwaPartii, numerOkręgu, nowyKandydaci);
    }

    public Iterator<Kandydat> iterator() {
        return kandydaci.iterator();
    }
}
