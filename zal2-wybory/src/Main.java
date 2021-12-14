public class Main {

    public static void main(String[] args) {
        CzytnikDanych czytnik = new CzytnikDanych();
        Bajtocja bajtocja = czytnik.czytajDane();
        bajtocja.scalOkręgi();
        bajtocja.przeprowadźKampanie();
        bajtocja.przeprowadźWybory();

        MetodaPrzeliczaniaGłosów m1 = new MetodaDHonta();
        MetodaPrzeliczaniaGłosów m2 = new MetodaSainteLague();
        MetodaPrzeliczaniaGłosów m3 = new MetodaHareaNiemeyera();
        bajtocja.wypiszWynikiWyborów(m1);
        bajtocja.wypiszWynikiWyborów(m2);
        bajtocja.wypiszWynikiWyborów(m3);
    }
}
