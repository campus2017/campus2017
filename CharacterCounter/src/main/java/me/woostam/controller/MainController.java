package me.woostam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by woo on 6/27.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value="login",method=RequestMethod.POST)
    public ModelAndView login(String username,String password){
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        if(this.checkParams(new String[]{username,password})){
            //指定要返回的页面为succ.jsp
            ModelAndView mav = new ModelAndView("succ");
            //将参数返回给页面
            mav.addObject("username",username);
            mav.addObject("password", password);
            return mav;
        }
        return new ModelAndView("home");
    }

    @RequestMapping(value = "CharacterCounter",method = RequestMethod.POST)
    public ModelAndView CharacterCounter(String CharacerText){
        ModelAndView mav = new ModelAndView("succ");

        mav.addObject("CharacterText",CharacerText);
        return mav;
    }

    /***
     * 验证参数是否为空
     * @param params
     * @return
     */
    private boolean checkParams(String[] params){
        for(String param:params){
            if(param==""||param==null||param.isEmpty()){
                return false;
            }
        }
        return true;
    }
}