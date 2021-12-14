import java.util.LinkedList;

public class PartiaZachłanna extends Partia {
    public PartiaZachłanna(String nazwa, int budżet, int liczbaOkręgów) {
        super(nazwa, budżet, liczbaOkręgów);
    }

    private int policzSumęWażoną(WektorCech cechyKandydata, LinkedList<WektorCech> opinieWyborców) {
        int suma = 0;
        for (WektorCech w : opinieWyborców) {
            suma += w.iloczynSkalarny(cechyKandydata);
        }
        return suma;
    }

    private int policzSumęWażonąPoDziałaniu(WektorCech cechyKandydata, LinkedList<WektorCech> opinieWyborców, WektorCech działanie) {
        int suma = 0;
        for (WektorCech w : opinieWyborców) {
            WektorCech wZmieniony = w.dodaj(działanie); // klasa WektorCech dba o to, żeby wektor po dodaniu nie przekraczał granic [-100, 100]
            suma += wZmieniony.iloczynSkalarny(cechyKandydata);
        }
        return suma;
    }

    @Override
    public void przeprowadźkampanię(Okręg[] okręgi, WektorCech[] działania) {
        Okręg najepszyO = null;
        WektorCech najlepszyD = null;
        int najlepszaRóżnica = Integer.MIN_VALUE;
        int kosztNajlepszej = 0;

        for (int i = 0; i < okręgi.length; i++) {
            LinkedList<WektorCech> opinieWyborców = okręgi[i].przeprowadźSondaż();
            for (int j = 0; j < działania.length; j++) {
                int koszt = policzKosztDziałania(działania[j], okręgi[i]);
                if (this.budżet > koszt ) {
                    int sumaRóżnicKandydatów = 0;
                    ListaKandydatówPartii kandydaci = okręgi[i].dajListęKandydatówPartii(this.nazwa);
                    for (Kandydat k : kandydaci) {
                        // liczy różnicę sumy ważonej przed i po zastosowaniem działania
                        int sumaPrzed = policzSumęWażoną(k.przedstawPoglądy(), opinieWyborców);
                        int sumaPo = policzSumęWażonąPoDziałaniu(k.przedstawPoglądy(), opinieWyborców, działania[j]);
                        // różnica sum ważonych dla okręgi[i], działania[j]:
                        int różnicaKandydata = sumaPo - sumaPrzed; // im większa różnica tym lepsza zmiana
                        sumaRóżnicKandydatów += różnicaKandydata;
                    }
                    if (sumaRóżnicKandydatów > najlepszaRóżnica) {
                        najepszyO = okręgi[i];
                        najlepszyD = działania[j];
                        najlepszaRóżnica = sumaRóżnicKandydatów;
                        kosztNajlepszej = koszt;
                    }
                }
            }
        }
        assert(najepszyO != null && najlepszyD != null);
        this.budżet -= kosztNajlepszej;
        najepszyO.przeprowadźDziałanie(najlepszyD);
    }
}
