package com.qunar.main;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.qunar.util.GetFiles;
import com.qunar.util.ImportClassTopK;
import com.qunar.vo.ImportClassVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by yaoguoli on 2017/6/15.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("输入要统计的目录：");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();//获取文件路径

        Logger logger = Logger.getLogger(Main.class.getName());//打印日志
        logger.info("程序开始运行");

        Multiset<String> set = HashMultiset.create();
        File file = new File(filePath);
        set = new GetFiles().transfer(file,set);

        List<ImportClassVo> listVo = new ArrayList<ImportClassVo>();
        ImportClassVo importClassVo = null;
        for(String s:set.elementSet()){
            importClassVo = new ImportClassVo();
            importClassVo.setClassName(s);
            importClassVo.setNum(set.count(s));
            listVo.add(importClassVo);
        }

        ImportClassVo[] importClassVoArr = listVo.toArray(new ImportClassVo[listVo.size()]);
        List<ImportClassVo> listTopK = new ArrayList<ImportClassVo>();

        listTopK = new ImportClassTopK().topK(importClassVoArr, 10);
        System.out.println("出现次数最多的前10个类是：");
        for(ImportClassVo vo:listTopK){
            System.out.println(vo.getClassName() + "             出现次数是：" + vo.getNum());
        }
        logger.info("程序结束");

    }
}
