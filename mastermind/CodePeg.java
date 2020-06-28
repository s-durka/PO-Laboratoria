package com.company;


public class CodePeg {
    private String color;
    public String getColor() {
        return color;
    }

    public CodePeg(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodePeg codePeg = (CodePeg) o;
        return color.equals(((CodePeg) o).getColor());
    }

    @Override
    public String toString(){
        return color;
    }
}
