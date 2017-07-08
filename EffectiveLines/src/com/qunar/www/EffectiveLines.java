package com.qunar.www;

import java.io.*;

/**
 * Created by ZC on 2017/6/29.
 */
public class EffectiveLines {

    public static void main(String[] args){
        if(args.length==0 || args[0].trim().equals("")){
            System.out.println("输入文件地址");
            return;
        }
        int num=0;
        BufferedReader br = null;
        File file = new File(args[0]);
        //获取编码
        String code = getCode(file);
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),code));
            String line= null;
            String lineTrim = null;
            while((line = br.readLine()) != null){
                lineTrim = line.trim();
                if(lineTrim.startsWith("//") || "".equals(lineTrim)){
                }else{
                    num++;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }
    finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println(num);
    }

    /**
     *
     * @param file
     * @return code编码
     * 检测文件编码
     */
    public static String getCode(File file)  {
        BufferedInputStream bin = null;
        String code = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        } finally{
            try {
                bin.close();
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
                e.printStackTrace();
            }
        }
        return code;

    }
    }
