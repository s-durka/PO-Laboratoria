package cover;

import java.util.ArrayList;

public class NaiveHeuristic extends Solution {

    public NaiveHeuristic() {
        super();
    }

    public void solve(ArrayList<SetABC> sets, SetZ Z) {
        SetZ Zcopy = new SetZ(Z);
        int i = 0;
        while (!(Zcopy.getHowManyCovered() == Zcopy.getMaxNumber()) && i < sets.size()) {
            SetABC currentSet = sets.get(i);
            int common = currentSet.coverIntersection(Zcopy);
            if (common > 0) {
                this.solution.add(i + 1);
            }
            i++;
        }
        if (Zcopy.getHowManyCovered() < Zcopy.getMaxNumber()) {
            this.solution = null; // rozwiązanie nie pokrywa całego zbioru
        }
    }

}
