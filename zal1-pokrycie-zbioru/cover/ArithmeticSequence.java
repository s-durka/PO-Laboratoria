package cover;

public abstract class ArithmeticSequence extends Component {
    protected int a_0;
    protected int difference;
    protected int maxTerm;

    public ArithmeticSequence(int a_0, int difference) {
        this.a_0 = a_0;
        this.difference = difference;
    }

    public int coverIntersection(SetZ Z) {
        int maxValue;
        if (maxTerm < Z.getMaxNumber()) maxValue = maxTerm;
        else maxValue = Z.getMaxNumber();
        int count = 0;
        for (int i = a_0; i <= maxValue; i += difference) {
            if (!Z.isCovered(i)) {
                Z.cover(i);
                count++;
            }
        }
        return count;
    }
}
