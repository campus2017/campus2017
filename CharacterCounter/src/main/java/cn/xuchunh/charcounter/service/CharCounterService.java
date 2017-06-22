package cn.xuchunh.charcounter.service;

import cn.xuchunh.charcounter.model.TextStats;
import org.springframework.stereotype.Service;

/**
 * Created by XCH on 2017/6/12.
 * 字符统计的 Service
 */
@Service
public interface CharCounterService {

    TextStats parse(String content);

}
