import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.google.common.base.Preconditions;

/**
 * Created by Administrator on 2017/6/9.
 */

public class EffectiveLines {
    private int totalLines;
    private int invalidLines;
    private String filePath;
    private boolean isClosed;//多行注释是否关闭

    public static void main(String[] args) {

        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("please input the file path (input q to quit): ");
            String filePath = in.next();
            if(filePath.equals("q")) break;
            try {
                EffectiveLines el = new EffectiveLines(filePath);
                int result = el.computeEffectiveLines();
                System.out.println("effective lines is : " + result);
                el.showDetail();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 构造器
     * @param filePath
     * @throws IllegalArgumentException
     */
    private EffectiveLines(String filePath) throws IllegalArgumentException {
        this.totalLines = 0;
        this.invalidLines = 0;
        this.filePath = filePath;
        this.isClosed = true;
    }

    /**
     * 返回有效行数
     * @return
     * @throws IOException
     */
    private int computeEffectiveLines() throws IOException {
        this.computeLines();
        return totalLines-invalidLines;
    }

    /**
     * 计算java文件的无效行数和总行数
     * @throws IOException
     */
    private void computeLines() throws IOException {
        //检查文件的合法性
        Preconditions.checkArgument(filePath.endsWith(".java"),"%s is not java file",filePath);
        File file = new File(filePath);
        Preconditions.checkArgument(file.exists(),"the file is not exist");
        Preconditions.checkArgument(file.isFile(),"it's not a standard file");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        //计算文件总行数和无效行数
        try {
            String lineStr = "";
            //逐行读取文件内容
            while((lineStr = reader.readLine()) != null) {
                //总行数
                this.totalLines++;
                //如果是空行，无效行加1
                if(isEmptyLine(lineStr)) {
                    this.invalidLines++;
                }
                //如果是多行注释，无效行加1
                //是多行注释且未关闭（/**...、/*...）
                else if(isUnclosedMultiNoteLine(lineStr)) {
                    this.invalidLines++;
                    this.isClosed = false;
                }
                else if(!this.isClosed) {
                    //多行注释关闭
                    if(isClosedMultiNoteLine(lineStr)) {
                        this.isClosed = true;
                    }
                    this.invalidLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 判断是否是空行
     * @param lineStr
     * @return
     */
    private boolean isEmptyLine(String lineStr) {
        return Pattern.matches("^[\\s&&[^\\n]*\\n$]", lineStr) ||
            Pattern.matches("\\s*", lineStr);
    }

    /**
     * 判断是否是未关闭的多行注释
     * @param lineStr
     * @return
     */
    private boolean isUnclosedMultiNoteLine(String lineStr) {
        return Pattern.matches("\\s*/\\*+.*(?<!\\*/)$",lineStr);
    }

    /**
     * 判断是否是关闭的注释
     * @param lineStr
     * @return
     */
    private boolean isClosedMultiNoteLine(String lineStr) {
        return Pattern.matches("\\s*.*\\*/+\\s*",lineStr);
    }

    /**
     * 显示详细信息（总行数，无效行数）
     */
    private void showDetail() {
        System.out.println("totalLines is : " + totalLines + "  " + "invalidLines is : " + invalidLines);
    }

}
