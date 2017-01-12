var express = require('express');
var app = express();

var allob={
1:["2014-10-28 15:37:21","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
2:["2014-10-23 21:34:44","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
3:["2014-10-21 15:29:18","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
4:["2014-10-21 15:06:51","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
5:["2014-10-10 14:56:28","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。测试用例","<p>兑换信息</p>"," "],
6:["2014-09-25 14:03:00","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
7:["2014-09-24 11:35:38","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。测试用例","<p>兑换信息</p>"," "],
8:["2014-09-24 14:34:59","博洋梦澜棉被","1","1049","兑换失败","您兑换的商品申请处理失败，金币已退回。测试用例","<p>兑换信息</p>"," "],
9:["2014-09-22 15:34:23","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
10:["2014-09-01 12:38:36","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "],
11:["2014-08-29 18:26:05","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。失败处理","<p>兑换信息</p>"," "],
12:["2014-08-29 18:24:37","米奇真空古堡杯","1","323","兑换失败","您兑换的商品申请处理失败，金币已退回。处理中。失败处理。","<p>兑换信息</p>"," "],
13:["2014-08-18 17:41:38","手机50元话费充值","1","190","兑换失败","充值失败，E币已退回。","<p>兑换信息</p>"," "]
}
var backob={};
var backyn=false;


app.get('/', function (req, res) {
  res.send('Hello World!');
  console.log("hello world cwj")
});

app.get('/getdata', function (req, res) {
	var querys=req.query;
	res.set({
  'Content-Type': 'text/plain',//application/json
  'Content-Length': '123',
  'ETag': '12345',
  'Cache-Control':'no-cache',
  'Expires':'0',
  'Pragma':'no-cache',
  'Access-Control-Allow-Origin': '*'
});
	if(querys.way=="all")
	{
		res.send(JSON.stringify(allob));
	}
	else if(querys.way=="suc")
	{
		for( x in allob)
		{
			if(allob[x][4]=="兑换成功")
			{
				backyn=true;
				backob[x]=allob[x];
			}
		}
		if(backyn)
		{
			res.send(JSON.stringify(backob));
			backob={};
			backyn=false;
		}
		else{
			res.send("nothing");
		}
	}
	else if(querys.way=="fail")
	{
		for( x in allob)
		{
			if(allob[x][4]=="兑换失败")
			{
				backyn=true;
				backob[x]=allob[x];
			}
		}
		if(backyn)
		{
			res.send(JSON.stringify(backob));
			backob={};
			backyn=false;
		}
		else{
			res.send("nothing");
		}
	}
	else if(querys.way=="sear")
	{
		var startd=new Date(querys.startd).getTime();
		var endd=new Date(querys.endd).getTime();
		for( x in allob)
		{	
			var nowd=new Date(allob[x][0]).getTime();
			console.log(startd+"   "+endd+"   "+nowd);
			if(nowd<=endd && nowd>=startd)
			{
				backyn=true;
				backob[x]=allob[x];
			}
		}
		if(backyn)
		{
			res.send(JSON.stringify(backob));
			backob={};
			backyn=false;
		}
		else{
			res.send("nothing");
		}
	}
	
});

var server = app.listen(8866, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});