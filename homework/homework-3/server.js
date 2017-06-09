var path = require('path');
var cors = require('kcors');
var koa = require('koa');
var app = module.exports = new koa();

app.use(cors());
var data = [
    {
        "time": "2014-10-28 15:37:21",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-10-23 21:36:44",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-10-21 15:29:18",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-10-21 15:06:51",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-10-10 14:56:28",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回。测试用例"
    },
    {
        "time": "2014-09-25 14:03:00",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-09-24 11:35:38",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回。测试用例"
    },
    {
        "time": "2014-09-24 11:34:59",
        "name": "博洋梦澜棉被",
        "number": 1,
        "eCoin": 1049,
        "status": "兑换失败",
        "info": "您兑换的商品申请处理失败，金币已退回。测试用例"
    },
    {
        "time": "2014-09-22 15:34:23",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-09-01 12:38:36",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    },
    {
        "time": "2014-08-29 18:26:05",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回。失败处理。"
    },
    {
        "time": "2014-08-29 18:24:37",
        "name": "米奇真空古堡杯",
        "number": 1,
        "eCoin": 323,
        "status": "兑换失败",
        "info": "您兑换的商品申请处理失败，金币已退回。处理中。失败处理。"
    },
    {
        "time": "2014-08-18 17:41:38",
        "name": "手机50元话费充值",
        "number": 1,
        "eCoin": 190,
        "status": "兑换失败",
        "info": "充值失败，E币已退回"
    }
]
// dummy data


// render

app.use(function *() {
  this.body = data;
});

if (!module.parent) app.listen(4000);