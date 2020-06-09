package com.company;

public class Ogród {
    private Warzywo warzywa[];
    private Integer ilośćWarzyw;
    private Integer rozmiarTablicy; // tablicy warzywa[]

    public Ogród(int rozmiar) {
        this.warzywa = new Warzywo[rozmiar];
        this.rozmiarTablicy = rozmiar;
        this.ilośćWarzyw = 0;
    }
    public int dajRozmiarTablicy() {
        return rozmiarTablicy;
    }

    public int dajIlośćWarzyw() {
        return this.ilośćWarzyw;
    }

    public boolean posadźWarzywo(Warzywo w) {
        // jeśli ogród jest pełny, zwraca false
        // w przeciwnym wypadku sadzi warzywo na 1-szym wolnym miejscu i zwraca true
        // oraz zwiększa ilośćWarzyw
        if (ilośćWarzyw >= rozmiarTablicy)
            return false;
        else {
            int i = 0;
            while (warzywa[i] != null)
                i++;
            warzywa[i] = w;
            ilośćWarzyw++;
            return true;
        }
    }

    public Warzywo zerwijWarzywo(int i, int czas) {
        // Ustala wartość warzywa oraz wstawia null w jego miejsce w tablicy
        // zwraca warzywo pod danym indeksem lub null jeśli miejsce jest puste
        if (warzywa[i] == null) return null;
        warzywa[i].oceńWartość(czas); // oceńWartość ustala atrybut "wartość" warzywa
        Warzywo w = warzywa[i];
        w.zaznaczŻeZebrane(); // od tej pory metoda warzywa OceńWartość nie będzie zmieniać wartości zależnie od czasu
        warzywa[i] = null;
        this.ilośćWarzyw--;
        return w;
    }

    // jeśli nie ma warzywa pod danym indeksem, zwraca INT_MAX
    public Integer dajWartośćWarzywa(int i, int czas) {
        if (warzywa[i] == null) return Integer.MAX_VALUE;
        return warzywa[i].oceńWartość(czas); // oceńWartość ustala atrybut "wartość" warzywa
    }

    public Integer dajRóżnicęWartościWarzywa(int i, int czas) {
        if (warzywa[i] == null) return Integer.MAX_VALUE;
        return warzywa[i].porównajWartość(czas); // oceńWartość ustala atrybut "wartość" warzywa
    }
}
