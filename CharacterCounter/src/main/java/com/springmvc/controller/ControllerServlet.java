package com.springmvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gcy0904 on 2017/1/24.
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String hidden1 = request.getParameter("div1");
        String hidden2 = request.getParameter("div2");

        if(hidden1 != null && "div2".equals(hidden2) ){
            System.out.println("div2");
        }

        if("div1".equals(hidden1) && hidden2 != null){
            System.out.println("div1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
