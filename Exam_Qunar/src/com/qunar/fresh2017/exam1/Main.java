package com.qunar.fresh2017.exam1;

public class Main {

    public static void main(String[] args) {

        String ret = "排序完成，结果输出在文件orderedmsg.txt";
        //order the file
        Exam1 exam = new Exam1();

        if(!exam.Order()){
            ret = "排序失败，请检查输入文件，重新运行程序";
        }

        FileManager.PrintLog(ret);
    }
}
