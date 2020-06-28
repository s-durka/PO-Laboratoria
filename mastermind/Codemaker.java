package com.company;


import java.util.Random;

public class Codemaker {

    private Code myCode; // 4 elementowy ciąg kolorów

    public void setCode(CodePeg[] possibleColors) {
        Random r = new Random();
        String[] newCode = new String[4];
        for(int i = 0; i < myCode.getCodeLength(); i++) {
            int index = r.nextInt(possibleColors.length);
            newCode[i] = possibleColors[index].getColor();
        }
        myCode = new Code(newCode);
    }

    public void printCode()  {
        System.out.println(myCode);
    }

    public Codemaker(int codeLength) {
        this.myCode = new Code(codeLength);
    }

    public KeyPegs giveAnswer(Code guess) {
        return guess.compare(myCode);
    }
}
