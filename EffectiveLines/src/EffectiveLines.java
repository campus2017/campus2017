/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-21
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class EffectiveLines {
    //**
    // * @param args
    // */
    private static int tatolLines;
    private static int whiteLines;
    private static int commentLines;
    // private static boolean bComment=false;
    public  static void computeLines(File file)
    {
        BufferedReader bf=null;
        try
        {
            bf=new BufferedReader(new FileReader(file));
            String lineStr="";
            while((lineStr=bf.readLine())!=null)
            {
                //总行数
                tatolLines++;
                //计算空行
                whiteLines(lineStr);
                //统计代码行数
                commendLines(lineStr);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件没有找到");
        }catch(IOException ee)
        {
            System.out.println("输入输出异常　");
        }finally
        {
            if(bf!=null)
            {
                try{
                    bf.close();
                    bf=null;
                }catch(Exception e)
                {
                    System.out.println("关闭BufferReader时出错");
                }
            }
        }
    }
    public static void whiteLines(String lineStr)
    {
        if(lineStr.matches("^[\\s&&[^\\n]]*$"))
        {
            whiteLines++;
        }
    }
    public static void commendLines(String lineStr)
    {
        if(lineStr.matches("^[\\s]*//.*"))
        {
            commentLines++;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File file=new File("D://javatest//text.java");
        computeLines(file);
        System.out.println("java文件中的注释行数为："+commentLines);
        System.out.println("java文件中的空行数为："+whiteLines);
        System.out.println("java文件中的有效代码行数为:"+(tatolLines-whiteLines-commentLines));
        System.out.println("java文件中的总行数为："+tatolLines);
    }

}


