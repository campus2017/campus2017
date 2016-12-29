package com.qunar.wireless.wcf;

import java.util.Map;

/**
 * Created by wcf on 2016-12-29.
 */
public interface IWriteToExcel {
    void writeACurrency(String fileName, Map<String,Map<String,String>> rates);
}