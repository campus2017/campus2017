package QFC.CharacterCounter.Controller;

import QFC.CharacterCounter.POJO.Result;
import QFC.CharacterCounter.Service.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by root on 6/29/17.
 */

@Controller
@RequestMapping("/characterController")
public class counterController
{
    private Counter counter;

    @Autowired
    public void setCounter(Counter counter)
    {
        this.counter = counter;
    }

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/text.do")
    public String getString(@RequestParam(value = "text") String text, Model model)
    {
        System.out.println("controller:"+text);
        Result result;
        result = counter.work(text);
        model.addAttribute("Result",result);
        return "index";
    }

    @RequestMapping("/file.do")
    public String getFile(@RequestParam(value = "file",required = true)
                                      MultipartFile file, Model model)
    {
        Result result;
        result = counter.readFile(file);
        model.addAttribute("Result",result);
        return "index";
    }
}
