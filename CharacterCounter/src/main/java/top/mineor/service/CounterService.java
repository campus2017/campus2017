package top.mineor.service;

/**
 * Created by Mineor on 2017/6/26.
 */

import java.util.Map;

public interface CounterService {

    Map<String,Object> parseText(String text);
    Map<String, Object> topKCharacter(String text,int k);


}
