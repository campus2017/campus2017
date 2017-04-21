package com.qunar.fresh2017.exam1;

//聊天记录类
class ChatLog {

    long date;  //时间
    String name;  //名字
    String log;  //整条聊天记录

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatLog(){
    }

    public ChatLog(Long date, String name, String log){
        this.date = date;
        this.name = name;
        this.log = log;
    }

}