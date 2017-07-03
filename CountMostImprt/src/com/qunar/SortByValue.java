package com.qunar;

import java.util.Comparator;

class SortByValue implements Comparator<MostImportClass> {
    public int compare(MostImportClass o1, MostImportClass o2) {
        return o2.getValue() - o1.getValue() ;
    }
}
