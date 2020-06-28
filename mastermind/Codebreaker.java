package com.company;

import java.util.Scanner;

public class Codebreaker {
    private int codeLength;
    private Scanner in = new Scanner(System.in);

    public Codebreaker(int codeLength) {
        this.codeLength = codeLength;
    }

    public Code guess() throws WrongInput, HelpCalled, ExitGame, ShowAnswer {
        String[] code = in.nextLine().split(" ");
        if (code.length != codeLength) {
            if (code.length == 1) {
                // programista założył, że użytkownik szanuje się i nie będzie grał z kodem dł. 1 :)
                if (code[0].equals("h"))
                    throw new HelpCalled();
                else if (code[0].equals("q"))
                    throw new ExitGame();
                else if (code[0].equals("s"))
                    throw new ShowAnswer();
            }
            throw new WrongInput();
        }
        return new Code(code);
    }
}

