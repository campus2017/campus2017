package wz.service;

import java.util.List;
import java.util.Map;

/**
 * CounterService
 *
 * @author wz
 * @date 16/12/23 16:02
 */
public interface CounterService {

    Map<String,Object> parseText(String text);
    Map<String, Object> topKCharacter(String text,int k);


}


