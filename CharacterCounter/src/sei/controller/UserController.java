package sei.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sei.bean.ShowCountBean;
import sei.pojo.User;
import sei.service.UserService;
import sei.tool.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/work")  
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }

    @RequestMapping("/funPage")
    public String showHomePage(Model model) {
        return "homePage";
    }

    @RequestMapping(value = "/counterStr",method = RequestMethod.POST)
    @ResponseBody
    public ShowCountBean analyseStr(@RequestParam("wordStr") String testStr) {
        ShowCountBean resBean=new ShowCountBean();
        WordsTopCalculator wtc = new WordsTopCalculator();
        Map<CharacterType,Integer> typeMap  = CounterTool.createMap();
        for(int i=0;i<testStr.length();i++)	{
            char c= testStr.charAt(i);
            String tmpStr = String.valueOf(c);
            CharacterType chtype=CounterTool.judgeType(tmpStr);

            if(!typeMap.containsKey(chtype)){
                typeMap.put(chtype, 1);
            }else{
                typeMap.put(chtype, typeMap.get(chtype)+1);
            }
            if(chtype.equals(CharacterType.CHZ)){
                wtc.addElement(tmpStr);
            }
        }
        resBean.setStmap(typeMap);
        List<WordsTopCalculator.WordCountBean> rlst=wtc.getCurrentTop(3);
        resBean.setTplist(rlst);
       /* Map<String,WordsTopCalculator.WordCountBean> tmap =new HashMap<>();
        for(int i=0;i<rlst.size();i++){
            tmap.put("top")
        }*/
      //  resBean.setTplist(wtc.getCurrentTop(3));
        //for
        /*for(Map.Entry<CharacterType, Integer> en: typeMap.entrySet()){
            logger.info(en.getKey().getName() + " " + en.getValue());
           // System.out.println();
        }*/
        return resBean;
    }
}