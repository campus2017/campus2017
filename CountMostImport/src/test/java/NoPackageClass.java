/**
 * Created by youthlin.chen on 2016-12-13 013.
 * 不带包名的类
 */
public class NoPackageClass {
    public static void staticMethod() {

    }

    public void commonMethod() {

    }

    public static class StaticClassOfNoPackageClass {
        public static void innerStaticMethod() {

        }

        public void innerCommonMethod() {

        }
    }

    public class innerClassOfNopackageClass {
        //不可含有static方法
        public void methodOfNoStaticInnerClass() {

        }
    }
}

