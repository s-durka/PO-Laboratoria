public class LiczbyNieparzyste implements GeneratorLiczb {

    @Override
    // zakłada, że poprz. liczba jest nieparzysta
    public int dajNastępnąLiczbę(int aktLiczba) {
        return aktLiczba + 2;
    }
}
