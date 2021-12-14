package cover;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyHeuristic extends Solution {
    public GreedyHeuristic() {
        super();
    }

    public void solve(ArrayList<SetABC> sets, SetZ Z) {
        ArrayList<Integer> solution = new ArrayList<>();
        Boolean[] available = new Boolean[sets.size()];
        for (int i = 0; i < available.length; i++)
            available[i] = true;
        int checkedCount = 0; // zlicza ilość elementów tablicy available o wartości true

        SetZ Zstate = new SetZ(Z); // uaktualniany przy każdym przejściu przez listę zbiorów (przy wyborze nowego zbioru do rozwiązania

        while (checkedCount < sets.size() || Z.getMaxNumber() == Z.getHowManyCovered()) {
            SetZ Zcandidate = null;
            int maxIntersection = 0; // przechowuje największe przecięcie w tym obiegu
            int indexOfNextBestSet = -1; // zmieniane na indeks w tablicy sets
            for (int j = 0; j < available.length; j++) {
                if (available[j] == true) {
                    SetABC S = sets.get(j);
                    SetZ newZ = new SetZ(Zstate); // kopia zbioru Z na której będą zaznaczane poprzednio i nowo pokryte elementy
                    int intersection = S.coverIntersection(newZ);
                    if (intersection == 0) {
                        available[j] = false;
                        checkedCount++;
                    } else {
                        if (intersection > maxIntersection) {
                            // ten zbiór jest kandydatem do dodania do rozwiązania
                            indexOfNextBestSet = j;
                            maxIntersection = intersection;
                            Zcandidate = newZ; // zapisuje stan zbioru Z
                        }
                    }
                }
            }
            if (indexOfNextBestSet != -1) {
                Zstate = Zcandidate; // nadpisuje stan zbioru do pokrycia
                available[indexOfNextBestSet] = false;
                checkedCount++;
                solution.add(indexOfNextBestSet + 1);
            }
        }
        if (Zstate.getMaxNumber() > Zstate.getHowManyCovered())
            solution = null;
        if (solution != null)
            Collections.sort(solution);
        this.solution = solution;
    }
}
