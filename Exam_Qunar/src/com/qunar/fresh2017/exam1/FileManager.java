package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.*;
import java.lang.StringBuilder;

/**
 * Created by Administrator on 2017/4/14.
 */
public class FileManager {

    public static String m_basePath = "";

    public static boolean FileValidation(){
        boolean ret = false;

        m_basePath = FileManager.class.getResource("").getPath();;
        boolean flag = true;
        Scanner in = new Scanner(System.in);
        File file = null;

        while(flag){
            file = new File(m_basePath +"unorderedmsg.txt");

            if(file.exists()){
                flag = false;
                ret = true;
            }
            else {
                FileManager.PrintLog("unorderedmsg.txt不存在当前的路径下,输入1重新检查，输入0退出程序");

                while(true){
                    try {
                        int input = in.nextInt();

                        if(input == 0){
                            flag = false;
                            break;
                        }
                        else if(input == 1){
                            break;
                        }
                        else{
                            FileManager.PrintLog("输入错误，请重新输入");
                        }
                    }
                    catch (Exception ex){
                        FileManager.PrintLog("输入错误，请重新输入");
                    }
                }
            }

            file = null;
        }

        return ret;
    }

    //parse the input file
    public static ArrayList<Message> FileParser(){
        ArrayList<Message> list = new ArrayList<Message>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader( new FileInputStream(m_basePath + "unorderedmsg.txt"),"GB2312"));
            String temp = null;

            while ((temp = reader.readLine()) != null) {
                if(!temp.trim().equals("")){
                    Message msg = GetMessage(temp);

                    if(msg == null){
                        continue;
                    }

                    list.add(msg);
                }
            }

            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return list;
    }

    //count the msg
    public static HashMap<String,Integer> Count(ArrayList<Message> list){
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        for (Message msg:list) {
            if(map.containsKey(msg.Name)){
                map.put(msg.Name,map.get(msg.Name)+1);
            }
            else{
                map.put(msg.Name,1);
            }
        }

        return map;
    }

    //output the msg
    public static boolean Output(ArrayList<Message> list, List<Map.Entry<String,Integer>> mapList){

        StringBuilder orderMsg, countResult ;
        orderMsg = new StringBuilder("");
        countResult = new StringBuilder("");

        int len = list.size();

        for(int i = 0; i < len; i++){
            orderMsg.append(FileManager.GetMessageLine(list.get(i)));
        }

        for(Map.Entry<String, Integer> entry : mapList){
            countResult.append(entry.getKey());
            countResult.append("    ");
            countResult.append(entry.getValue());
            countResult.append("\r\n");
        }

        //output msg to file
        if(!FileManager.WriteByFile(m_basePath + "orderedmsg.txt",orderMsg.toString()) ||
                !FileManager.WriteByFile(m_basePath + "count.txt",countResult.toString())){
            return false;
        }

        return true;
    }

    public static boolean WriteByFile(String path, String content) {
        FileWriter fw = null;
        File file = null;
        boolean ret = false;

        try {
            file = new File(path);
            if(file.exists()){
                file.delete();
            }

            fw = new FileWriter(path);

            fw.append(content + "\n");
            ret = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fw != null) {
                try
                {
                    fw.close();
                    fw = null;
                }
                catch (Exception ex){

                }
            }

            if(file != null){
                file = null;
            }
        }

        return ret;
    }

    public static void PrintLog(String log){
        System.out.println(log);
    }

    private static String GetMessageLine(Message msg){
        String ret = "";

        if(msg != null){

            ret = msg.Name + "    " + msg.MsgDate + "    " + msg.MsgContent + "\r\n";
        }

        return ret;
    }

    private static Message GetMessage(String msg){
        Message ret = null;
        int len = msg.length();
        int index = 0;
        char tmp = ' ';
        StringBuilder entity = new StringBuilder("");
        String[] msgEntity = new String[3];

        for(int i = 0; i < len; i++){
            tmp = msg.charAt(i);

            if(tmp == ' ' && index < 2){

                // 以四个空格作为间隔
                if(i+1 < len && msg.charAt(i+1) == ' '){
                    i += 3;

                    msgEntity[index++] =  entity.toString().trim();
                    entity = new StringBuilder("");
                }
            }

            entity.append(tmp);
        }

        msgEntity[index] =  entity.toString();

        ret = new Message();
        ret.Name = msgEntity[0];
        ret.MsgDate = msgEntity[1];
        ret.MsgContent = msgEntity[2];

        return ret;
    }
}
