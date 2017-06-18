import java.io.*;

/**
 * Created by libo on 2017/6/5.
 *
 * 统计一个Java文件的有效行数    程序中不会检查源文件中的语法错误
 * 不考虑多行注释的情况(/\\*.*?\\* /    (?s)/\\*.*?\\* /  )
 * 有效行数不包括空行和单行注释的情况
 *
 */
public class EffectiveLines {

    /**
     * 检查文件的有效性
     * @param file
     * @return null表示验证成功，非null表示验证失败
     */
    private LinesInfo checkFile(File file){
        if (!file.exists()) {
            return new LinesInfo("文件不存在");
        }
        if (!file.getName().endsWith(".java")){
            return new LinesInfo("文件类型不支持，必须是java文件");
        }

        return null;
    }


    /**
     * 遍历每一行计算有效行数
     * @param reader
     * @return
     * @throws IOException
     */
    private int calcEffectiveLines(BufferedReader reader) throws IOException {
        int num = 0;
        String line = null;

        while((line = reader.readLine()) != null){
            //trim()可以去掉前置和后置的所有空白字符
            line = line.trim();
            if (line.matches("^\\s*$") || line.matches("^//[\\s\\S]*") ||
                line.matches("^/\\*[\\s\\S]*\\*/$")){
                continue;
            }

            ++num;
        }

        return num;
    }


    /**
     * 统计java文件的有效行数
     * @param filePath  给出文件的本机路径信息
     * @return 返回LinesInfo对象        getLine()获取有效行数
     *         如果发生错误（包括文件类型不支持、文件不存在等原因）  getLine()返回-1   getErrorMsg()获取错误的具体信息
     */
    public LinesInfo countLinesNum(String filePath){
        if (filePath == null || "".equals(filePath)){
            return new LinesInfo("非法的参数");
        }
        File file = new File(filePath);
        LinesInfo msg = checkFile(file);
        if (msg != null){
            return msg;
        }

        int num = -1;
        try {
            num = calcEffectiveLines(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            e.printStackTrace();
            return new LinesInfo("文件解析出现异常");
        }

        return new LinesInfo(num);
    }

}
