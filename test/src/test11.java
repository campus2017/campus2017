/**
 * Created by Administrator on 2017/6/30.
 */

import com.qunar.fresh2017.exam1.Exam1;

public class test11 {
    public static void main(String[] args) throws Exception{
    Exam1 examsss=new Exam1();
    String path="F:/Qunar/unorderedmsg.txt";
    examsss.process(path);
    examsss.output1("orderedmsg.txt");
    examsss.output2("count.txt");
    }
}
