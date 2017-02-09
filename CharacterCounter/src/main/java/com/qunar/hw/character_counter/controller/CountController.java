package com.qunar.hw.character_counter.controller;

import com.qunar.hw.character_counter.bean.CountRes;
import com.qunar.hw.character_counter.bean.StatisticsInfo;
import com.qunar.hw.character_counter.bean.Top3Info;
import com.qunar.hw.character_counter.service.CountService;
import com.qunar.hw.character_counter.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统计文本中各类型字符数量并得出出现次数最多的三个字符
 * Created by runsheng.zhou on 2017/2/3.
 */
@Controller
public class CountController {
    private static final Logger logger = LoggerFactory.getLogger(CountController.class);

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public String statistics(@RequestParam("text") String text, Model model) {
        CountRes res = countService.process(text);
        logger.info("result : {}", JacksonUtil.serialize(res));

        StatisticsInfo statisticsInfo = res.getStatisticsInfo();
        Top3Info top3Info = res.getTop3Info();
        model.addAttribute("text", text);
        model.addAttribute("statisticsInfo", statisticsInfo);
        model.addAttribute("most", top3Info.getMost());
        model.addAttribute("secondMore", top3Info.getSecondMore());
        model.addAttribute("thirdMore", top3Info.getThirdMore());

        return "result";
    }
}
