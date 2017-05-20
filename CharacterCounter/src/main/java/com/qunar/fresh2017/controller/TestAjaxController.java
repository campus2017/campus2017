package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.AngularText;
import com.qunar.fresh2017.model.MostNum;
import com.qunar.fresh2017.model.ResModel;
import com.qunar.fresh2017.model.Statics;
import com.qunar.fresh2017.utils.ListFile;
import com.qunar.fresh2017.utils.modelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹 on 2017.5.17.
 */
@Controller
public class TestAjaxController {

    @RequestMapping(value="/TongJi",method= RequestMethod.GET)
    @ResponseBody
    public List<ResModel> transRes(){
        String str = ListFile.showFileName();

        System.out.println("统计的文件"+str);

        if(str==null){
            return null;
        }else{
            String paths = "F://test";

            paths += str;

            System.out.println(paths);

            Statics res = modelUtils.readFileByLines(paths);

            List<MostNum> lis = modelUtils.readFileChines(paths);

            System.out.println(res.getBiaodian() + " : " + res.getChinese() + " : " + res.getNumber() + " : " + res.getEnglish());

            for (MostNum mn : lis) {
                System.out.println(mn.getName() + " : " + mn.getNum());
            }

            ResModel resModel = new ResModel(res,lis);

            List<ResModel> l = new ArrayList<ResModel>();

            l.add(resModel);

            ListFile.removeFileName();

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
        Statics statics = modelUtils.getObject(res);

        List<MostNum> lis = modelUtils.getFinalObj(res);

        System.out.println(statics.getBiaodian()+": "+ statics.getNumber()+": "+ statics.getEnglish()+": "+ statics.getChinese());

        for(int i=0;i<lis.size();i++){
            System.out.println(lis.get(i).getName()+"::"+lis.get(i).getNum());
        }

        ResModel resModel = new ResModel(statics,lis);

        List<ResModel> l = new ArrayList<ResModel>();

        l.add(resModel);

        System.out.println(angularText.getText());
        return l;
    }

}
