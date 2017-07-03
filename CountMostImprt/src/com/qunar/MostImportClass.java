package com.qunar;

public class MostImportClass {

    public String importName;
    public Integer value;
    MostImportClass(String importName, Integer value){
        this.importName=importName;
        this.value=value;
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
