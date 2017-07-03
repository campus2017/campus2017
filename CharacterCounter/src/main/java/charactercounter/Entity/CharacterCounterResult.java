package charactercounter.Entity;

/**
 * Created by admin on 2017/2/6.
 */
public class CharacterCounterResult {
    private int English;
    private int number;
    private int Chinese;
    private int punctuation;
    private CharacterCount[] top3;

    public CharacterCount[] getTop3() {
        return top3;
    }

    public void setTop3(CharacterCount[] top3) {
        this.top3 = top3;
    }

    public int getEnglish() {
        return English;
    }

    public void setEnglish(int english) {
        English = english;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getChinese() {
        return Chinese;
    }

    public void setChinese(int chinese) {
        Chinese = chinese;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public String toString() {
        String str = English+" " +number+" "+Chinese+" "+punctuation;
        for (int i=0; i<top3.length; ++i) {
            str += " "+top3[i].toString();
        }
        return str;
    }
}
