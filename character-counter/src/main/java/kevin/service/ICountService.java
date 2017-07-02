package kevin.service;

import java.io.InputStream;
import java.util.List;


public interface ICountService {
    /**
     * 根据输入流统计字符信息
     * @param streams
     * @return 字符统计信息和按照出现频率排序的字符及其频率
     */
    Object[] countFromStream(List<InputStream> streams);

    /**
     * 根据字符串 统计字符信息
     * @param streams
     * @return 字符统计信息和按照出现频率排序的字符及其频率
     */
    Object[] countFromString(String streams);
}
