import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyText {
    //文本的行数
    public int _textLine;
    //文本的路径名称
    public String _fileName;

    /**
     * 分析是否为有效行
     * @param line 待分析的行
     * @return 如果为有效行则返回1， 否则返回0
     */
    private Boolean isValideLine(String line)
    {
        line = line.trim();
        if(line.length() < 1 || line.startsWith("//"))
            return false;
        else
            return true;
    }

    /**
     * 根据名称，打开文本，并统计文本的行数
     * @param fileName 文本的路径名称
     * @return 返回该文件的有效行数
     */
    private int coutFileLine(String fileName)
    {
        int currentLine = 0;
        try {
            // 打开文本
            BufferedReader textReader = new BufferedReader(new FileReader(fileName));

            // 读取文本的每一行
            while(true)
            {
                // 读取该行
                Optional<String> one_line = Optional.fromNullable(textReader.readLine());
                // 读取未结束
                if(one_line.isPresent())
                {
                    // 分析是否为有效行
                    if(isValideLine(one_line.get()))
                        currentLine++;
                }
                // 读取结束
                else
                {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentLine;
    }

    /**
     * 构造函数. 打开文本，统计文本行数，并记录
     * @param fileName
     */
    public MyText(String fileName)
    {
        try {
            // 读取文本
            _textLine = coutFileLine(fileName);

            // 记录文本名称
            _fileName = fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
