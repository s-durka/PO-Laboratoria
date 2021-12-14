import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Komisja {
    private LinkedList<Głos> oddaneGłosy;
    private Map<String, ProfilKandydata[]> bazaGłosów;
    private Para<String, Integer>[] tablicaMandatów;

    private Map<String, ProfilKandydata[]> stwórzPustąBazęGłosów(ListaWyborcza listaWyborcza) {
        Map<String, ProfilKandydata[]> bazaGłosów = new HashMap<String, ProfilKandydata[]>();
        listaWyborcza.zacznijIterować();
        while(listaWyborcza.hasNext()) {
            ListaKandydatówPartii lkp = listaWyborcza.dajNastępnąPartię();
            String klucz = lkp.dajNazwęPartii();
            ProfilKandydata[] kandydaci = new ProfilKandydata[lkp.dajLiczbęKandydatów()];
            int i = 0;
            for (Kandydat k : lkp) {
                kandydaci[i++] = new ProfilKandydata(k);
            }
            bazaGłosów.put(klucz, kandydaci);
        }
        return bazaGłosów;
    }

    public Komisja(ListaWyborcza listaWyborcza) {
        this.oddaneGłosy = new LinkedList<Głos>();
        this.bazaGłosów = stwórzPustąBazęGłosów(listaWyborcza);
        this.tablicaMandatów = null;
    }

    // przyjmuje głosy,
    // zapisuje je w rejestrze oddanych głosów
    // oraz aktualizuje bazę danych głosów zebranych przez kandydatów
    public void zarejestrujGłos(Głos g) {
        ProfilKandydata[] głosyPartii = bazaGłosów.get(g.dajNazwęPartii());
        głosyPartii[g.dajNumerNaLiście() - 1].dodajGłos();
        oddaneGłosy.add(g);
    }

    public void wypiszHistorięGłosów() {
        for (int i = 0; i < oddaneGłosy.size(); i++) {
            System.out.println(oddaneGłosy.get(i));
        }
    }

    public void wypiszWynikiGłosowania() {
        for(String partia : bazaGłosów.keySet()) {
            ProfilKandydata[] kandydaci = bazaGłosów.get(partia);
            for (int i = 0; i < kandydaci.length; i++) {
                System.out.println(kandydaci[i]);
            }
        }
    }

    private Para<String, Integer>[] sumujGłosyPatrii() {
        int długośćTablicy = bazaGłosów.size();
        Para<String, Integer>[] tablicaPar = (Para<String, Integer>[]) new Para<?, ?>[długośćTablicy];
        int l = 0;
        for(String partia : bazaGłosów.keySet()) {
            ProfilKandydata[] kandydaci = bazaGłosów.get(partia);
            int sumaGłosów = 0;
            for (int i = 0; i < kandydaci.length; i++) {
                sumaGłosów += kandydaci[i].dajLiczbęGłosów();
            }
            Para<String, Integer> PiG = new Para<String, Integer>(partia, sumaGłosów);
            tablicaPar[l] = PiG;
            l++;
        }
        return tablicaPar;
    }

    // sumuje mandaty partiom i zapisuje je w atrybucie tablicaMandatów
    public void zamieńGłosyNaMandaty(MetodaPrzeliczaniaGłosów generatorMandatów) {
        int liczbaMandatów = oddaneGłosy.size()/10;
        Para<String, Integer>[] tablicaPar = sumujGłosyPatrii();
        Para<String, Integer>[] tablicaMandatów = generatorMandatów.rozdajMandaty(liczbaMandatów, tablicaPar);
        this.tablicaMandatów = tablicaMandatów;
    }

    public void wypiszMandaty() {
        if (this.tablicaMandatów == null)
            System.out.println("Mandaty nie zostały przyznane.");
        for (int i = 0; i < tablicaMandatów.length; i++) {
            System.out.println(tablicaMandatów[i]);
        }
    }

    public Integer dajMandatyPartii(String nazwaPartii) {
        for (int i = 0; i < tablicaMandatów.length; i++) {
            if (tablicaMandatów[i].getPierwszy() == nazwaPartii)
                return tablicaMandatów[i].getDrugi();
        }
        return null;
    }

}
