import java.io.*;

/**
 * Created by isc on 2016/11/20.
 */
public class EffectiveLines {

    public static void main(String[]args){
        String filePath = "G:\\IDEA_Workspace_2016_12_16\\EffectiveLines\\src\\Test2.java";
        System.out.println(calculateLinesAccordingPath(filePath));
    }

    private static String calculateLinesAccordingPath(String path) {
        File file = new File(path);
        return countCodeLines(file);
    }

    /**
     *
     * @param file
     * @return 行数
     */
    private static String countCodeLines(File file){
        int lines=0;
        //判断是否为.java文件
        if(isJavaFile(file)){
            FileReader filereader = null;
            BufferedReader bufferReader=null;
            try{
                filereader = new FileReader(file);
                bufferReader = new BufferedReader(filereader);
                String lineContent;
                while(bufferReader.ready()){
                    //去掉开头可能存在的空格
                    lineContent = bufferReader.readLine().trim();
                   if(isBlankLine(lineContent))
                        continue;
                    if(isAnnotation(lineContent))
                        continue;
                    lines++;
                }
            }catch (Exception e){
                return e.getMessage();
            }finally {
                closeReader(filereader,bufferReader);
            }
        }
        return String.valueOf(lines) ;
    }

    private static boolean isJavaFile(File file){
        if(file.getName().matches(".*\\.java$")) {
            return true;
        }
        return false;
    }

    private static boolean isBlankLine(String lineContent){
        if(lineContent.matches("^[\\s && [^\\n]]*$"))
            return true;
        return false;
    }

    /*   注释的情况:
    * 1. /* 2. // 3. * 4.  */
    private static boolean isAnnotation(String lineContent){

        if((lineContent.matches("^/\\*.*")))  //   表示/*  \\表示转义  \\* —> *
            return true;
        if (lineContent.matches("^//.*"))    //  表示//
            return true;
        if(lineContent.matches("^\\*.*"))    // 表示 *
            return true;
        if(lineContent.matches(".*\\*/"))  //表示  */
            return true;
        return  false;
    }

    private static void closeReader(FileReader fileReader,BufferedReader bufferedReader){
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
