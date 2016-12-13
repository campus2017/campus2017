package com;

// 不能 import! 错误：找不到符号 JDK 1.4 起，不能导入不带包名的类
// @see http://blog.csdn.net/xyz_fly/article/details/8178104
// import static NoPackageClass.*;

/**
 * Created by youthlin.chen on 2016-12-13 013.
 * TestClass
 */
public class TestClass {
    public static final int A = 0;
    public static final int a = 0;

    public static void staticMethod() {
    }

    public void commonMethod() {

    }

    public static class InnerStaticClass {
        public static void innerStaticMethod() {

        }

        public void innerCommonMethod() {

        }
    }

    public class InnerClass {
        //不可含有static方法
        public void methodOfInnerClass() {

        }
    }
}
