/**
 * Created by steamedfish on 17-6-27.
 */
package com.JingYu.CharacterCounter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller

public class CharacterCounterController {

    @RequestMapping(value = "/context",method = RequestMethod.GET)
    public ModelAndView context(){
        pageContext pageContext = new pageContext();
        return new ModelAndView("context").addObject(pageContext);
    }

    @RequestMapping(value="/count",method=RequestMethod.POST)
    public void countText(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String str=request.getParameter("text");
        System.out.println("str:"+str);
        CharacterCounter.AnalyzeString(str);
        CharacterCounter.CountCharacter(str);
        StringBuffer buffer=new StringBuffer("");
        buffer.append("{");

        buffer.append("\"1\":");
        buffer.append("\""+CharacterCounter.map.get("letter")+"\",");

        buffer.append("\"2\":");
        buffer.append("\""+CharacterCounter.map.get("number")+"\",");
        buffer.append("\"3\":");
        buffer.append("\""+CharacterCounter.map.get("Chinese")+"\",");

        buffer.append("\"4\":");
        buffer.append("\""+CharacterCounter.map.get("symbol")+"\",");

        buffer.append("\"5\":");
        buffer.append("\""+CharacterCounter.map1.get("Seq0")+"\",");

        buffer.append("\"6\":");
        buffer.append("\""+CharacterCounter.map1.get("Seq1")+"\",");

        buffer.append("\"7\":");
        buffer.append("\""+CharacterCounter.map1.get("Seq2")+"\"");




        buffer.append("}");
        System.out.println(buffer.toString());
        response.getWriter().write(buffer.toString());


    }

    @RequestMapping(value = "/addText",method = RequestMethod.POST)
    public String addText(@ModelAttribute("SpringWeb")pageContext pageContext, ModelMap model){
        model.addAttribute("text",pageContext.getTextBox());
        return "context";
    }


}
