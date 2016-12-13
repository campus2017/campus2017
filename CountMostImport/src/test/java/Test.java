import com.TestClass;

import static com.TestClass.*;
import static com.TestClass.InnerStaticClass.innerStaticMethod;

//import static com.*;
//import static java.math.*;

/**
 * Created by youthlin.chen on 2016-12-13 013.
 * TestClass
 */
public class Test {
    public static void main(String[] args) {
        staticMethod();
        innerStaticMethod();
        TestClass.InnerClass innerClass = new TestClass().new InnerClass();
        int i = A;
        i = a;
    }
}
