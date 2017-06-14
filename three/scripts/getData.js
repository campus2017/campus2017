$(document).ready(function(){
    let $table = $(".m-content table");
    let $tableTr = $(".m-content table tr:not(:first)");
    let $currentPage = $(".m-content .current-page");
    let $allPage = $(".m-content .all-page");
    let $firstPage = $(".m-content .first-page");
    let $lastPage = $(".m-content .last-page");
    let $confirmButton = $(".m-content .table-foot button");

    let currentPage = 1;
    let data;

    const MAXLINE = 13;

    //获取数据
    const getDataPromise = new function(){
        return new Promise(function(resolve,reject){
            $.get("data/data.json",function(str,status){
                data =  $.parseJSON(str);
                if(status == "success"){
                    resolve(data);
                }else{
                    reject();
                }
            });
        });
    }

    //更新UI
    getDataPromise.then(function(data){
        for(let i = 0; i < data.length && i < MAXLINE; i++){
            let date = data[i].date;
            let time = data[i].time;
            let name = data[i].name;
            let amount = data[i].amount;
            let eCoin = data[i].eCoin;
            let state = data[i].state;
            let info = data[i].info;

            let tr = `<tr><td><span>${date}</span><br><span>${time}</span></td><td>${name}</td><td>${amount}</td><td>${eCoin}</td><td>${state}</td><td><p>${info}</p></td><td><span>兑换信息</span></td></tr>`;

            $table.append(tr);
        }
        let allPage = Math.ceil(data.length / MAXLINE);
        $allPage.html(allPage);
        $currentPage.html(currentPage);
    });

    //返回第一页数据
    $firstPage.click(function(){
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
        
        if(currentPage != 1){

        }
    });
});