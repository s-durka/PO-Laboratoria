import java.util.LinkedList;

public abstract class MetodaDzielników implements MetodaPrzeliczaniaGłosów {
    protected GeneratorLiczb generatorDzielników;
    // konstruktory - w podklasach

    // wstawia element typu comparable do listy posortowanej nierosnąco
    private <T extends Comparable> void wstawSort(LinkedList<T> listaMalejąca, T element) {
        int i = 0;
        while (i < listaMalejąca.size() && listaMalejąca.get(i).compareTo(element) > 0) {
            // iterujemy tak długo, jak i-ty element listy jest większy od naszego
            i++;
        }
        if (listaMalejąca.size() == i)
            listaMalejąca.add(element); // dodaj na koniec
        else
            listaMalejąca.add(i, element); // dodaj na i-ty indeks
    }

    // zwraca tablicę par w postaci <nazwa partii, liczba uzyskanych przez nią mandatów>
    private Para<String, Integer>[] podliczMandaty(int liczbaMandatów, LinkedList<Para<String, Integer>> listaMalejąca, Para<String, Integer>[] tablicaGłosówPom) {
        for (int i = 0; i < tablicaGłosówPom.length; i++) {
            tablicaGłosówPom[i].setDrugi(0); // ustawiamy liczbę mandatów na zero
        }

        for (int j = 0; j < liczbaMandatów && j < listaMalejąca.size(); j++) {
            String nazwaPartii = listaMalejąca.get(j).getPierwszy();
            // szukanie pary dla naszej partii:
            boolean znaleziona = false;
            for (int l = 0; l < tablicaGłosówPom.length  && !znaleziona; l++) {
                String nazwaDrugiejPartii = tablicaGłosówPom[l].getPierwszy();
                if (nazwaPartii.equals(nazwaDrugiejPartii)) {
                    znaleziona = true;
                    tablicaGłosówPom[l].setDrugi(tablicaGłosówPom[l].getDrugi() + 1); // zwiększamy liczbę mandatów o jeden
                }
            }
        }
        return tablicaGłosówPom;
    }


    @Override
    public Para<String, Integer>[] rozdajMandaty(int liczbaMandatów, Para<String, Integer>[] tablicaGłosów) {
        // wykonuje na wartościach z tablicy głosów operacje pomocnicze, dodając pary <nazwaPartii, liczbaMandatów> do listy pomocniczej
        // w kolejności niemalejącej
        // ostatecznie przechodzi przez tą listę (posortowaną nierosnąco), i rozdziela kolejno mandaty, wpisując je do
        // tablicy liczbaMandatówPom[] poprzez funkcję podliczMandaty()
        // zwraca liczbaMandatówPom[]

        int dzielnik = 1;
        LinkedList<Para<String, Integer>> listaMalejąca = new LinkedList();
        boolean mandatyObsadzone = false;
        while(!mandatyObsadzone) {
            int maksWartośćWObiegu = 0;
            for (int i = 0; i < tablicaGłosów.length; i++) {
                int nowyIloraz = tablicaGłosów[i].getDrugi() / dzielnik;
                String nazwaPartii = tablicaGłosów[i].getPierwszy();
                Para<String, Integer> ilorazPara = new Para<>(nazwaPartii, nowyIloraz);
                wstawSort(listaMalejąca, ilorazPara);

                if (nowyIloraz > maksWartośćWObiegu)
                    maksWartośćWObiegu = nowyIloraz;

                dzielnik = generatorDzielników.dajNastępnąLiczbę(dzielnik); // jedyna różnica między metodą D'Hondta a Sainte-Laguea
            }
            if (listaMalejąca.size() >= liczbaMandatów) {
                if (maksWartośćWObiegu < listaMalejąca.get(liczbaMandatów - 1).getDrugi())
                    mandatyObsadzone = true; // nie otrzymamy już ilorazu wystarczająco dużego na zdobycie mandatu
            }
        }
        return podliczMandaty(liczbaMandatów, listaMalejąca, tablicaGłosów); // zwracamy tablicę par <nazwa partii, liczba uzyskanych mandatów>
    }
}
