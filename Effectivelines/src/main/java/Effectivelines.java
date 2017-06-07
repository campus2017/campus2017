import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.common.base.Preconditions;


/**
 * Created by hughgilbert on 2017/5/9.
 */
public class Effectivelines {
    private int totalLines;
    private int invalidLines;
    private String fileName;


    /*
        constructor
     */
    public Effectivelines(String fileName) throws IllegalArgumentException
    {

        //检查是否是java文件
        Preconditions.checkArgument(fileName.endsWith(".java"),"%s is not java file",fileName);
        this.totalLines = 0;
        this.invalidLines = 0;
        this.fileName = fileName;
    }

    /*
        private function
     */
    private void caculator() throws IOException
    {
        File file = new File(fileName);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String ContentOfTheLine = null;
            //读取文件内容
            while((ContentOfTheLine = reader.readLine()) != null)
            {
                //去除首尾空格
                ContentOfTheLine = ContentOfTheLine.trim();
                this.totalLines++;


                if(isEmpty(ContentOfTheLine) == true)
                {
                    this.invalidLines++;
                }
                else if(ContentOfTheLine.startsWith("/**") || ContentOfTheLine.startsWith("/*") )
                {
                    this.invalidLines++;
                    isMultipleAnnotation(reader);
                }
                else if(isSignleAnnotation(ContentOfTheLine) == true)
                {
                    this.invalidLines++;
                }
            }

        }
        catch(IOException e)
        {
            throw e;
        }

    }

    //判断是否是空行
    private boolean isEmpty(String line)
    {
        return (line.length() == 0);
    }

    //判断是否是多行注释或者文档注释
    private void isMultipleAnnotation(BufferedReader reader) throws IOException
    {
        try {
            String lineOfMultipleAnnotation = "";
            while (!((lineOfMultipleAnnotation = reader.readLine()).trim().endsWith("*/"))) {
                this.totalLines++;
                this.invalidLines++;
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        this.totalLines++;
        this.invalidLines++;
    }

    //判断是否是单行注释
    private boolean isSignleAnnotation(String line)
    {
        return line.startsWith("//");
    }

    /*
        public function
     */
    public int getEffectiveLinesOftheFile() throws IOException
    {

        try {
            this.caculator();
            return totalLines - invalidLines;
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    public void show()
    {
        System.out.println("totalLines : " + totalLines + "  " + "invalidLines : " + invalidLines);
    }

}
