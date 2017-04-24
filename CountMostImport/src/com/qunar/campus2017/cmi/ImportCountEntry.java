package com.qunar.campus2017.cmi;

/**
 * Created by Leon on 2017/4/24.
 */
public class ImportCountEntry implements Comparable<ImportCountEntry>{



    private String strImport;
    private Integer count;

    public ImportCountEntry(String strImport, int count) {
        this.strImport = strImport;
        this.count = count;
    }

    public String getStrImport() {
        return strImport;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int compareTo(ImportCountEntry o) {
        return o.count.compareTo(this.count);
    }
}
