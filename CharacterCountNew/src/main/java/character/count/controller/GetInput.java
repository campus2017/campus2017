package character.count.controller;

import character.count.beans.ResultOfCount;
import character.count.service.CountContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hughgilbert on 30/06/2017.
 */
@Controller
public class GetInput {
    @RequestMapping("/getText")
    public String getText(HttpServletRequest request,Model model)
    {
        String text = request.getParameter("text");
        CountContent counter = new CountContent();
        ResultOfCount result = counter.getResultOfText(text);
        model.addAttribute("result",result);
        return "index";
    }
    @RequestMapping("/getFile")
    public String getFile(@RequestParam(value="file") MultipartFile file, Model model)
    {
        CountContent counter = new CountContent();
        ResultOfCount result = counter.getResultOfFile(file);
        model.addAttribute("result",result);
        return "index";
    }
}
