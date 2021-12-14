import java.util.ArrayList;
import java.util.Scanner;

public class CzytnikDanych {

    private Kandydat stwórzKandydata(String wiersz, int liczbaCech) {
        // zakłada poprawność danych
        String wSplit[] = wiersz.split(" ");
        String imię = wSplit[0];
        String nazwisko = wSplit[1];
        String imięNazwisko = imię + " " + nazwisko;
        int numerOkręgu = Integer.parseInt(wSplit[2]);
        String nazwaPartii = wSplit[3];
        int pozycjaNaLiście = Integer.parseInt(wSplit[4]);
        int[] cechy = new int[liczbaCech];
        for (int i = 0; i < liczbaCech; i++) {
            cechy[i] = Integer.parseInt(wSplit[4 + i]);
        }
        WektorCech wc = new WektorCech(cechy);
        Kandydat k = new Kandydat(numerOkręgu, nazwaPartii, pozycjaNaLiście, imięNazwisko, wc);
        return k;
    }

    private Wyborca stwórzWyborcę(String wiersz, int liczbaWag) {
        String wSplit[] = wiersz.split(" ");
        String imię = wSplit[0];
        String nazwisko = wSplit[1];
        String imięNazwisko = imię + " " + nazwisko;
        int numerOkręgu = Integer.parseInt(wSplit[2]);
        int typWyborcy = Integer.parseInt(wSplit[3]);
        Wyborca wyborca = null;
        switch (typWyborcy) {
            case 1:
                String nazwaPartii = wSplit[4];
                wyborca = new ŻelaznyElektoratPartyjny(imięNazwisko, numerOkręgu, nazwaPartii);
                break;
            case 2:
                String nazwaPartii2 = wSplit[4];
                int numerKandydata = Integer.parseInt(wSplit[5]);
                wyborca = new ŻelaznyElektoratKandydata(imięNazwisko, numerOkręgu, nazwaPartii2, numerKandydata);
                break;
            case 3:
                wyborca = new MinJednocechowyBezpartyjny(Integer.parseInt(wSplit[4]), numerOkręgu, imięNazwisko);
                break;
            case 4:
                wyborca = new MaksJednocechowyBezpartyjny(Integer.parseInt(wSplit[4]), numerOkręgu, imięNazwisko);
                break;
            case 6:
                wyborca = new MinJednocechowyPartyjny(Integer.parseInt(wSplit[4]), numerOkręgu, imięNazwisko, wSplit[5]);
                break;
            case 7:
                wyborca = new MaksJednocechowyPartyjny(Integer.parseInt(wSplit[4]), imięNazwisko, numerOkręgu, wSplit[5]);
                break;
            case 5:
                int[] wagi = new int[liczbaWag];
                for (int i = 0; i < liczbaWag; i++) {
                    wagi[i] = Integer.parseInt(wSplit[4 + i]);
                }
                WektorCech WektorWag = new WektorCech(wagi);
                wyborca = new WszechstronnyBezpartyjny(imięNazwisko, numerOkręgu, WektorWag);
                break;
            case 8:
                int[] wagi2 = new int[liczbaWag];
                for (int i = 0; i < liczbaWag; i++) {
                    wagi2[i] = Integer.parseInt(wSplit[4 + i]);
                }
                WektorCech WektorWag2 = new WektorCech(wagi2);
                String nazwaP = wSplit[liczbaWag];
                wyborca = new WszechstronnyPartyjny(nazwaP, numerOkręgu, imięNazwisko, WektorWag2);
                break;
        }
        return wyborca;
    }


    public Bajtocja czytajDane() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // liczba podstawowych okręgów
        int p = input.nextInt(); // liczba partii
        int d = input.nextInt(); // liczba możliwych działań
        int c = input.nextInt(); // liczba cech kanydatów

        Partia[] partie = new Partia[p];
        Okręg[] okręgi = new Okręg[n];
        int[] liczbyWyborcówWOkręgach = new int[n];
        ListaWyborcza[] listyWyborczeOkręgów = new ListaWyborcza[n];

        // 2 wiersz

        int scalanePary = input.nextInt();
        // TUTAJ KOD PARSUJĄCY PARY (o, o+1).......
        String wiersz2 = input.nextLine();
        String[] pary = wiersz2.split(" ");
        int[] pierwszeNumery = new int[pary.length - 1];
        for (int i = 1; i < pary.length; i++) {
            // dzielimy na 2 stringi przecinkiem, obcinamy nawias z przodu i zmieniamy na int
            pierwszeNumery[i-1] = Integer.parseInt(pary[i].split(",")[0].substring(1));
        }
        int scalaneOkręgi[] = pierwszeNumery; // z par (o, o+1) trzyma o

