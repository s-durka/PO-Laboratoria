public class PartiaZRozmachem extends Partia {
    public PartiaZRozmachem(String nazwa, int budżet, int liczbaOkręgów) {
        super(nazwa, budżet, liczbaOkręgów);
    }

    @Override
    public void przeprowadźkampanię(Okręg[] okręgi, WektorCech[] działania) {
        Okręg najepszyO = null;
        WektorCech najlepszyD = null;
        int maksKoszt = Integer.MIN_VALUE;
        for (int i = 0; i < okręgi.length; i++) {
            for (int j = 0; j < działania.length; j++) {
                int koszt = policzKosztDziałania(działania[j], okręgi[i]);
                if (this.budżet > koszt && koszt > maksKoszt) {
                    najepszyO = okręgi[i];
                    najlepszyD = działania[j];
                    maksKoszt = koszt;
                }
            }
        }
        assert(najepszyO != null && najlepszyD != null);
        this.budżet -= maksKoszt;
        najepszyO.przeprowadźDziałanie(najlepszyD);
    }
}
