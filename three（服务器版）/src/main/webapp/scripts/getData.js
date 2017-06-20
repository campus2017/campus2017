$(document).ready(function(){
    let $table = $(".m-content table");
    let $tableTr = $(".m-content table tr:not(:first)");
    let $currentPage = $(".m-content .current-page");
    let $allPage = $(".m-content .all-page");
    let $firstPage = $(".m-content .first-page");
    let $lastPage = $(".m-content .last-page");
    let $targetPage = $(".m-content .table-foot input");
    let $confirmButton = $(".m-content .table-foot button");

    let currentPage = 1;
    let data;
    let allPage;
    let targetPage;

    const MAXLINE = 10;

    //获取数据
    const getDataPromise = new function(){
        return new Promise(function(resolve,reject){
        	$.get("/three/index",function(str,status){
        		data = $.parseJSON(str.replace(/'/g, '"'));
        		console.log(data);
        		if(status == "success"){
                    resolve(data);
                }else{
                    reject();
                }
        	});
        	/*本地数据模拟
            $.get("data/data.json",function(str,status){
                data =  $.parseJSON(str);
                if(status == "success"){
                    resolve(data);
                }else{
                    reject();
                }
            });*/
        });
    };

    //更新UI
    getDataPromise.then(function(data){
        allPage = Math.ceil(data.length / MAXLINE);
        $allPage.html(allPage);
        $currentPage.html(currentPage);
        showPage(currentPage);
    });

    //返回第一页数据
    $firstPage.click(function(){
        if(currentPage != 1){
            let th = `<tr>
                        <th class="time"><span>时间</span></th>
                        <th class="name">商品名称</th>
                        <th class="amount">数量</th>
                        <th class="e-coin">E币</th>
                        <th class="state">状态</th>
                        <th class="info">信息</th>
                        <th class="exch-info">兑换信息</th>
                        <th class="confirm">确认</th>
                    </tr>`;
            $table.empty();
            $table.append(th);
            currentPage = 1;
            showPage(currentPage);
        }
    });

    //返回末页数据
    $lastPage.click(function(){
        if(currentPage != allPage){
             let th = `<tr>
                        <th class="time"><span>时间</span></th>
                        <th class="name">商品名称</th>
                        <th class="amount">数量</th>
                        <th class="e-coin">E币</th>
                        <th class="state">状态</th>
                        <th class="info">信息</th>
                        <th class="exch-info">兑换信息</th>
                        <th class="confirm">确认</th>
                    </tr>`;
            $table.empty();
            $table.append(th);
            currentPage = allPage;
            showPage(currentPage);
        }
    });

    //限制页码只能输入正整数
    $targetPage.blur(function(){
        let page = $targetPage.val();
        if(isNaN(page) || page>allPage){
            $targetPage.val("");
        }else{
            targetPage = page;
        }
    });

    //跳转到目标页
    $confirmButton.click(function(){
        if(targetPage>=0 && targetPage<=allPage){
            let th = `<tr>
                        <th class="time"><span>时间</span></th>
                        <th class="name">商品名称</th>
                        <th class="amount">数量</th>
                        <th class="e-coin">E币</th>
                        <th class="state">状态</th>
                        <th class="info">信息</th>
                        <th class="exch-info">兑换信息</th>
                        <th class="confirm">确认</th>
                    </tr>`;
            $table.empty();
            $table.append(th);
            currentPage = targetPage;
            showPage(targetPage);
        }
    });

    //根据传入参数，展示第page页的数据
    function showPage(page){
        if(page < 1 || page > allPage){
            console.log("函数showPage参数错误。");
            return "error";
        }

        for(let i = (page-1)*MAXLINE; i < data.length && i < (page-1)*MAXLINE+MAXLINE; i++){
            let date = data[i].date;
            let time = data[i].time;
            let name = data[i].name;
            let amount = data[i].amount;
            let eCoin = data[i].eCoin;
            let state = data[i].state;
            let info = data[i].info;

            let tr = `<tr><td><span>${date}</span><br><span>${time}</span></td><td>${name}</td><td>${amount}</td><td>${eCoin}</td><td>${state}</td><td><p>${info}</p></td><td><span>兑换信息</span></td></tr>`;

            $table.append(tr);
            $currentPage.html(currentPage);
        } 
    }
});