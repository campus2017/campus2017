package com.qunar.clp.service;

import com.qunar.clp.pojo.CountDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nipingchen on 16-12-14.
 */
public class CountsServiceTest {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Test
    public void count() throws Exception {
        CountsService countsService=new CountsService();
        String s="adfdf 767你好 ***";
        CountDto countDto = countsService.count(s);
        logger.info("英文{}", countDto.enCounts);
        logger.info("中文{}", countDto.chCounts);
        logger.info("數字{}", countDto.numCounts);
        logger.info("符號{}", countDto.symbolCounts);
        logger.info("字符出现频率前三:{},{},{}", countDto.maxNumThreeCharacter.get(0),countDto.maxNumThreeCharacter.get(1),countDto.maxNumThreeCharacter.get(2));
    }
}
