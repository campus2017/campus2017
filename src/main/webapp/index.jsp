<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="js/ionic.bundle.min.js"></script>
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

<form action="" style="height:900px;">
    <input type="radio" name="type"  style="margin-left:20px;" checked/>
    <label for="">文件上传</label>
    <input type="radio" name="type" style="margin-left:100px;"/>
    <label for="">文本输入</label>
    <br/>
    <div id="file1" class="change fixed">
        <button class="button button-small button-calm " style="height:30px;margin:15px 0 20px 20px ;padding:0 15px;float:left;">上传文件</button>
        <input type="text" style="float:left;margin:15px 0 20px 2px;width:300px;"/>
        <button class="button button-small button-calm " style="height:30px;margin:15px 0 20px 20px ;padding:0 15px;float:left;">统计</button>
    </div>

    <div id="file2" class="change fixed" style="width:400px;display:none;">
        <textarea name="" id="text" cols="30" rows="4" style="border:1px solid grey;overflow-y: scroll;float:left;margin-left:20px;resize:none;" placeholder="请再此输入文本内容"></textarea>
        <div class="button button-small button-calm " style="height:30px;margin:0 0 0 20px ;padding:0 30px;float:left;" ng-click="count()">统计</div>
        <div class="button  button-small " style="height:30px;margin:8px 0 0  20px ;padding:0 17.6px;float:left;background: orange;"  ng-click="clear()"><b>清空内容</b></div>
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
            <td style="border:1px solid #000;text-align: center;" >{{arr[0]}}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center;padding:6px; " >数字</td>
            <td style="border:1px solid #000;text-align: center;" >{{arr[1]}}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; padding:6px;" >中文汉字</td>
            <td style="border:1px solid #000;text-align: center;" >{{arr[2]}}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center;padding:6px; " >中英文标点符号</td>
            <td style="border:1px solid #000;text-align: center;padding:6px;" >{{arr[3]}}</td>
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
            <td style="border:1px solid #000;text-align: center; height:21px;padding:0 6px;" >{{str[arr]}}</td>
            <td style="border:1px solid #000;text-align: center;" >{{arr1[0]}}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center;height:21px;padding:0 6px;" ></td>
            <td style="border:1px solid #000;text-align: center;" >{{arr1[1]}}</td>
        </tr>
        <tr>
            <td style="border:1px solid #000;text-align: center; height:21px;padding:0 6px;" ></td>
            <td style="border:1px solid #000;text-align: center;" >{{arr1[2]}}</td>
        </tr>
    </table>

</form>
</body>
<script>
    var radio = document.getElementsByName("type");
    radio[0].onclick =function(){
        document.getElementsByTagName("h2")[0].innerText = "请选择一段文字";

        document.getElementById("file1").style.display="block";
        document.getElementById("file2").style.display="none";

    }
    radio[1].onclick =function(){

        document.getElementsByTagName("h2")[0].innerText = "请输入一段文字";
        document.getElementById("file2").style.display="block";
        document.getElementById("file1").style.display="none";
    };
    angular.module("myApp",["ionic"]).controller("myCtrl",function($scope){
        $scope.count = function() {
            var arr = [];
            var text = document.getElementById("text").value;
            var yingwen = 0, shuzi = 0, hanzi = 0, biaodian = 0;
            var reg = /\d/;
            var reg1 = /[A-Z]|[a-z]/;
            var reg2 = /^[\u4e00-\u9fa5]$/;
            var number;
            for (i = 0; i < text.length; i++) {
                if (reg1.test(text[i])) {
                    yingwen++;
                }
                else if (reg.test(text[i])) {
                    shuzi++;
                }
                else if (reg2.test(text[i])) {

                    hanzi++;
                }
                else {
                    biaodian++;
                }

            }
            arr.push(yingwen);
            arr.push(shuzi);
            arr.push(hanzi);
            arr.push(biaodian);
            console.log(arr);
            var first =[];
            for(i = 0;i<arr.length;i++){
                first.push(arr[i]);
            }

            $scope.arr = arr;

            var obj={};
            var str = ["英文字母","数字","中文汉字","中英文标点符号"];
            for(i = 0;i<str.length;i++){
                if(!obj[str[i]]){
                    obj[str[i]] = arr[i];
                }
                else{
                    obj[str[i]] =0;
                }
            }
            console.log(obj);
            var array = first.sort(function(a,b){
                return b-a;
            });
            $scope.arr1 = array;

            console.log(array);
            console.log(arr);

            var max = 0;
            var maxchar;
            for(var pro in obj){
                if(max<obj[pro]){
                    max = obj[pro];
                    maxchar = pro;
                }
                console.log(pro)

            }
            $scope.obj = obj;
        }


        $scope.clear = function(){
            var text1 = document.getElementById("text");
            text1.value="";

        }
    })
</script>
</html>
