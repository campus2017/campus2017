package cn.xuchunh.charcounter.dao;

import cn.xuchunh.charcounter.model.FileInfo;

/**
 * Created by XCH on 2017/6/13.
 */
public interface FileDao {

    void insertAndGetId(FileInfo fileInfo);

    String getContentById(int id);

}
