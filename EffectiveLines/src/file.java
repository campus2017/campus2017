import java.io.*;
public class file {
    public static void main(String args[]) throws IOException {
        File directory=new File("");
        System.out.println("java文件不存在！");
        File file = new File(directory.getCanonicalPath()+"/file.java");
        if(file.exists()){
            System.out.println("java文件的有效行数："+countLines(file));
        }
        else
        {
           // System.out.println("java文件不存在！");
           /* return;*/
        }

    }

    //计算有效行数
    private static int countLines(File file) throws IOException {
        int num=0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            //一次读入一行直到读入null结束
            while((temp=reader.readLine())!=null){
                //判断有效行
                if(temp.equals("")|| temp.startsWith("//")||(temp.startsWith("/*")&&temp.endsWith("*/"))){
                    ;
                }
                else{
                    num++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
            return num;
        }

    }

}
