package com.qunar.fresh2017.exam1;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/14.
 */
public class Message implements Comparable<Message> {

    public String Name;
    public String MsgDate;
    public String MsgContent;

    public Message(){

    }

    public int compareTo(Message msg) {
        int ret = this.MsgDate.compareTo(msg.MsgDate);

        if(ret == 0){
            ret = this.Name.compareTo(msg.Name);
        }

        return ret;
    }

}