        // 3 wiersz
        String[] nazwyPartii = input.nextLine().split(" "); // powinno ich być p
        // 4 wiersz
        int[] budżetyPartii = new int[p];
        for (int i = 0; i < p; i++) {
            budżetyPartii[i] = input.nextInt();
        }
        // 5 wiersz
        String[] strategiePartii = new String[p];
        for (int i = 0; i < p; i++) {
            strategiePartii[i] = input.next();
        }

        // tworzenie partii, po kolei w tablicy w partie[]
        for (int i = 0; i < p; i++) {
            switch(strategiePartii[i]) {
                case "R":
                    partie[i] = new PartiaZRozmachem(nazwyPartii[i], budżetyPartii[i], n);
                    break;
                case "S":
                    partie[i] = new PartiaSkromna(nazwyPartii[i], budżetyPartii[i], n);
                    break;
                case "Z":
                    partie[i] = new PartiaZachłanna(nazwyPartii[i], budżetyPartii[i], n);
                    break;
                case "W":
                    partie[i] = new MojaPartia(nazwyPartii[i], budżetyPartii[i], n);
                    break;
            }
        }

        // 6 wiersz - liczby wyborców w okręgach
        for (int i = 0; i < n; i++) {
            liczbyWyborcówWOkręgach[i] = input.nextInt();
        }
        input.nextLine(); // pobiera znak końca linii

        // następne wiersze:
        // liczba kandydatów partii w danym okręgu = liczba wyborców w okręgu / 10

        // czytanie wierszy dla okręgu:

        for (int o = 0; o < n; o++) { // n = liczba okręgów
            ListaKandydatówPartii[] listyPartiiWTymOkręgu = new ListaKandydatówPartii[p];
            int liczbaKandydatów = liczbyWyborcówWOkręgach[o] / 10;
            for (int j = 0; j < p; j++) { // p = liczba partii
                // tworzymy nową ListęKandydatówPartii
                String nazwaPartii = nazwyPartii[j];
                Kandydat[] kandydaciPartii = new Kandydat[liczbaKandydatów];
                for (int k = 0; k < liczbaKandydatów; k++) {
                    String wiersz = input.nextLine();
                    Kandydat kand = stwórzKandydata(wiersz, c);
                    kandydaciPartii[k] = kand;
            }
                ListaKandydatówPartii listaKandydatówPartii = new ListaKandydatówPartii(nazwaPartii, o, kandydaciPartii);
                listyPartiiWTymOkręgu[j] = listaKandydatówPartii;
            }
            ListaWyborcza listaWyborcza = new ListaWyborcza(listyPartiiWTymOkręgu);
            listyWyborczeOkręgów[o] = listaWyborcza;
        }


        // teraz dodawani są wyborcy do okręgów:
        ArrayList<Wyborca>[] wyborcyO = new ArrayList[n];
        for (int o = 0; o < n; o++) {
            ArrayList<Wyborca> wyborcyWOkręgu = new ArrayList<Wyborca>();
            for (int w = 0; w < liczbyWyborcówWOkręgach[o]; w++) { // wyborcy w orkęgach - tablica liczb wyborców w okręgach
                String wiersz = input.nextLine();
                Wyborca wyborca = stwórzWyborcę(wiersz, c);
                wyborcyWOkręgu.add(wyborca);
            }
            wyborcyO[o] = wyborcyWOkręgu;
        }

        // teraz mamy tablicę list wyborczych dla okręgów po kolei
        // oraz tablicę list wyborców dla okręgów po kolei

        for (int i = 0; i < n; i++) {
            okręgi[i] = new Okręg(i+1, listyWyborczeOkręgów[i], wyborcyO[i], p);
        }

        WektorCech[] działania = new WektorCech[d];
        for (int i = 0; i < d; i++) {
            int cechyTab[] = new int[c];
            for (int j = 0; j < c; j++) {
                cechyTab[j] = input.nextInt();
            }
            działania[i] = new WektorCech(cechyTab);
        }
        return new Bajtocja(okręgi, scalaneOkręgi, partie, działania);
    }
}