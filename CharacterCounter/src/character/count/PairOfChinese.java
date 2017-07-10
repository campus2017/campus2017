package character.count;

import java.text.Collator;
import java.util.Locale;

/**
 * Created by admin on 2017/7/10.
 */

public class PairOfChinese implements Comparable<PairOfChinese>{
    private String chinese;
    private int count;
    /*
        consturctor
     */

    public PairOfChinese(String chinese,int count){
        this.chinese = chinese;
        this.count = count;
    }

    /*
        setter
     */

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /*
        getter
     */

    public String getChinese() {
        return chinese;
    }

    public int getCount() {
        return count;
    }

    /*
        compareTo
     */
    public int compareTo(PairOfChinese object) {

        if (object.getCount() == this.getCount()) {

            Collator instance = Collator.getInstance(Locale.CHINA);

            return instance.compare(this.getChinese(), object.getChinese());

        } else {
            return object.getCount() - this.getCount();

        }
    }
}
