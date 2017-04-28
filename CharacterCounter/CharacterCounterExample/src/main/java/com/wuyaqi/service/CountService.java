package com.wuyaqi.service;

import com.wuyaqi.dao.CountDAO;
import com.wuyaqi.model.CountResult;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyaqi on 17-4-29.
 * 给controller提供接口
 */
public class CountService {
    private CountDAO countDAO = new CountDAO();

    public CountResult getCountResult(String str)
    {
        CountResult countResult = new CountResult();

        if(str==null)
        {
            return countResult;
        }
        Map<String,Integer> charNumMap =  countDAO.getCounter(str);
        List<Map.Entry<Character,Integer>> top3Map = countDAO.getTop3Charater();

        countResult.setChnCount(charNumMap.get("chnCount"));
        countResult.setEngCount(charNumMap.get("engCount"));
        countResult.setNumCount(charNumMap.get("numCount"));
        countResult.setPunCount(charNumMap.get("punCount"));
        countResult.setResultTop3(top3Map);

        return countResult;
    }
}
