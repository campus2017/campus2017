package com.qunar.fresh2017.utils;

import java.util.*;

/**
 * Created by 曹 on 2017.5.17.
 */
public class ListFile {
    private static Stack<String> stack = new Stack<String>();
    public static String AddFileName(String newName){
        stack.push(newName);
        return "success";
    }
    public static String showFileName(){
        if(stack.isEmpty()){
            return null;
        }else {

            return stack.peek();

        }
    }
    public static void removeFileName(){
        if(!stack.isEmpty()){
            stack.pop();
        }
    }

    public static void main(String args[]){
        System.out.println(showFileName());
    }
}
