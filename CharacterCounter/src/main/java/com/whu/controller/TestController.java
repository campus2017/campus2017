package com.whu.controller;

import com.whu.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Fly on 2017/1/17.
 */

@Controller
public class TestController {

    @RequestMapping(value="/book.do", params = "method=add")
    public String add(Book book){
        System.out.println("bookname:" + book.getName());
        System.out.println("author:" + book.getAuthor());
        return "success";
    }

}
