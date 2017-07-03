/**
 * Created by mml on 17-7-3.
 */
public class ClassAndValue {
    public  String className;
    public  int times;

    public ClassAndValue(String a,int b)
    {
        className=a;
        times=b;
    }

    boolean compare(ClassAndValue object)
    {
        return times>object.times;
    }


}
