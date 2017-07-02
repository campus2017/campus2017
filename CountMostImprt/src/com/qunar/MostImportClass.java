package com.qunar;


public class MostImportClass implements Comparable<MostImportClass>{

    public String importName;
    public Integer value;
    MostImportClass(String importName, Integer value){
        this.importName=importName;
        this.value=value;
    }


    public int compareTo(MostImportClass o) {
        if(value.compareTo(o.value)>0) {
            return 1;
        }else{
            return 0;
        }
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
