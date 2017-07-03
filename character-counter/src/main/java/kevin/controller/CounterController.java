package kevin.controller;

import com.google.common.collect.Lists;
import kevin.service.ICountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class CounterController {
    public static Logger logger = Logger.getLogger(CounterController.class);

    @Autowired
    private ICountService service;

    @RequestMapping("/")
    public String welcome(){
        return "/index";
    }
    @RequestMapping("/hello")
    public String hello(){
        return "/index";
    }

    @RequestMapping(value = "/textInput", method = RequestMethod.POST)
    public ModelAndView textInput(@RequestParam String text){
        Object[] objects = service.countFromString(text);
        ModelAndView model = new ModelAndView("index");
        model.addObject("countResult",objects[0]);
        model.addObject("topThree",objects[1]);
        return model;
    }
    @RequestMapping("/fileUpload")
    public ModelAndView fileUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(request)) {
            return new ModelAndView("index");
        }
        List<InputStream> streams = Lists.newArrayList();
        //将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //获取multiRequest 中所有的文件名
        Iterator<String> iter = multiRequest.getFileNames();
        while (iter.hasNext()) {
            //一次遍历所有文件
            MultipartFile mfile = multiRequest.getFile(iter.next().toString());
            if (mfile != null) {
                streams.add(mfile.getInputStream()); // 不需要再次转移到某一路径，以流的形式引用
            }
        }
        Object[] objects = service.countFromStream(streams);
        ModelAndView model = new ModelAndView("index");
        if (objects != null && objects.length > 0) {
            model.addObject("countResult",objects[0]);
            model.addObject("topThree",objects[1]);
        }
        return model;
    }
}
