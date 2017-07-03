package charactercounter.Entity;

/**
 * Created by admin on 2017/2/7.
 */
public class CharacterCount implements Comparable {
    private char ch;
    private int count;

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(Object o) {
        if (o instanceof CharacterCount) {
            CharacterCount cc = (CharacterCount)o;
            return cc.count - count;
        }
        return -1;
    }

    public String toString() {
        return ch+": "+count;
    }
}
