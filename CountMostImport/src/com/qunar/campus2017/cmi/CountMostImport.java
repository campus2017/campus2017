package com.qunar.campus2017.cmi;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Leon on 2017/4/24.
 */
public class CountMostImport {

    private final static String REGEX_IMPORT = "import\\s+([^;\\s]+)\\s*;";
    private final static Pattern patten = Pattern.compile(REGEX_IMPORT);
    /** GroupId of the imported class string in the regex. */
    private final static int GROUP_ID = 1;
    private final static int TOP_N = 10;

    private static ImportStatistics imp = null;
    private static File srcDir = null;





    public static void dfs(File file) {
        if (file.isDirectory()) {

            File[] subFilesArray = file.listFiles();
            for(File subFile: subFilesArray) {
                dfs(subFile);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                processFile(file);
            }
        }
    }

    private static void processFile(File file) {

        String line = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            while((line = br.readLine()) != null) {

                Matcher matcher = patten.matcher(line);
                while (matcher.find()) {
                    String importClass = matcher.group(GROUP_ID);
                    imp.addImport(importClass);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void outputMostImport(int topN){
        ArrayList<ImportCountEntry> list = imp.getMostImport(topN);
        for (ImportCountEntry entry: list) {
            System.out.println(entry.getStrImport() + ": " + entry.getCount());
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("input java source file or directory: ");
        String strSrcDir = sc.nextLine();
        srcDir = new File(strSrcDir);
        if (!srcDir.exists()) {
            System.out.println("Error: invalid file or directory!");
        }
        imp = new ImportStatistics();
        dfs(srcDir);
        outputMostImport(TOP_N);
    }

}

