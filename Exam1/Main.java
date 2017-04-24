package com.lfz;
import java.io.*;
import java.text.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader=new BufferedReader(new FileReader("/Users/lfz/unorderedmsg.txt"));
        HashMap<String,Integer> nameAndCount=new HashMap<String,Integer>();
        ArrayList<Entity> entities=new ArrayList<Entity>();
        String msgLine=null;
        String[] msgArray=null;
        Integer count=null;
        long time;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while((msgLine=reader.readLine())!=null){
            msgArray=msgLine.split("    ");
            count=nameAndCount.get(msgArray[0]);
            if(count==null)
                nameAndCount.put(msgArray[0],1);
            else
                nameAndCount.put(msgArray[0],count+1);
            time=format.parse(msgArray[1]).getTime();
            entities.add(new Entity(msgArray[0],time,msgArray[2]));
        }
        BufferedWriter writer=new BufferedWriter(new FileWriter("/Users/lfz/count.txt"));
        Iterator<Map.Entry<String,Integer>> it=nameAndCount.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,Integer> entry=it.next();
            writer.write(entry.getKey()+"    "+entry.getValue());
            writer.newLine();
        }
        writer.close();
        Collections.sort(entities,new MyComparetor());
        writer=new BufferedWriter(new FileWriter("/Users/lfz/orderedmsg.txt"));
        for (Entity entity:entities){
            writer.write(entity.getName()+"    "+format.format(new Date(entity.getTime()))+"    "+entity.getMsg());
            writer.newLine();
        }
        writer.close();
    }
}
class Entity{
    private String name;
    private String msg;
    private long time;
    public Entity(String name,long time,String msg){
        this.name=name;
        this.time=time;
        this.msg=msg;
    }
    public String getName(){
        return name;
    }
    public String getMsg(){
        return msg;
    }
    public long getTime(){
        return time;
    }
}
class MyComparetor implements Comparator<Entity>{

    @Override
    public int compare(Entity o1, Entity o2) {
        if(o1.getTime()>o2.getTime())
            return 1;
        else if(o1.getTime()<o2.getTime())
            return -1;
        else
            return 0;
    }
}
