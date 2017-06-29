import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by isc on 2016/11/18.
 */
public class Test2 {

    public static void main(String[]args){
        //
        matchEmail();
        //
    }
//
    /**
     * 邮箱正则表达式检测
     */
    static void matchEmail(){
//        String str = "aaa@aaa.com.com.com";//true
//        String str1="111aa@aa.com";//true
//        String str12="a111@aa.com";//true
//        String str13="185230@163.com";
//        String regEx = "[a-zA-Z_]{0,}[0-9]{0,}[a-zA-Z_]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
//        Pattern pattern = Pattern.compile(regEx);
//        // 忽略大小写的写法
//        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
//        Matcher matcher1 = pattern.matcher(str1);
//        Matcher matcher2 = pattern.matcher(str1);
//        Matcher matcher3 = pattern.matcher(str1);
//        Matcher matcher4 = pattern.matcher(str13);
//        // 字符串是否与正则表达式相匹配
//        boolean rs = matcher4.matches();

        if("..*...*".matches("^\\*.*"))    // 表示 *
            System.out.println("true");
        else
            System.out.println("false");
    }

    static void method1(){
        String regular_expression = "Java now has regular expressions";
        String []a = {"^Java","\\Breg.*","n.w\\s+h(a|i)s","s?","s*","s+","s{4}","s{1}","s{0,3}"};
//        print(args[0]);
        print("Regular expression :\""+regular_expression+"\"");
        for(String arg:a){
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(regular_expression);
            print("--------------------------------------------------------->");
            while(m.find()){
                print("Match \""+m.group()+"\" at position " + m.start() +" _ "+(m.end()-1));
            }
        }
    }

    static void method2(){
        String input = "This!!unusual use!!of exclamation!!points";
        print(Arrays.toString(Pattern.compile("!!").split(input)));
        print(Arrays.toString(Pattern.compile("!!").split(input,3)));//第二个参数是返回数组的最大长度
    }

    static void print(String s){
        System.out.println(s);
    }
}
