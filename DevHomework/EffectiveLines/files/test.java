import java.io.*;

/**
 * Created by muhongfen on 17/4/24.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        File file = new File("EffectiveLines/files/test.txt");
        if(!(file.exists())){
            System.out.println("file not exist");
            return;
        }
        else
        {
            System.out.println("有效代码行数："+countEffective(file));
        }

    }
    public static int countEffective(File file) throws IOException {
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

    public static boolean isEffective(String line){
        if(line.equals("")|| line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/"))){
            return false;
        }
        return true;
    }
}
