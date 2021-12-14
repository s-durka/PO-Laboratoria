import java.util.ArrayList;

public class Bajtocja {
    private Okręg[] okręgiPodstawowe;
    private int[] okręgiDoScalenia;
    private Okręg[] okręgi; // po scaleniu
    private WektorCech[] działania;
    private Partia[] partie;

    public Bajtocja(Okręg[] okręgiPodstawowe, int[] okręgiDoScalenia, Partia[] partie, WektorCech[] działania) {
        this.okręgiPodstawowe = okręgiPodstawowe;
        this.okręgiDoScalenia = okręgiDoScalenia;
        this.partie = partie;
        this.działania = działania;
    }

    // scala okręgi i nadpisuje atrybut okręgi[] nową tablicą scalonych okręgów
    public void scalOkręgi() {
        ArrayList<Okręg> okręgiPoScaleniu = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < okręgiDoScalenia.length; i++) {
            int numerPierwszego = okręgiDoScalenia[i];
            int indeksPierwszego = numerPierwszego - 1;
            System.out.println(numerPierwszego);
            Okręg scalony = okręgiPodstawowe[indeksPierwszego].scal(okręgiPodstawowe[indeksPierwszego + 1]);
            okręgiPoScaleniu.add(scalony);
            if(i + 1 < okręgiDoScalenia.length) {
                int numerPierwszegoNast = okręgiDoScalenia[i + 1];
                for (int k = indeksPierwszego + 2; k < numerPierwszegoNast - 1; k++) {
                    okręgiPoScaleniu.add(okręgiPodstawowe[k]);
                }
            }
        }
        int ostatni = okręgiDoScalenia[okręgiDoScalenia.length - 1]; // numer ost. elementu
        int indeksStart = ostatni + 2 - 1;
        for (int q = indeksStart; q < okręgiPodstawowe.length; q++) {
            okręgiPoScaleniu.add(okręgiPodstawowe[q]);
        }

        this.okręgi = okręgiPoScaleniu.toArray(new Okręg[okręgiPoScaleniu.size()]);
        this.okręgiDoScalenia = null;
    }

    public void przeprowadźKampanie() {
        for (int i = 0; i < partie.length; i ++) {
            if (partie[i].czyStaćNasNaKampanię(okręgi, działania)) {
                partie[i].przeprowadźkampanię(okręgi, działania);
            }
        }
    }

    public void przeprowadźWybory() {
        for (int i = 0; i < okręgi.length; i++) {
            okręgi[i].głosowanie();
        }
    }

    public void wypiszWynikiWyborów(MetodaPrzeliczaniaGłosów metodaMandatów) {
        for (int i = 0; i < okręgi.length; i++) {
            okręgi[i].podliczMandaty(metodaMandatów);
            okręgi[i].wypisGłosowania();
        }
        for (int j = 0; j < partie.length; j++) {
            int suma = 0;
            for (int k = 0; k < okręgi.length; k++) {
                suma += okręgi[k].dajMandatyPartii(partie[j].dajNazwę());
            }
            System.out.println(partie[j].dajNazwę() + " " + suma);
        }
    }

}
