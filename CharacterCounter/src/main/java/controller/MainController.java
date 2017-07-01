package controller;

import domain.Context;
import domain.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.ContextService;

import java.io.IOException;

/**
 * Created by luotuomianyang on 2017/1/6.
 */
@Controller
public class MainController {
    @Autowired
    private ContextService contextService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("context", new Context());
        return "index";
    }

    @RequestMapping(value = "/file_save", method = RequestMethod.POST)
    public String fileSave(@RequestParam(value = "file", required = true) MultipartFile file,
                           @ModelAttribute Context context,
                           Model model) throws IOException {
        Statistics stat = contextService.statFile(file);
        model.addAttribute("statistics", stat);
        return "index";
    }

    @RequestMapping(value = "/context_save", method = RequestMethod.POST)
    public String contextSave(@ModelAttribute Context context, Model model){
        //System.out.println(context.getText());
        Statistics stat = contextService.statContext(context);
        model.addAttribute("statistics", stat);
        return "index";
    }
}
