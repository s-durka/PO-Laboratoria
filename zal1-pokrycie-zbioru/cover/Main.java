package cover;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Solution solution = null;
        Scanner input = new Scanner(System.in);
        String state = "start";
        SetABC S = null;
        SetZ Z = null;
        ArrayList<SetABC> setFamily = new ArrayList<>();
        int arg1, arg2, arg3, c;
        arg1 = arg2 = arg3 = c = 0;
        while (input.hasNext()) {
            switch (state) {
                case "start":
                    c = input.nextInt();
                    if (c > 0) {
                        S = new SetABC();
                        setFamily.add(S);
                        state = "new component";
                    } else if (c == 0) {
                        S = new SetABC();
                        setFamily.add(S);
                        state = "start";
                    } else {
                        assert (c < 0);
                        Z = new SetZ((-1) * c);
                        state = "solution";
                    }
                    break;
                case "solution":
                    c = input.nextInt();
                    if (c == 1) {
                        solution = new ExactAlgorithm();
                    } else if (c == 2) {
                        solution = new GreedyHeuristic();
                    } else {
                        assert (c == 3);
                        solution = new NaiveHeuristic();
                    }
                    solution.solve(setFamily, Z);
                    System.out.println(solution);
                    state = "start";
                    break;
                case "new component": // został pobrany już 1 parametr elementu
                    arg1 = c;
                    c = input.nextInt();
                    if (c < 0) {
                        state = "new sequence";
                    } else {
                        // dodaj element o wartości arg1
                        Component element = new Element(arg1);
                        SetABC lastSet = setFamily.get(setFamily.size() - 1); // ostatni indeks
                        lastSet.addComponent(element);
                        if (c == 0) {
                            // koniec zbioru
                            state = "start";
                        } else
                            state = "new component";
                    }
                    break;
                case "new sequence":
                    arg2 = (-1) * c;
                    c = input.nextInt();
                    if (c >= 0) {
                        SetABC lastSet = setFamily.get(setFamily.size() - 1); // ostatni indeks
                        InfiniteSequence seq = new InfiniteSequence(arg1, arg2);
                        lastSet.addComponent(seq);
                        if (c == 0) {
                            // koniec zbioru
                            state = "start";
                        }
                        if (c > 0)
                            state = "new component";
                    } else { // c < 0
                        state = "finite sequence";
                    }
                    break;
                case "finite sequence":
                    arg3 = (-1) * c;
                    c = input.nextInt();
                    assert (c >= 0);
                    if (c == 0)
                        state = "start";
                    else
                        state = "new component";
                    FiniteSequence seq = new FiniteSequence(arg1, arg2, arg3);
                    SetABC lastSet = setFamily.get(setFamily.size() - 1); // ostatni indeks
                    lastSet.addComponent(seq);
                    break;
            }
        }
    }
}
