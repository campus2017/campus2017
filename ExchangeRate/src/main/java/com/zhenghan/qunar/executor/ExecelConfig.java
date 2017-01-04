package com.zhenghan.qunar.executor;

import com.google.common.io.Files;

import java.io.File;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ExecelConfig {
    /**
     * 生成的文件名将是你自己取的path+"_"+"timestamp".xlsx
     * 注意这里是全路径如果没有设置默认的是d:\\execel_[timestamp].xlsx
     */
    private String path = "execel.xlsx";
    /**
     * Execel表格的名称
     */
    private String execelTableName = "";

    public String getPath() {
        int index = 0;
        String filename = Files.getNameWithoutExtension(path) + "_" + new Date().getTime() + ".xlsx";
        return (index = path.lastIndexOf(File.separator)) == -1 ? filename : path.substring(0, index + 1) + filename;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExecelTableName() {
        return execelTableName;
    }

    public void setExecelTableName(String execelTableName) {
        this.execelTableName = execelTableName;
    }
}
