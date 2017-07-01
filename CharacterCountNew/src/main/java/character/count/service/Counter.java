package character.count.service;

import character.count.beans.ResultOfCount;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hughgilbert on 30/06/2017.
 */
public interface Counter {
    //获取文本内容结果
    ResultOfCount getResultOfText(String text);
    //获取文件结果
    ResultOfCount getResultOfFile(MultipartFile file);
}
