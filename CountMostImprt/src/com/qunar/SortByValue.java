package com.qunar;

import java.util.Comparator;

class SortByValue implements Comparator {
    public int compare(Object o1, Object o2) {
        MostImportClass s1 = (MostImportClass) o1;
        MostImportClass s2 = (MostImportClass) o2;
        return s1.getValue().compareTo(s2.getValue());
    }
}
