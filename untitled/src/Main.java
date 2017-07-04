import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

//import java.nio.file.Files;



public class Main {
    public static void main(String[] args) throws IOException {
        countLines("./src/Main.java");
        //System.out.println("Hello World!")
    }

    public static void countLines(String fileName) throws IOException {
        int countLines = 0;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一行");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.startsWith("//") || tempString.equals("")||tempString==null)
                {
                    continue;
                }
                else {
                countLines++;}
            }
            System.out.print(countLines);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (reader!=null){
                try{
                    reader.close();
                }
                catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
        }
    }

