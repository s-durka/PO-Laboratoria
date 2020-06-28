package com.company;


import java.util.ArrayList;
import java.util.Arrays;

public class Mastermind {

    private int codeLength;

    private CodePeg[] possibleColors;

    private int maxNumberOfGuesses;

    private Codemaker codemaker;

    private Codebreaker codebreaker;

    private ArrayList<Row> historyOfGuesses;

    private void printHelp() {
        System.out.println("Welcome to mastermind");
        System.out.println("Enter " + codeLength + " characters separated by spaces");
        System.out.println("Colors to chose from: "+ Arrays.toString(possibleColors));
        System.out.println("Press h followed by enter to toggle help");
        System.out.println("q to quit");
        System.out.println("s to show answer");
        System.out.println("o = white key pegs");
        System.out.println("* = black key pegs");


    }

    public Mastermind(int codeLength){
        this.codeLength = codeLength;
        this.possibleColors = new CodePeg[] {new CodePeg("r"), new CodePeg("g"), new CodePeg("b")};
        System.out.println("possible colors: "+ Arrays.toString(possibleColors));

        this.maxNumberOfGuesses = 10;
        this.historyOfGuesses = new ArrayList<>();
        codemaker = new Codemaker(codeLength);
        codebreaker = new Codebreaker(codeLength);
    }

    public void game(){
        System.out.println();
        codemaker.setCode(possibleColors);
        int numberOfGuesses = 0;
        boolean guessed = false;
        boolean exit = false;

        printHelp();
        while(numberOfGuesses < maxNumberOfGuesses && !guessed && !exit) {
            try {
                int guessesLeft = maxNumberOfGuesses - numberOfGuesses;
                System.out.println("Guesses left: " + guessesLeft);
                Code guess = codebreaker.guess();

                System.out.println(guess);
                KeyPegs answer = codemaker.giveAnswer(guess);
                if(answer.getBlackPegs() == 4)
                    guessed = true;

                Row r = new Row(guess, answer);
                historyOfGuesses.add(r);

                System.out.println();
                for(Row row: historyOfGuesses)
                    System.out.println(row);

                numberOfGuesses++;
            }
            catch (WrongInput e) {
                System.out.println("Invalid input. Please enter " + codeLength + " characters separated by spaces");
                System.out.println("Colors to chose from: "+ Arrays.toString(possibleColors));
            }
            catch (HelpCalled e) {
                printHelp();
            }
            catch (ExitGame e) {
                System.out.println("Exit");
                exit = true;
            }
            catch (ShowAnswer e) {
                codemaker.printCode();
            }
        }
        if (guessed) System.out.println("Congratulations! you won!");
    }

}
