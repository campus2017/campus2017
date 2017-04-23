package com.qunar.fresh2017.exam1;

import java.io.*;

/**
 * Created by muhongfen on 17/4/13.
 */
public class FileExam {
    public static void main(String[] args) throws IOException {
        String filename ="file"+File.separator+"test1.txt";
        String outputfile ="file"+File.separator+"output.txt";
        fileInpputStream(filename);
        fileOutputStream();
        bufferedInputStream(filename);
        bufferedOutputStream(filename,outputfile);
        readFileByChars(filename);
        writeByFileReader(outputfile);
        readByBufferedReader(filename);
        writeByBufferedReader(outputfile);

    }

    /**
     * 字节流：FileInputStream  FileOutputStream
     */
    public  static void fileInpputStream(String fileName) {
        // 一般先创建file对象
        FileInputStream fileInput = null;

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] buffer = new byte[1024];
            fileInput = new FileInputStream(file);
            int byteread = 0;
            // byteread表示一次读取到buffers中的数量。
            while ((byteread = fileInput.read(buffer)) != -1) {
                System.out.write(buffer, 0, byteread);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {

            try {
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public static void fileOutputStream() {

        FileOutputStream fop = null;
        File file;
        String content = "fileOutputStream";
        try {
            file = new File("file"+File.separator+"test1.txt");
            fop = new FileOutputStream(file,true);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 缓冲字节流：BufferedInputStream
     * @throws IOException
     */
    public static void bufferedInputStream(String filename)throws IOException {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] contents = new byte[1024];
        int byteRead = 0;
        String strFileContents;

        try {
            while((byteRead = bis.read(contents)) != -1){
                strFileContents = new String(contents,0,byteRead);
                System.out.println(strFileContents);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bis.close();
    }

    /**
     * 缓冲字节流：BufferedOutputStream
     * @throws IOException
     */
    public  static void bufferedOutputStream(String filename,String outputfile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputfile));
        int i;
        do {
            i = bis.read();
            if (i != -1) {
                bos.write(i);
            }
        } while (i != -1);

        bis.close();
        bos.close();
    }
    /**
     * 字符流:FileReader
     */
    public static void readFileByChars(String fileName) {
        FileReader reader = null;
        System.out.print("FileReader:");
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new FileReader(file);
            char[] buffer = new char[1024];
            int charread = 0;
            while ((charread = reader.read(buffer)) != -1) {
                System.out.print(buffer);
            }
        } catch (IOException e) {
            // TODO: handle exception

        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符流：FileWriter
     */
    public static void writeByFileReader(String output) {
        try {
            String data = " This content will append to the end of the file";
            File file = new File(output);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            fileWritter.write(data);
            fileWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** 缓冲字符流 BufferedReader BufferedWriter
     *
      * @param fileName
     */
    public static void readByBufferedReader(String fileName) {
        System.out.println("BufferedReader:");
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                System.out.println(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void writeByBufferedReader(String output) {
        try {

            String content = "This is the content to write into file";
            File file = new File(output);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
