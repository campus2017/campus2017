package com.qunar.hotel.campus2017;

import com.qunar.hotel.campus2017.service.imp.CountMostImportImp;

import javax.swing.*;

/**
 * Created by fangming.yi on 2017/1/15.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("请选择java源文件目录路径：");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        String path = chooser.getSelectedFile().getPath();
        System.out.println("你选择的路径为：" + path);
        if (path == null || path.trim().equals("")) {
            System.out.println("选择路径有误！");
            System.exit(-1);
        }
        CountMostImportImp cmi = new CountMostImportImp();
        cmi.getFileFromPath(path);
        cmi.getClassNum();
        cmi.getMaxClass(10);
    }
}
