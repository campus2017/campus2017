package task1;

import java.io.*;
public class EffectiveLines {
    /*
        测试
     */
    public static void main(String[] args){
        String fileName = "task1_data.txt";
        int lineCnt = EffectiveLines.calEffectiveLines(fileName);
        System.out.println(lineCnt);
    }
    /*
        将fileName包装成BufferedReader实例并返回
     */
    private static BufferedReader getReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);
        return bf;
    }
    /**
     *  1、如果文件不存在，则直接退出。
        2、逐行读取，然后将当前行的前后空白去掉：
            如果是当前行是空行，或者以//开头则直接跳过，否则结果加一。
        3、文件读取出错或者关闭出错，则打印提示，结果无效。
     * @param fileName 待处理的文件
     * @return 行数
     */
    public static int calEffectiveLines(String fileName) {
        BufferedReader bf = null;
        try {
            bf = getReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(fileName + "不存在");
            System.exit(1);
        }
        String line = null;
        int lineCnt = 0;
        try {
            while((line = bf.readLine()) != null){
                line = line.trim();
                if(line.length() == 0 || line.startsWith("//")){
                    continue;
                }
                lineCnt++;
            }
        } catch (IOException e) {
            System.out.println("文件读取时出错");
        }
        finally{
            try {
                bf.close();
            } catch (IOException e) {
                System.out.println("关闭" + fileName + "失败");
            }
        }
        return lineCnt;
    }
}