package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiazihao on 6/29/17.
 */
@Controller
public class HomePageController {
    @RequestMapping(value = "/start")
    public String HomePage(){
        return "/jsp/start.jsp";
    }
}
