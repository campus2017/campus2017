package com.qunar.amao.service;

import org.apache.poi.hssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 打印Excel服务
 * Created by FGT on 2017/6/31.
 */
public class PrintExcelService<T> {

    /**
     *
     * @param title 文件标题
     * @param headers 列名称
     * @param dataset 查询数据集合
     * @param out 输入文件
     * @param pattern 日期匹配格式
     */
    public void PrintExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern){
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽为15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        //生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for(short i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while(it.hasNext()){
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for(short i=0;i<fields.length;i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try{
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t,new Object[]{});
                    //判断值类型后进行强制类型转换
                    String textValue = null;
                    if(value instanceof Date){
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    }else if (value instanceof Long) {
                        long longValue = (Long) value;
                        cell.setCellValue(longValue);
                    }else if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "1";
                       if (!bValue) {
                            textValue = "0";
                        }
                    } else if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        cell.setCellValue(intValue);
                    }
                    else{
                        if(value == null){
                            textValue="";
                        }else{
                            textValue = value.toString();
                        }
                    }
                    if(textValue != null){
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if(matcher.matches()){
                            cell.setCellValue(Double.parseDouble(textValue));
                        }else{
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            richString.applyFont(font);
                            cell.setCellValue(textValue);
                        }
                    }
                }catch(NoSuchMethodException e){
                    e.printStackTrace();
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }catch(InvocationTargetException e){
                    e.printStackTrace();
                }
            }
        }
        try{
            workbook.write(out);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
