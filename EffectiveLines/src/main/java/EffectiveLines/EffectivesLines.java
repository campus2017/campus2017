package EffectiveLines;

import java.io.*;

/**
 * 功能：统计一个java文件的有效行数
 * 要求：1）有效不包括空行
 *       2）不考虑代码有多行注释的情况
 * 作者：Yung
 * 时间：2016-12-1
 */
public class EffectivesLines
{
    static int effectiveLines=0; //统计有效行
    static int commentLines=0;   //统计注释行
    static int whiteLines=0;     //统计空行
    public static void main( String[] args )
    {
        File file=new File("F:\\work\\SEI-Pro\\src\\com\\controller\\"); //通过指定路径读取java文件
        File[] codeFiles= file.listFiles();
        for(File child:codeFiles){
            if(child.getName().matches(".*\\.java$")){   //判断当前文件是否以.java结尾
                parse(child);
            }
        }
        System.out.println("effectiveLines:"+effectiveLines);  //打印有效代码行数
    }

    /*功能：判断是否是有效行*/
    private static void parse(File f){
        BufferedReader br=null;
        boolean comment=false;
        try {
            br=new BufferedReader(new FileReader(f));
            String line="";
            while((line=br.readLine())!=null){
                line=line.trim();
                if(line.matches("^[\\s&&[^\\n]]*$")){  //判断是否为空行
                    whiteLines++;
                }else if(line.startsWith("/*")&&!line.endsWith("*/")){  //判断是否为注释行
                    commentLines++;
                    comment=true;
                }else if(line.startsWith("/*")&& line.endsWith("*/")){  //判断是否为注释行
                    commentLines++;
                }else if(true==comment){
                    commentLines++;
                    if(line.endsWith("*/")){                             //判断是否为注释行
                        comment=false;
                    }
                }else if(line.endsWith("//")){                           //判断是否为注释行
                    commentLines++;
                }else{
                    effectiveLines++;                                     //统计有效行
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                   br.close();
                    br=null;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
