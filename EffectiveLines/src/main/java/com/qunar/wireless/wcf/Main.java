package com.qunar.wireless.wcf;

import java.io.IOException;

/**
 * Created by wcf on 2016-12-14.
 */
public class Main {

    public static void main(String[] args) {

        if(args.length>1){
            System.out.println("Too many arguments!");
        }else if(args.length<1){
            System.out.println("Need file name!");
        }else{
            try {
                EffectiveLines effectiveLines = new EffectiveLines(args[0]);
                System.out.println("The effective lines in this java source file is: " + effectiveLines.getLinesNum());
            }catch (IOException e){
                System.out.println("Error when open the file: "+args[0]);
            }

        }

    }
}
// 21