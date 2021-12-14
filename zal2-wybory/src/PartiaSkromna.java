public class PartiaSkromna extends Partia {
    public PartiaSkromna(String nazwa, int budżet, int liczbaOkręgów) {
        super(nazwa, budżet, liczbaOkręgów);
    }

    @Override
    public void przeprowadźkampanię(Okręg[] okręgi, WektorCech[] działania) {
        Okręg najepszyO = null;
        WektorCech najlepszyD = null;
        int minKoszt = Integer.MAX_VALUE;
        for (int i = 0; i < okręgi.length; i++) {
            for (int j = 0; j < działania.length; j++) {
                int koszt = policzKosztDziałania(działania[j], okręgi[i]);
                if (this.budżet > koszt && koszt < minKoszt) {
                    najepszyO = okręgi[i];
                    najlepszyD = działania[j];
                    minKoszt = koszt;
                }
            }
        }
        assert(najepszyO != null && najlepszyD != null);
        this.budżet -= minKoszt;
        najepszyO.przeprowadźDziałanie(najlepszyD);
    }
}
