import java.io.*;

/**
 * Created by muhongfen on 17/4/24.
 * 统计一个java文件的有效行数：有效不包括空行，不考虑代码间有多行注释的情况
 */
public class Main {
    public static void main(String args[]) throws IOException {
        File file = new File("EffectiveLines/files/test.java");
        if(!(file.exists())){
            System.out.println("file not exist");
            return;
        }
        else
        {
            System.out.println("有效代码行数："+countEffective(file));
        }

    }

    //统计有效行数
    private static int countEffective(File file) throws IOException {
        int num=0;
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = buffer.readLine())!=null){
                if(isEffective(line))
                {
                    num++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            buffer.close();
            return num;
        }

    }

    //判断有效
    private static boolean isEffective(String line){
        if(line.equals("")|| line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/"))){
            return false;
        }
        return true;
    }
}
