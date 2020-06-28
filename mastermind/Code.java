package com.company;

public class Code {
    private CodePeg[] code;

    public int getCodeLength() {
        return code.length;
    }

    public Code(int codeLength) {
        this.code = new CodePeg[codeLength];
    }

    public Code(String[] newCode) {
        code = new CodePeg[newCode.length];
        int i = 0;
        for(String s: newCode){
            code[i] = new CodePeg(s);
            i++;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(CodePeg p: code) {
            sb.append(p);
            sb.append(" ");
        }
        return sb.toString();
    }

    private boolean contains(CodePeg cp) {
        for (int i = 0; i < code.length; i++) {
            if (cp.equals(code[i])) return true;
        }
        return false;
    }

    private boolean hasPegAtIndex(CodePeg peg, int index) {
        if (code[index].equals(peg)) return true;
        return false;
    }

    public KeyPegs compare(Code c2) {
        int white = 0;
        int black = 0;
        for (int i = 0; i < code.length; i++) {
            if (c2.hasPegAtIndex(code[i], i))
                black++;
            else if (c2.contains(code[i]))
                white++;
        }
        return new KeyPegs(white, black);
    }
}