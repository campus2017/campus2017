package com.qunar.fresh2017.exam1;

import java.util.Comparator;

//聊天记录比较器
class ChatLogComparator implements Comparator<ChatLog> {
    public int compare(ChatLog o1, ChatLog o2) {
        if (o1.getDate() < o2.getDate()){
            return -1;
        } else if (o1.getDate() > o2.getDate()){
            return 1;
        }else{
            if (o1.getName().compareTo(o2.getName()) > 0){
                return 1;
            }else if(o1.getName().compareTo(o2.getName()) < 0){
                return -1;
            }
            else{
                return 0;
            }
        }

    }
}
