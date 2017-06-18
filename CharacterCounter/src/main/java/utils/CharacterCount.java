package utils;

import bean.ResponseObj;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * Created by libo on 2017/6/3.
 *
 * 用来统计一个文件或字符中的字符类型
 */

public class CharacterCount{

    /**
     * 判断一个字符是不是英文字母
     * @param ch
     */
    public static boolean isLetter(char ch){
        return String.valueOf(ch).matches("[a-zA-Z]");
    }

    /**
     * 判断一个字符是不是数字
     * @param ch
     * @return
     */
    public static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

    /**
     * 判断一个字符是不是中文汉字
     * 使用硬编码的方式，一些生僻字可能不在这个范围之内
     * @param ch
     * @return
     */
    public static boolean isChineseByRange(char ch){
        return String.valueOf(ch).matches("[\\u4e00-\\u9fa5]");
    }


    /**
     * 使用UnicodeScript的方式判断中文字符
     * @param ch
     * @return
     */
    public static boolean isChineseByScript(char ch){
        Character.UnicodeScript sc = Character.UnicodeScript.of(ch);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }


    /**
     * 判断中英文标点符号      \pS表示一系列特殊符号，如数学符号、货币符号
     * @param ch
     * @return
     */
    public static boolean isPunctuation(char ch){
        return String.valueOf(ch).matches("[\\pP]");
    }



    /**
     * 统计一个字符串中的英文字母、数字(考虑多位数)、中文汉字、中英文标点符号
     * 并统计英文字符和中文汉字出现的频率
     * @param con   如果为空，函数返回res
     * @param res   如果为null，方法中创建一个返回
     */
    public static ResponseObj characterCount(String con, ResponseObj res){
        if (con == null || "".equals(con)){
            return res;
        }
        if (res == null){
            res = new ResponseObj();
        }

        for(int i=0; i<con.length(); ++i){
            char ch = con.charAt(i);

            if (isChineseByScript(ch)){
                res.clacChineseNum();
                res.calcFrequence(ch);
            }
            else if (isLetter(ch)){
                res.clacLetterNum();
                res.calcFrequence(ch);
            }
            else if (isDigit(ch)){
                res.clacDigitNum();
                //处理多位的数字情况
                while (i+1 < con.length() && isDigit(con.charAt(i+1))){
                    ++i;
                }
            }
            else if (isPunctuation(ch)){
                res.clacPunctuationNum();
            }
        }

        return res;
    }

    /**
     * 处理文本上传的统计
     * @param con
     * @return
     */
    public static ResponseObj textCount(String con){
        return characterCount(con, null).getMaxFrequence(3);
    }

    /**
     * 处理txt文件的统计
     */
    public static ResponseObj txtFileCount(byte[] bytes) throws UnsupportedEncodingException {
        return characterCount(new String(bytes, "utf-8"), null).getMaxFrequence(3);
    }


    /**
     * 处理doc文件的统计
     * @param
     * @return
     */
    public static ResponseObj docFileCount(InputStream in) throws IOException {
        WordExtractor wordExtractor = new WordExtractor(in);
        String text = wordExtractor.getText();

        return characterCount(text, null).getMaxFrequence(3);
    }


    /**
     * 处理docx文件的统计
     * @param in
     * @return
     */
    public static ResponseObj docxFileCount(InputStream in) throws IOException {
        XWPFDocument doc = new XWPFDocument(in);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();

        return characterCount(text, null).getMaxFrequence(3);
    }



}
