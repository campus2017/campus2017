package com.qunar.fresh2017.exam1;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Administrator on 2017/4/14.
 */
public class Exam1 {

    public Exam1(){

    }

    //order the message
    public boolean Order(){
        boolean ret = false;

        //validate the file
        if(FileManager.FileValidation()){

            ArrayList<Message> list = FileManager.FileParser();

            //sort
            if(list != null){
                try
                {
                    Collections.sort(list);
                }
                catch (Exception ex){
                    return ret;
                }
            }
            else{
                return ret;
            }

            //count
            HashMap<String,Integer> map = FileManager.Count(list);

            //sort
            List<Map.Entry<String,Integer>> mapList =
                    new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

            Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return (o2.getValue() - o1.getValue());
                }
            });

            //output
            if(map.isEmpty()){
                return ret;
            }
            ret = FileManager.Output(list, mapList);

            //clear the memory
            list.clear();
            map.clear();
            list = null;
            map = null;
        }

        return  ret;
    }
}
