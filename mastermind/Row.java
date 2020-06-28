package com.company;


public class Row {
    private Code code;
    private KeyPegs keyPegs;

    public Row(Code guess, KeyPegs keyPegs) {
        this.code = guess;
        this.keyPegs = keyPegs;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(code.toString());
        sb.append("| ");
        sb.append(keyPegs.toString());

        return sb.toString();
    }
}