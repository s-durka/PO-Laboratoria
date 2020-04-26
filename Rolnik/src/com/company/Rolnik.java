package com.company;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;


public abstract class Rolnik {
    private ArrayList<Warzywo> zebraneWarzywa;
    private Integer pieniądze;

    public Rolnik(int pieniądze) {
        this.pieniądze = pieniądze;
        this.zebraneWarzywa = new ArrayList<Warzywo>();
    }

    public void sprzedajWarzywa() {
        for (int i=0; i<zebraneWarzywa.size(); i++) {
            Warzywo w = zebraneWarzywa.get(i);
            this.pieniądze += w.oceńWartość(0);
        }
    }

    public Integer spytajOCenę(Ogród o, int indeks_warzywa, int czas) {
        return o.dajWartośćWarzywa(indeks_warzywa, czas);
    }

    public boolean zerwijWarzywo(Ogród o, int indeks_warzywa, int czas) {
        // zwraca false, jeśli pod danym indeksem nie ma warzywa
        // jeśli jest warzywo, dodaje je do listy zebrane warzywa, ustala jego wartość, zwraca true;
        Warzywo w = o.zerwijWarzywo(indeks_warzywa, czas);
        if (w == null) return false;
        zebraneWarzywa.add(w);
        System.out.println("Zebrałem: " + w.toStringZebranie());
        return true;
    }

    public boolean posadź(Ogród o, Warzywo w) {
        // jeśli nie ma miejsca w ogrodzie, zwraca false
        // wpp. zwraca true oraz:
        // sadzi warzywo na pierwszym wolnym miejscu w ogrodzie lub
        // jeśli rolnik nie ma wystarczająco pieniędzy na posadzenie warzywa pisze, że nie ma pieniędzy
        // pisze "Posadziłem: <nazwa warzywa (koszt)> oraz odejmuje koszt posadzenia od swojego konta jeśli udało się posadzić
        if (this.pieniądze < w.dajKosztPosadzenia()) {
            System.out.println("Nie mam wystarczająco pieniędzy na posadzenie: " + w.toStringSadzenie() + ":(");
            return false;
        }

        boolean ret = o.posadźWarzywo(w);
        if (ret) {
            this.pieniądze -= w.dajKosztPosadzenia();
            System.out.println("Posadziłem: " + w.toStringSadzenie());
        }
        return ret;
    }
//
    public boolean posadźLosoweWarzywo(Ogród o, int aktCzasSekundy) {
        Random rand = new Random();
        int numer_warzywa = rand.nextInt(3);
        if (numer_warzywa == 0) return posadź(o, new Ziemniak(aktCzasSekundy));
        else if (numer_warzywa == 1) return posadź(o, new Pomidor(aktCzasSekundy));
        else return posadź(o, new Marchewka(aktCzasSekundy));
    }

    public abstract void taktykaRolnika(int aktCzasSekundy, Ogród o) throws InterruptedException;

    // rolnik najpierw sadzi, wydając pieniądze, i dopiero pod koniec sezonu sprzedaje swoje warzywa
    // - nie jest wystarczająco obrotny, żeby sprzedawać na bierząco
    public void symuluj(int czasSymulacji, Ogród o) throws InterruptedException {
        System.out.println("Symuluję: " + this.toString());
        czasSymulacji *= 1000; // zmiana z sekund na milisekundy
        czasSymulacji = (int)czasSymulacji;
        long start = System.currentTimeMillis();
        long aktCzas = 0;
        while (aktCzas < czasSymulacji && this.pieniądze > 0) {
            aktCzas = System.currentTimeMillis() - start;
            int aktCzasSekundy = (int)aktCzas/1000;
            System.out.println("czas w sekundach: " + aktCzasSekundy);
            taktykaRolnika(aktCzasSekundy, o);
        }
        if (this.pieniądze == 0)
            System.out.println("Wydałem wszystkie pieniądze.");
        sprzedajWarzywa();
        System.out.println("Nowy bilans konta: " + this.pieniądze);
    }

    public Integer sprawdźZmianęWartościWarzywa(Ogród o, int indeks_warzywa, int czas) {
        return o.dajRóżnicęWartościWarzywa(indeks_warzywa, czas);
    }


}

class pracownikPGR extends Rolnik {
    // kosntruktor
    public pracownikPGR(int pieniądze) {
        super(pieniądze);
    }

    public String toString() {
        return "PracownikPGR";
    }

    public void taktykaRolnika(int aktCzasSekundy, Ogród o) throws InterruptedException {
        boolean jestMiejsceWOgrodzie = true;
        while (jestMiejsceWOgrodzie)
            jestMiejsceWOgrodzie = this.posadźLosoweWarzywo(o, aktCzasSekundy);

        for (int j = 0; j < o.dajRozmiarTablicy(); j++) {
            this.zerwijWarzywo(o, j, aktCzasSekundy);
        }
        Thread.sleep(10000);
    }
}

class Gospodarz extends Rolnik {
    public Gospodarz(int pieniądze) {
        super(pieniądze);
    }

    public String toString() {
        return "Gospodarz";
    }

    public void taktykaRolnika(int aktCzasSekundy, Ogród o) throws InterruptedException {
        boolean jestMiejsceWOgrodzie = true;

        for (int i = 0; i < o.dajRozmiarTablicy(); i++) {
            int zmianaWCenie = sprawdźZmianęWartościWarzywa(o, i, aktCzasSekundy);
            if (zmianaWCenie != Integer.MAX_VALUE) {
                if (zmianaWCenie < 0) {
                    zerwijWarzywo(o, i, aktCzasSekundy);
                    posadźLosoweWarzywo(o, aktCzasSekundy);
                }
            }
            else
                posadźLosoweWarzywo(o, aktCzasSekundy);
        }
        Thread.sleep(1000);
    }
}

    // czas podawany w sekundach
//    public void symuluj(int czas_symulacji, Ogród o) throws InterruptedException {
//        czas_symulacji *= 1000; // zmiana z sekund na milisekundy
//        czas_symulacji = (int)czas_symulacji;
//        long start = System.currentTimeMillis();
//        long aktCzas = 0;
//        while (aktCzas < czas_symulacji) {
//            aktCzas = System.currentTimeMillis() - start;
//            System.out.println(aktCzas);
//            int aktCzasSekundy = (int)aktCzas/1000;
//            System.out.println("czas w sekundach: " + aktCzasSekundy);
//            boolean jestMiejsceWOgrodzie = true;
//            while (jestMiejsceWOgrodzie)
//                 jestMiejsceWOgrodzie = this.posadź(o, new Ziemniak(aktCzasSekundy));
//
//            for (int j = 0; j < o.dajRozmiarTablicy(); j++) {
//                this.zerwijWarzywo(o, j, aktCzasSekundy);
//            }
//            Thread.sleep(10000);
//        }
//    }
//}



