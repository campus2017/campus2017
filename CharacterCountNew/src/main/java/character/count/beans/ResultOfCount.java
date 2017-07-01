package character.count.beans;

import java.util.List;

/**
 * Created by hughgilbert on 30/06/2017.
 */
public class ResultOfCount {
    private int letters;
    private int chinese;
    private int number;
    private int punctuation;
    private List<PairOfChinese> chineseWordsList;
    /*
        Consturctor
     */
    public ResultOfCount(){

    }
    /*
        setter
     */
    public void setLetters(int letters) {
        this.letters = letters;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public void setChineseWordsList(List<PairOfChinese> chineseWordsList) {
        this.chineseWordsList = chineseWordsList;
    }

    /*
        getter
     */

    public int getLetters() {
        return letters;
    }

    public int getChinese() {
        return chinese;
    }

    public int getNumber() {
        return number;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public List<PairOfChinese> getChineseWordsList() {
        return chineseWordsList;
    }
}