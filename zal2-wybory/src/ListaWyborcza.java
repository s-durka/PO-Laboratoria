public class ListaWyborcza {
    private ListaKandydatówPartii[] listyPartii; // listy wyborcze partii
    private int licznik; // licznik po listach wyborczych partii

    public ListaWyborcza(ListaKandydatówPartii[] lkp) {
        this.listyPartii = lkp;
    }
    // lista daje po kolei listy kandydatów następnych partii
    public void zacznijIterować() {
        this.licznik = 0;
    }
    public boolean hasNext() {
        if (licznik >= listyPartii.length) return false;
        return true;
    }

    public ListaWyborcza połączListy(ListaWyborcza l2) {

        ListaKandydatówPartii[] noweListy = new ListaKandydatówPartii[this.listyPartii.length];
        for(int i = 0; i < noweListy.length; i++) {
            assert(listyPartii[i].dajNazwęPartii().equals(l2.listyPartii[i].dajNazwęPartii()));
            noweListy[i] = this.listyPartii[i].scal(l2.listyPartii[i]);
        }
        return new ListaWyborcza(noweListy);
    }

    public ListaKandydatówPartii dajNastępnąPartię(){
        int i = licznik;
        ListaKandydatówPartii l = listyPartii[i];
        licznik++;
        return l;
    }
}
