package cover;

import java.util.ArrayList;

public class ExactAlgorithm extends Solution {
    private int solutionLength;

    public ExactAlgorithm() {
        super();
        this.solutionLength = Integer.MAX_VALUE;
    }

    // funkcja rekurencyjna, pomocnicza w solve()
    private void findSetSum(ArrayList<Integer> chosenSets, ArrayList<SetABC> sets, SetZ Z, int index) {
        if (Z.getHowManyCovered() == Z.getMaxNumber()) {
            if (this.solutionLength >= chosenSets.size()) {
                ArrayList<Integer> copy = new ArrayList<>(chosenSets);
                this.solution = copy;
                this.solutionLength = copy.size();
            }
        } else if (index < sets.size()) {
            SetZ Z2 = new SetZ(Z); // kopiujemy zbiór do pokrycia
            SetABC currentSet = sets.get(index);
            int coveredComponents = currentSet.coverIntersection(Z2);
            findSetSum(chosenSets, sets, Z, index + 1);
            if (coveredComponents > 0) {
                chosenSets.add(index + 1);
                findSetSum(chosenSets, sets, Z2, index + 1);
                chosenSets.remove(chosenSets.size() - 1);
            } else {
                assert (coveredComponents == 0);
                findSetSum(chosenSets, sets, Z, index + 1);
            }
        }
    }

    public void solve(ArrayList<SetABC> sets, SetZ Z) {
        this.solution = null;
        ArrayList<Integer> optimalSoFar = new ArrayList<Integer>();
        SetZ Zcopy = new SetZ(Z); // kopiujemy zbiór który będziemy pokrywać
        findSetSum(optimalSoFar, sets, Zcopy, 0);
    }
}
