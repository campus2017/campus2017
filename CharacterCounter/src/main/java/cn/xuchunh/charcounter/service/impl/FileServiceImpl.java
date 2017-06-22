package cn.xuchunh.charcounter.service.impl;

import cn.xuchunh.charcounter.dao.FileDao;
import cn.xuchunh.charcounter.model.FileInfo;
import cn.xuchunh.charcounter.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created on 2017/6/13.
 *
 * @author XCH
 */
@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService{

    @Resource
    private FileDao fileDao;

    @Override
    public int insert(String name, String content) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(name);
        fileInfo.setContent(content);
        fileDao.insertAndGetId(fileInfo);
        return fileInfo.getId();
    }

    @Override
    public String getContentById(int id) {
        return fileDao.getContentById(id);
    }
}
