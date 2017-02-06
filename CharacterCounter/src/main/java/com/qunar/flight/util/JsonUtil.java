package com.qunar.flight.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by nostalie.zhang on 2017/2/3.
 */
public class JsonUtil {
    public static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static String toJson(Object o){
        if(o == null){
            return null;
        }
        String result;
        ObjectMapper mapper = new ObjectMapper();
        try {
             result = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("json序列化失败",e);
            return null;
        }
        return result;
    }
}
