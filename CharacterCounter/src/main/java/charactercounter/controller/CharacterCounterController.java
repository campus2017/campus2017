package charactercounter.controller;

import javax.servlet.http.HttpServletRequest;

import charactercounter.Entity.CharacterCounterResult;
import charactercounter.service.CharacterCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2017/2/5.
 */
@Controller
public class CharacterCounterController {
    @Autowired
    CharacterCounterService characterCounterService;


    @RequestMapping("/")
    public String Counter() {
        return "index";
    }

    @RequestMapping(value = "/counttext", method = RequestMethod.POST)
    @ResponseBody
    public CharacterCounterResult Text(HttpServletRequest request) {
        String text = null;
        try {
            text = java.net.URLDecoder.decode(request.getParameter("text"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        CharacterCounterResult result = characterCounterService.count(text);
        return result;
    }

    @RequestMapping(value = "/countfile", method = RequestMethod.POST)
    @ResponseBody
    public CharacterCounterResult File(HttpServletRequest request) {
        String text = null;
        try {
            text = java.net.URLDecoder.decode(request.getParameter("text"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        CharacterCounterResult result = characterCounterService.count(text);
        return result;
    }
}