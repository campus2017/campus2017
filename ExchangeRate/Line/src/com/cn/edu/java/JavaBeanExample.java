package com.cn.edu.java;

/**
 * Created by ASUS on 2017/2/3.
 */
public class JavaBeanExample {
    private String name;
    private double age;
    private String birthday;
    public String GetName(){
        return name;
    }
    public double GetAge(){
        return age;
    }
    public String GetBirthday(){
        return birthday;
    }
    public void SetName(String name){
        this.name = name;
    }
    public void SetAge(double age){
        this.age = age;
    }
    public void SetBirthday(String birthday){
        this.birthday = birthday;
    }
}
