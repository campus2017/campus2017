<!DOCTYPE html>


<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" import="java.util.* ,java.awt.*" pageEncoding="UTF-8"%>

<html>
<head>

    <script language=javascript>
        function reset(){
            document.getElementById("inputWord").value="";
        }
    </script>



    <style>
        .textInput {
            width: 300px;
            height: 100px;
            id:"InpuText";
            align:"center";
            padding: 10px;
            border-color: blue;
        }


        .table-list {border:solid #add9c0; border-width:1px 0px 0px 1px;border-color:#000;font-size:15px;width:320px;}
        td{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:20px;border-color:#000;text-align:center;}
        th{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:20px;border-color:#000;}


        .table-sort{border:solid #add9c0; border-width:1px 0px 0px 1px;border-color:#000;font-size:15px;width:320px;}
        td{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:20px;border-color:#000;text-align:center;}
        th{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:20px;border-color:#000;}


    </style>
</head>

<body>


<div style="border:solid 1px; margin:0 auto; width:400px;height:560px;align:center;border-color: #000;padding:10px 10px 10px 10px;font-size:15px;">


    <div>
        <p style = "color: #000; font-size: 15px"; align="center">Please Input Some Words</p>
    </div>


    <div align="center"; style="margin-bottom: 10px">
        <form id="buttonform" action = "/hello/getStasticDataValue" method="post"; >


            <div align="center"; >
                <textarea  class="textInput"; name="inputWord"; >${textArea}</textarea>
            </div>

            <input type="submit" ; style="background:#663300; height: 30px; width: 160px; color: #FFFFFF";value="Count";>
            </input>

            <input type="button" style="background: #FF3399; height: 30px; width: 160px; color: #000000" value="Clear";
                   onclick="reset()">
            </input>

        </form>
    </div>



    <div>
        <p style = "color: #000; font-size: 15px"; align="center">Statisric Sheet</p>
    </div>

    <table class="table-list"; align="center">
        <tr>
            <td>Item</td>
            <th>Count</th>
        </tr>
        <tr>
            <td>English</td>
            <th>${EnglishCount}</th>
        </tr>
        <tr>
            <td>Number</td>
            <th>${NumberCount}</th>
        </tr>
        <tr>
            <td>Chinese</td>
            <th>${ChineseCount}</th>
        </tr>
        <tr>
            <td>Dot</td>
            <th>${DotCount}</th>
        </tr>
    </table>

    <div>
        <p style = "color: #000; font-size: 15px"; align="center">Highest Frequency Words </p>
    </div>

    <table class="table-sort"; align="center">
        <tr>
            <td>Name</td>
            <th>Count</th>
        </tr>
        <tr>
            <td>${ItemFirst}</td>
            <th>${ItemFirstCount}</th>
        </tr>
        <tr>
            <td>${ItemSecond}</td>
            <th>${ItemSecondCount}</th>
        </tr>
        <tr>
            <td>${ItemThird}</td>
            <th>${ItemThirdCount}</th>
        </tr>
    </table>



</div>

</body>
</html>
