package com.qunar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiazihao on 7/2/17.
 */
@Controller
public class controller {
    @RequestMapping(value = "/start")
    public String start(){
        return "start";
    }

    @RequestMapping(value = "/uploadFile")
    public String upLoadFile(){
        return "UpLoad";
    }

    @RequestMapping(value = "/TestAjax",method = RequestMethod.POST)
    public String ajax(@RequestBody Angular angular){
        System.out.print(angular.getText());
        return "start";
    }
}
