package EffectiveLiness;

import java.io.*;

/**
 * Created by 曹 on 2017/5/16.
 */
public class EffectiveLines {

    //构造函数
    public EffectiveLines(){

    }

    /**
     * 统计java文件的有效行数
     * @param inFile
     * 输入文件
     * @throws Exception
     * 抛出文件找不到等异常
     * @return int line
     * 文件有效行数
     */
    public static int fileStaticsLine(File inFile) throws Exception{
        if(inFile==null||!inFile.exists()){
            throw new FileNotFoundException("文件不存在");
        }
        if(!(inFile.isFile()&&inFile.getName().endsWith(".java"))){
            throw new Exception("文件错误");
        }
        int line = 0;
        BufferedReader br=null;
        try{
            //文件读入
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(inFile)
            ));
            String s = null;
            while((s=br.readLine())!=null){
                //将空格，回车，换行等空白符转换为空字符（去掉空白符）
                s = s.replaceAll("\\s","");
                //判断注释和空行
                if("".equals(s)
                        ||s.startsWith("//")
                        ||s.startsWith("/*")
                        ||s.startsWith("/**")
                        ||s.startsWith("*")){

                }else{
                    line++;
                }
            }

        }catch (IOException e){
            //流读取失败
            throw new RuntimeException("读取文件失败"+e);
        }finally {//finally除特殊情况一定会关闭，保证流关闭
            try{
                //关闭流
                br.close();
            }catch (IOException e){
                //流关闭失败
                throw new RuntimeException("关闭文件流失败");
            }
        }
        System.out.println(inFile.getName()+" "+line);
        return line;
    }


    //统计总文件数
    public static int sumFile = 0;
    //统计总有效行数
    public static int sumLine = 0;
    /**
     * 统计文件夹的有效行数
     * @param inFile
     * 输入文件夹
     * @throws Exception
     * 抛出文件夹找不到等异常
     */
    public static void staticsLine(File inFile) throws Exception{
        //统计单个文件有效行数
        int line=0;
        for(File file:inFile.listFiles()){
            if(file.isFile()&&file.getName().endsWith(".java")){
                line =fileStaticsLine(file);
                sumFile++;
                sumLine += line;
            }else if(file.isDirectory()){
                staticsLine(file);
            }
        }
        System.out.println(sumFile+" "+sumLine);
    }
    //测试
    public static void main(String[] args) {
        EffectiveLines effectiveLines = new EffectiveLines();
        try{
            File inFile = new File("F:\\test");
            effectiveLines.staticsLine(inFile);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
