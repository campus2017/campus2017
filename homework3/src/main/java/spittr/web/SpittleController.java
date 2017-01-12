package spittr.web;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaojun
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {
    @Autowired
    private SpittleRepository spittleRepository;
    @RequestMapping(method = RequestMethod.POST, produces ="application/json;charset=utf-8")
    @ResponseBody
    public String spittle(
            @RequestParam(value = "start") long start,
            @RequestParam(value = "end") long end,
            @RequestParam(value = "beginTime",defaultValue = "0") String beginTime,
            @RequestParam(value = "endTime",defaultValue = "0")  String endTime
            ) {
        System.out.println(beginTime + endTime);
        Gson gson = new Gson();
        Spitter spitter = new Spitter(50L,spittleRepository.findSpittles(start,end,beginTime,endTime));
        String obj2Json = gson.toJson(spitter );
        System.out.println(obj2Json);
        return obj2Json;
    }




}
