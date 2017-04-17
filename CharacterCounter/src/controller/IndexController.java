package controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bll.CounterManager;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/2/9.
 */
@Controller
public class IndexController {

    CounterManager m_manager = new CounterManager();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {
        return "index";
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public void TextCounterProcess(@RequestParam(value="value", required=true) String text,HttpServletResponse response) {
        m_manager.TextManager(text,response,"text");
    }

    @RequestMapping(value = "/file")
    public void FileCounterProcess(@RequestParam("file") CommonsMultipartFile file,HttpServletResponse response) throws IOException {
        InputStream textStream = null;
        try{
            textStream  = file.getInputStream();
            byte[] bs = new byte[textStream.available()];
            textStream.read(bs);
            String text = new String(bs);
            m_manager.TextManager(text,response,"file");
        }
        catch (Exception ex){
            String msg="<script>parent.callBack('" +"2"+ "','" + "系统异常，请您稍后重试！" + "')</script>";
            response.getWriter().write(msg);
        }
        finally {
            if(textStream!=null){
                textStream.close();
                textStream = null;
            }
        }
    }
}
