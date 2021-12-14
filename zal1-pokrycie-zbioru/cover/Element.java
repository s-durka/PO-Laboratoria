package cover;

public class Element extends Component {
    // element == singleton
    private int value;

    public int getValue() {
        return value;
    }

    public Element(int value) {
        this.value = value;
    }

    // zaznacza liczbę w Z jako covered jeśli value należy do Z (i nie był zaznaczony jako covered)
    // dodaje zaznaczony indeks do listy coveredIndexes
    // oraz zwraca liczbę zaznaczonych (0 lub 1)
    public int coverIntersection(SetZ Z) {
        int n = Z.getMaxNumber();
        if (n < this.value) return 0;
        if (Z.isCovered(this.value)) return 0;
        // else:
        Z.cover(this.value);
        return 1;
    }
}
