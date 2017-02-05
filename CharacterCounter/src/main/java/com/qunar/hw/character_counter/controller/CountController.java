package com.qunar.hw.character_counter.controller;

import com.qunar.hw.character_counter.bean.CountRes;
import com.qunar.hw.character_counter.bean.StatisticsInfo;
import com.qunar.hw.character_counter.bean.Top3Info;
import com.qunar.hw.character_counter.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统计文本中各类型字符数量并得出出现次数最多的三个字符
 * Created by runsheng.zhou on 2017/2/3.
 */
@Controller
@RequestMapping(value = "/count")
public class CountController {
    private static final Logger logger = LoggerFactory.getLogger(CountController.class);

    @Autowired
    private CountService countService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView statistics(@RequestParam("text") String text) {
        CountRes res = countService.process(text);
        StatisticsInfo statisticsInfo = res.getStatisticsInfo();
        Top3Info top3Info = res.getTop3Info();
        ModelAndView mav = new ModelAndView();
        mav.addObject("text", text);
        mav.addObject("statisticsInfo", statisticsInfo);
        mav.addObject("most", top3Info.getMost());
        mav.addObject("secondMore", top3Info.getSecondMore());
        mav.addObject("thirdMore", top3Info.getThirdMore());

        return mav;
    }
}
