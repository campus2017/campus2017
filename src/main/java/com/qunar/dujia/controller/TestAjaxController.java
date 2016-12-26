package com.qunar.dujia.controller;

import com.qunar.dujia.model.AngularText;
import com.qunar.dujia.model.MostNum;
import com.qunar.dujia.model.ResModel;
import com.qunar.dujia.model.TongJi;
import com.qunar.dujia.utils.LIstFile;
import com.qunar.dujia.utils.modelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyiren on 16-12-22.
 */
@Controller
public class TestAjaxController {

    @RequestMapping(value="/TongJi",method= RequestMethod.POST)
    @ResponseBody
    public List<ResModel> transRes(){
        String str = LIstFile.showFileName();

        System.out.println("统计的文件"+str);

        if(str==null){
            return null;
        }else{
            String paths = "/home/tianyiren/TestReceive/";

            paths += str;

            System.out.println(paths);

            TongJi res = modelUtils.readFileByLines(paths);

            List<MostNum> lis = modelUtils.readFileChines(paths);

            System.out.println(res.getBiaodian() + " : " + res.getChinese() + " : " + res.getNumber() + " : " + res.getEnglish());

            for (MostNum mn : lis) {
                System.out.println(mn.getName() + " : " + mn.getNum());
            }

            ResModel resModel = new ResModel(res,lis);

            List<ResModel> l = new ArrayList<ResModel>();

            l.add(resModel);

            LIstFile.removeFileName();

            return l;

        }
    }


    @RequestMapping(value="/TestAjax",method= RequestMethod.POST)
    @ResponseBody
    public List<ResModel> ajaxTo(@RequestBody AngularText angularText) {

        String res = angularText.getText();
        res = res.replaceAll(" ","");
        res = res.replaceAll("\n","");
        System.out.println("内容："+res);
        TongJi tongJi = modelUtils.getObject(res);

        List<MostNum> lis = modelUtils.getFinalObj(res);

        System.out.println(tongJi.getBiaodian()+": "+tongJi.getNumber()+": "+tongJi.getEnglish()+": "+tongJi.getChinese());

        for(int i=0;i<lis.size();i++){
            System.out.println(lis.get(i).getName()+"::"+lis.get(i).getNum());
        }

        ResModel resModel = new ResModel(tongJi,lis);

        List<ResModel> l = new ArrayList<ResModel>();

        l.add(resModel);





        System.out.println(angularText.getText());
        return l;
    }

}
