package com.qunar.QA;

/**
 * @Author Nicole
 * @Time  2017/7/1
 * @Description 无效行的正则表达式
 */

public class RegularExp {

    static final String docNoteBegin = "^\\s*/\\*\\*.*$";    //文档注释开头的正则匹配
    static final String docNoteEnd = "^\\s*\\*.*$";          //文档注释中间或结尾的正则匹配
    static final String singleLineNote = "^\\s*//.*$";       //单行注释的正则匹配
    static final String blankLine = "\\s*";                  //空白行的正则匹配

}
