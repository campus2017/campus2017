package com.qunar;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by chao on 2017/7/3.
 */
@WebServlet(name = "MainServlet", urlPatterns = "/getData.json")
public class MainServlet extends HttpServlet {
    private static Connection conn;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String isSuccess = request.getParameter("isSuccess");

        String sqlWhere = "";
        if (startTime != null && !"".equals(startTime)) {
            sqlWhere += "time > '" + startTime + "' and ";
        }
        if (endTime != null && !"".equals(endTime)) {
            sqlWhere += "time < '" + endTime + "' and ";
        }
        if ("success".equals(isSuccess)) {
            sqlWhere += "state = 1 and ";
        } else if ("fail".equals(isSuccess)) {
            sqlWhere += "state = 0 and ";
        }

        if (sqlWhere.length() > 4 && sqlWhere.substring(sqlWhere.length() - 4).equals("and ")) {
            sqlWhere = sqlWhere.substring(0, sqlWhere.length() - 4);
        }

        String sql = "select * from info";
        if (sqlWhere.length() > 0) {
            sql = sql + " where " + sqlWhere;
        }

        try {
            if (conn == null || conn.isClosed()) {
                connectMysql();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", resultSet.getDate("time").toString());
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("number", resultSet.getInt("number"));
                jsonObject.put("cost", resultSet.getInt("cost"));
                jsonObject.put("state", resultSet.getInt("state") == 1 ? "成功" : "失败");
                jsonObject.put("info", resultSet.getString("info"));
                jsonArray.put(jsonObject);
            }
            out.print(jsonArray.toString());
        } catch (SQLException e) {
            out.print("数据库连接错误");
            e.printStackTrace();
        }

        out.flush();
        out.close();
    }

    private void connectMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/three", "root", "1993");
            Statement statement = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
