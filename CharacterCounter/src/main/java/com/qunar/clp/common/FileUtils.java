package com.qunar.clp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by nipingchen on 16-12-22.
 */
public class FileUtils {
    private static Logger logger= LoggerFactory.getLogger(FileUtils.class);
    public static String readToString(InputStream inputStream) {
        StringBuffer sb=new StringBuffer();
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        String line=null;
        try{
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
        }catch(IOException e){
            logger.error("{}",e);
        }finally {
            try{
                reader.close();
            }catch (IOException e2){
                logger.error("{}",e2);
            }
        }
        return sb.toString();
    }
}
