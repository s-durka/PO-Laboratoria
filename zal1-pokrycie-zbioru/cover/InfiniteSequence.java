package cover;

public class InfiniteSequence extends ArithmeticSequence {
    InfiniteSequence(int a_0, int difference) {
        super(a_0, difference);
        this.maxTerm = Integer.MAX_VALUE;
    }
}
