import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
       //String pathName = "G:\\ideaworkspaces\\characterCounter\\src\\main\\java\\controller\\CharacterCounterController.java";
        String pathName = "G:\\JavaEE\\spring-ioc\\src\\ioc01\\Main.java";
        System.out.println(numbersOfEffectiveLines(pathName));
    }
    public static Integer numbersOfEffectiveLines(String pathName){
        Integer linesNum = 0;
        //判断该文件是否是java文件（以.java结尾）
        if(isJavaFile(pathName)){
            String str = null;
            try {
                FileReader fr = new FileReader(pathName);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null ){
                    line = br.readLine();
                    if (line != null){
                        if ( "".equals(line))
                            continue;
                        if (doEffectiveLinesCount(line))
                            continue;
                        linesNum++;
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return linesNum;
    }

    private static boolean doEffectiveLinesCount(String line){
        //多行注释开始/*匹配一位或无限位
        Pattern multiLinesCommentsPrefix = Pattern.compile("\\s*/\\*.*");
        Pattern multiLinesCommentsMiddle = Pattern.compile("\\s*\\*.*");
        Pattern multiLinesCommentsEnd = Pattern.compile(".*\\*/\\s");

        //单行注释//
        Pattern singleLineComments = Pattern.compile("\\s*//.*");

        if (multiLinesCommentsPrefix.matcher(line).matches()){
            return true;
        }
        if (multiLinesCommentsMiddle.matcher(line).matches()){
            return true;
        }
        if (multiLinesCommentsEnd.matcher(line).matches()){
            return true;
        }
        if (singleLineComments.matcher(line).matches()){
            return true;
        }

        return false;
    }

    /**
     *  判断目标文件是否为java文件
     * @param pathName
     * @return
     */
    private static boolean isJavaFile(String pathName){

        int lastIndexOf = pathName.lastIndexOf(".");

        //取出后缀
        String suffix = pathName.substring(lastIndexOf);

        if (".java".equals(suffix)){
            return true;
        }
        return false;
    }

}
