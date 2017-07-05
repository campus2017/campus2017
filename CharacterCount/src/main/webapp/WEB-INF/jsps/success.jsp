<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="UTF-8">
    <title></title>
    
    <link rel="stylesheet" href="css/ionic.min.css"/>
    <style>
        .fixed:after{
            display:block;
            content:"";
            clear:both;
        }
    </style>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
<h2>请选择一段文字</h2>

    <input type="radio" name="type"  style="margin-left:20px;" checked/>
    <label for="">文件上传</label>
    <input type="radio" name="type" style="margin-left:100px;"/>
    <label for="">文本输入</label>
    <br/>
    <div id="file1" class="change fixed" >
        <input type = "button" value = "上传文件" style="height:30px;margin:15px 0 20px 20px ;padding:0 15px;float:left;" onclick = "window.location.href = '/file.jsp'">  
        <button class="button button-small button-calm " style="height:30px;margin:15px 0 20px 20px ;padding:0 15px;float:left;" ng-click="countFile()">统计</button>
    </div>

    <div id="file2" class="change fixed" style="width:400px;display:none;">
     	 <form name="mainForm" action="<%= request.getContextPath()%>/Count/text" method="post">
        <textarea name="text" id="text" cols="30" rows="4" style="border:1px solid grey;overflow-y: scroll;float:left;margin-left:20px;resize:none;" placeholder="请再此输入文本内容"></textarea>
        <input  type="submit" style="height:30px;margin:0 0 0 20px ;padding:0 30px;float:left;" value = "统计"/>
        </form>
   	 <input  type="button" style="height:30px;margin:8px 0 0  20px ;padding:0 17.6px;float:left;background: orange;" value = "清空内容"  onclick="javasript: document.getElementById('text').value=''"/>
    </div>
    <br/>

    <p style="margin-left:20px;">各统计内容的个数如下</p>
    <table width="300" border="2" cellpadding="10" cellspacing="10" style="margin-left:20px;">
        <tbody>
        <tr>
            <th style="border:1px solid #000;text-align: center;width:150px;padding:6px;" ><b>统计项</b></th>
            <th style="border:1px solid #000;text-align: center;" ><b>个&nbsp;&nbsp;数</b></th>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; padding:6px;" >英文字母</td>
            <td style="border:1px solid #000;text-align: center;" >${statistics.english}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center;padding:6px; " >数字</td>
            <td style="border:1px solid #000;text-align: center;" >${statistics.number}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; padding:6px;" >中文汉字</td>
            <td style="border:1px solid #000;text-align: center;" >${statistics.chinese}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center;padding:6px; " >中英文标点符号</td>
            <td style="border:1px solid #000;text-align: center;padding:6px;" >${statistics.symbol}</td>
        </tr>
        </tbody>
    </table>
    <br/>

    <p style="margin-left:20px;">文字中出现频率最高的三个字是</p>
    <table width="300" border="2" cellpadding="10" cellspacing="10" style="margin-left:20px;">
        <tr>
            <th style="border:1px solid #000;text-align: center;width:150px;padding:6px;" ><b>统计项</b></th>
            <th style="border:1px solid #000;text-align: center;" ><b>个&nbsp;&nbsp;数</b></th>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; height:21px;padding:0 6px;" >${result.lisMostNum[0]['name']}</td>
            <td style="border:1px solid #000;text-align: center;" >${result.lisMostNum[0]['num']}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; height:21px;padding:0 6px;" >${result.lisMostNum[1]['name']}</td>
            <td style="border:1px solid #000;text-align: center;" >${result.lisMostNum[1]['num']}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; height:21px;padding:0 6px;" >${result.lisMostNum[2]['name']}</td>
            <td style="border:1px solid #000;text-align: center;" >${result.lisMostNum[2]['num']}</td>
        </tr>
    </table>

</body>

<script type="text/javascript">
    var radio = document.getElementsByName("type");
    radio[0].onclick =function(){
        document.getElementsByTagName("h2")[0].innerText = "请选择一段文字";
        document.getElementById("file1").style.display="block";
        document.getElementById("file2").style.display="none";
    };
    radio[1].onclick =function(){
        document.getElementsByTagName("h2")[0].innerText = "请输入一段文字";
        document.getElementById("file2").style.display="block";
        document.getElementById("file1").style.display="none";
    };
    angular.module("myApp",["ionic"]).controller("myCtrl",function($scope,$http,$location,$window){
        $scope.absUrl = $location.absUrl();
        $scope.protocol = $location.protocol();
        $scope.host = $location.host();
        $scope.port = $location.port();
        $scope.jumpToUrl = function(path){
            //alert($window.location.href);
            var loc = "http://localhost:8080/CharacterCounter/";
            loc = loc + path;
            //alert(loc)
            $window.location.href = loc;
        }
        $scope.saveText = {
            text:null
        }
        $scope.countText = function() {
            //alert($scope.saveText);
            if($scope.saveText==null){
                //alert("ok");
                $scope.statistics = null;
                $scope.lisMostNum = null;
            }else {
                $http({
                    method: "POST",
                    url: "http://localhost:8080/Count/text",
                    data: $scope.text
                }).success(function (data, status) {
                    $scope.statistics = data[0].statistics;
                    $scope.result.lisMostNum = data[0].lisMostNum;
                })
            }
        }
        
        $scope.countFile = function() {
            //alert($scope.saveText);
            if($scope.saveText==null){
                //alert("ok");
                $scope.statistics = null;
                $scope.result.lisMostNum = null;
            }else {
                $http({
                    method: "POST",
                    url: "http://localhost:8080/Count/file",
                    data: $scope.text
                }).success(function (data, status) {
                    $scope.statistics = data[0].statistics;
                    $scope.result.lisMostNum = data[0].lisMostNum;
                })
            }
        }
        
        $scope.clear = function(){
            var text1 = document.getElementById("text");
            text1.value="";
            $scope.tongji = null;
            $scope.lisMostNum = null;
            $scope.saveText = null;
        }
        $scope.UpCompute = function(){
            $http({
                method: "POST",
                url: "http://localhost:8080/CharacterCounter/TongJi"
            }).success(function(data,status){
                if(data[0] == null){
                    alert("并未上传文件！");
                    $scope.statistics = null;
                    $scope.result.lisMostNum = null;
                }else {
                    $scope.statistics = data[0].statistics;
                    $scope.result.lisMostNum = data[0].lisMostNum;
                }
            })
       
        }
    })
</script>
</html>

</html>