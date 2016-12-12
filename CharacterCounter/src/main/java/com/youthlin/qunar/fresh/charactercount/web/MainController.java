package com.youthlin.qunar.fresh.charactercount.web;

import com.google.common.collect.Multiset;
import com.youthlin.qunar.fresh.charactercount.vo.CountResult;
import com.youthlin.qunar.fresh.charactercount.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.youthlin.qunar.fresh.charactercount.util.CharacterUtil.isChinese;

/**
 * Created by youthlin.chen on 2016-12-9 009.
 * MainController
 */
@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static CountResult process(Scanner scanner) {
        CountResult result = new CountResult();
        Multiset<Character> multiset = result.getWords();
        String line;
        int len;
        long symbols = 0;
        long enWords = 0;
        long digital = 0;
        long cnWords = 0;
        Pattern symbolPattern = Pattern.compile("\\pP");
        Pattern enPattern = Pattern.compile("\\p{Alpha}");
        Pattern dgPattern = Pattern.compile("\\p{Digit}");
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            Matcher symbol = symbolPattern.matcher(line);
            Matcher en = enPattern.matcher(line);
            Matcher dg = dgPattern.matcher(line);
            while (symbol.find()) {
                symbols++;
            }
            while (en.find()) {
                enWords++;
            }
            while (dg.find()) {
                digital++;
            }

            len = line.length();
            for (int i = 0; i < len; i++) {
                Character c = line.charAt(i);
                if (!Character.isWhitespace(c)) {//统计的是字 不应包括空白符号
                    //noinspection ResultOfMethodCallIgnored 该注释告诉IDEA忽略返回值
                    multiset.add(c);
                }
                if (isChinese(c) && !c.toString().matches("\\pP")) {
                    cnWords++;
                }
            }
            //noinspection ResultOfMethodCallIgnored
            //multiset.add('\n');
        }
        return result.setEnWords(enWords).setNumbers(digital).setCnWords(cnWords).setSymbols(symbols)
                .setTop3(result.getTopkWords(3));
    }

    public static void main(String[] args) {
        String text = "统计一段文字中（①文本输入方式——要求完成、②文件上传方式——选做）英文字母、数字、中文汉字、中英\n" +
                "文标点符号的个数，以及文字中出现频率最高的三个字符（出现频率相同的，按字典顺序排）。\n" +
                "\uF073 点击“统计”按钮，以表格的形式显示以上统计结果，如下图所示。\n" +
                "\uF073 文本输入方式下，点击“清除内容”按钮可清空文本框中的内容。(.[]【】)\n" +
                "要求：使用 Sprin3g MVC1==";
        Pattern symbols = Pattern.compile("\\pP");
        Matcher matcher = symbols.matcher("点号：句号.。 问号?？ 感叹号！! 逗号，, 顿号、\\ 分号;；和冒号：:\n" +
                "标号：“”‘’\'\"、括号{}[](){}【】（）〔〕、破折号──- 省略号^······、着重号．书名号《》〈〉<>、间隔号·" +
                "连接号—和专名号____ \n" +
                "符号：注释号* 斜线号/ 连珠号…… 标识号▲或●代替号～~箭头号→隐讳号× 虚缺号□ 破折号── ^<>");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
//        CountResult countResult = process(new Scanner(text));
//        System.out.println(countResult);
//        Multiset<Character> words = countResult.getWords();
//        ImmutableMultiset<Character> characters = Multisets.copyHighestCountFirst(words);
//        System.out.println(characters);
//        System.out.println(countResult.getTopkWords(3));

//        //Pattern p = Pattern.compile("\\p{Alpha}");
//        //Pattern p = Pattern.compile("\\p{Digit}");
//        Pattern p = Pattern.compile("\\pP");
//        Matcher m = p.matcher(text);
//        int count = 0;
//        while (m.find()) {
//            count++;
//            System.out.println(m.group());
//        }
//        System.out.println(count);
//        System.out.println();
//        count = 0;
//        int length = text.length();
//        for (int i = 0; i < length; i++) {
//            Character c = text.charAt(i);
//            if (isChinesePunctuation(c)) {
//                System.out.println(i + "-中文标点-" + c);
//                count++;
//            }
//        }
//        System.out.println(count);
//        System.out.println();
//        text = text.replaceAll("\\pP", "");//去掉中英文标点
//        count = 0;
//        length = text.length();
//        for (int i = 0; i < length; i++) {
//            Character c = text.charAt(i);
//            if (isChinese(c)) {
//                System.out.println("中文-" + c);
//                count++;
//            }
//        }
//        System.out.println(count);
//
//
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/upload"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result upload(@RequestParam(value = "file-input") MultipartFile file, HttpServletRequest request) {
        Result result = new Result();
        if (file == null || file.isEmpty()) {
            return result.setCode(1).setMsg("请选择文件上传");
        }
        String contentType = file.getContentType();
        //log.debug("contentType = {}", contentType);
        if (!contentType.contains("text")) {
            return result.setCode(2).setMsg("请上传文本文件");
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file.getInputStream());
        } catch (IOException e) {
            log.warn("get file input stream error.", e);
            return result.setCode(3).setMsg("处理失败(获取文件流出错)");
        }
        return result.setData(process(scanner));
    }

    @RequestMapping(value = {"/text"}, method = {RequestMethod.POST})
    @ResponseBody
    private Result text(@RequestParam("text-input") String text) {
        return new Result().setData(process(new Scanner(text)));
    }
}
