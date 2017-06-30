package com.qunar.homework.three;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by deep on 2017/6/12.
 */
public class CountMostImport {


    public static void main(String[] args) {

    }


    public static void WalkDir(String path) throws FileNotFoundException {



        File basePath = new File(path);

        if (!basePath.exists()) {
            throw new FileNotFoundException();
        }
        if (basePath.isDirectory()) {
            String[] fileList = basePath.list();
            for (String file : fileList) {
                File file1 = new File(file);
            }
        }



    }


    public static void recursion(){

    }



}

