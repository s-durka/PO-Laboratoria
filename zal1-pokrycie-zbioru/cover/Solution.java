package cover;

import java.util.ArrayList;

public abstract class Solution {
    protected ArrayList<Integer> solution;

    @Override
    public String toString() {
        if (solution == null || solution.isEmpty())
            return 0 + "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solution.size() - 1; i++) {
            sb.append(solution.get(i));
            sb.append(" ");
        }
        sb.append(solution.get(solution.size() - 1));
        return sb.toString();
    }

    public Solution() {
        this.solution = new ArrayList<>();
    }

    public abstract void solve(ArrayList<SetABC> sets, SetZ Z);
}
