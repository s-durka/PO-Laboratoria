package cover;

// zbiór, który pokrywamy
public class SetZ {
    private int maxNumber; //
    // zerowy indeks jest nieużywany dla wygody
    private boolean[] covered;
    private int howManyCovered; // ile jeszcze nie pokrytych

    public SetZ(int n) {
        maxNumber = n;
        howManyCovered = 0;
        covered = new boolean[n + 1];
        // zerowy indeks jest pomijany dla ułatwienia
        for (int i = 0; i < n + 1; i++)
            covered[i] = false;
    }

    // konstruktor klonujący obiekt
    public SetZ(SetZ Z1) {
        this.maxNumber = Z1.maxNumber;
        this.howManyCovered = Z1.howManyCovered;
        this.covered = new boolean[Z1.covered.length];
        System.arraycopy(Z1.covered, 0, this.covered, 0, Z1.covered.length);
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getHowManyCovered() {
        return howManyCovered;
    }

    // zwraca false jeśli liczba jest już pokryta
    public boolean isCovered(int value) {
        assert (value > this.maxNumber);
        return covered[value];
    }

    public void unmarkAll() {
        for (int i = 0; i < covered.length; i++)
            covered[i] = false;
        howManyCovered = 0;
    }

    public void cover(int index) {
        covered[index] = true;
        howManyCovered++;
    }

    public void unmark(int index) {
        assert (covered[index] = true);
        covered[index] = false;
        howManyCovered--;
    }

}
