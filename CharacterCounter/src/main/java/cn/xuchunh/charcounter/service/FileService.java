package cn.xuchunh.charcounter.service;

import org.springframework.stereotype.Service;

/**
 * Created by XCH on 2017/6/13.
 *
 * 对上传的文件进行处理的 Service
 *
 */
@Service
public interface FileService {

    int insert(String name, String content);

    String getContentById(int id);

}
