import java.io.*;

public class Main {



    public static void main(String[] args) {
	// write your code here
        File file = new File("resources/demo.java");
        checkExit(file);
    }

    private static void checkExit(File file){
        if(!file.exists()){
            System.out.println("the file is not exits");
        }else {
            getLines(file);
        }
    }

    private static void getLines(File file){
        BufferedReader br = null;
        FileReader fr = null;
        String line = null;
        int count = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while((line = br.readLine())!= null){
                if(getResult(line)){
                    count++;
                }
            }
            System.out.print("文件有效行数为："+count+"行");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                fr.close();
                br.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    private static boolean getResult(String line){
        if(line.startsWith("/*")||line.startsWith("*")||line.endsWith("*/")||line.startsWith("//")){
            return false;
        }
        else return true;
    }
}
