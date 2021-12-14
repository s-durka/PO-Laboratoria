public abstract class Partia {
    // tablica kandydatów posortowanych względem okręgów wyborczych:
    // lista (tablica) wyborcza pod i-1 - szym indeksem jest listą i-tego okręgu
    protected String nazwa;
    protected int budżet;
    public Partia(String nazwa, int budżet, int liczbaOkręgów) {
        this.nazwa = nazwa;
        this.budżet = budżet;
    }

    public String dajNazwę() {
        return nazwa;
    }

    public void setBudżet(int budżet) {
        this.budżet = budżet;
    }



    protected int policzKosztDziałania(WektorCech działanie, Okręg o) {
        return działanie.sumaModułów() * o.dajLiczbęWyborców();
    }

    public abstract void przeprowadźkampanię(Okręg[] okręgi, WektorCech[] działania);

    public boolean czyStaćNasNaKampanię(Okręg[] okręgi, WektorCech[] działania) {
        boolean stać = false;
        for (int i = 0; i < okręgi.length; i++) {
            for (int j = 0; j < działania.length; j++) {
                if (policzKosztDziałania(działania[j], okręgi[i]) < this.budżet)
                    stać = true;
            }
        }
        return stać;
    }
}
