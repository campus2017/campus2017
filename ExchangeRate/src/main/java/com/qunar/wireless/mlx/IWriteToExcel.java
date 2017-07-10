package com.qunar.wireless.mlx;

import java.util.Map;

/**
 * Created by mlx on 2016-12-29.
 */
public interface IWriteToExcel {
    void writeACurrency(String fileName, Map<String,Map<String,String>> rates);
}