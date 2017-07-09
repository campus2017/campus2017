package domain;

import java.util.Map;

/**
 * Created by manlixin on 2017/6/28.
 */
public class Statistics {
    private Integer abcs;
    private Integer numbers;
    private Integer chinese;
    private Integer punctuation;
    private Map<String, Integer> top3;

    public Statistics() {
    }

    public Statistics(int abcs, int numbers, int chinese, int punctuation, Map<String, Integer> top3) {
        this.abcs = abcs;
        this.numbers = numbers;
        this.chinese = chinese;
        this.punctuation = punctuation;
        this.top3 = top3;
    }

    public int getAbcs() {
        return abcs;
    }

    public void setAbcs(int abcs) {
        this.abcs = abcs;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public Map<String, Integer> getTop3() {
        return top3;
    }

    public void setTop3(Map<String, Integer> top3) {
        this.top3 = top3;
    }
}
