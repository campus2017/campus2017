package kevin.vo;

/**
 * Created by Kevin on 2017/6/25.
 */
public class CountResult {
    int engCount;
    int numCount;
    int chineseCount;
    int punctuationCount;

    public CountResult() {
    }

    public CountResult(int engCount, int numCount, int chineseCount, int punctuationCount) {
        this.engCount = engCount;
        this.numCount = numCount;
        this.chineseCount = chineseCount;
        this.punctuationCount = punctuationCount;
    }

    public int getEngCount() {
        return engCount;
    }

    public void setEngCount(int engCount) {
        this.engCount = engCount;
    }

    public int getNumCount() {
        return numCount;
    }

    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }

    public int getChineseCount() {
        return chineseCount;
    }

    public void setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }

    public void setPunctuationCount(int punctuationCount) {
        this.punctuationCount = punctuationCount;
    }

    @Override
    public String toString() {
        return "CountResult{" +
                "engCount=" + engCount +
                ", numCount=" + numCount +
                ", chineseCount=" + chineseCount +
                ", punctuationCount=" + punctuationCount +
                '}';
    }
}
