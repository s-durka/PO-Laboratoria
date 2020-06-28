package com.company;

public class KeyPegs {
    private int whitePegs;
    private int blackPegs;

    public KeyPegs(int white, int black) {
        this.whitePegs = white;
        this.blackPegs = black;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int w = 0; w < whitePegs; w++) {
            ret.append(" o ");
        }
        for (int b = 0; b < blackPegs; b++) {
            ret.append(" * ");
        }
        return ret.toString();
    }

    public int getWhitePegs() {
        return whitePegs;
    }
    public int getBlackPegs() {
        return blackPegs;
    }
}