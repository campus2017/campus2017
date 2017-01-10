/**
 * Created by yulei on 16-12-29.
 */

function judgeChildStrType(tag, desInput) {
    if (!desInput)return;
    if (typeof desInput == "object") {
        tag.appendChild(desInput);
    } else {
        tag.innerHTML = desInput;
    }
    return tag;
}

function createTag(type, arrValue, childTag1, childTag2) {
    var tag = document.createElement(type);
    if (type == "img") {
        tag.src = arrValue
    } else if (type == "a") {
        tag.href = "index.html";
        tag.target = "_blank";
        tag.className = arrValue;
    } else {
        tag.className = arrValue;
    }
    judgeChildStrType(tag, childTag1);
    judgeChildStrType(tag, childTag1);
    return tag;
}
function addHTMLToID(titleData, itemData) {
    var id = document.getElementById("goods-list");
    var chargeStr1 = "<i>&yen;<span class=\"font30 f-bold\">" + itemData.charge + "</span></i></br>";
    var chargeStr2 = "<del class=\"font10 c-white70\"><i>原价：&yen;" + itemData.charge2 + "</i></del>";
    var chargeStr3 = "<i><del class=\"c-white70 font10\">原价：&yen;" + itemData.charge2 + "<\/del>&nbsp;&nbsp;&yen;<span class=\"font20 f-bold\">" + itemData.charge + "</span></i>";
    for (var i = 0; i < 4; i++) {
        var titleDiv = createTag("div", "goods-list-title");
        if (i == 3) {
            titleDiv.appendChild(createTag("span", "font30 f-bold", titleData[i].t_text));
            titleDiv.appendChild(createTag("a", "font10 more-link", titleData[i].t_link));
        } else {
            titleDiv.appendChild(createTag("img", titleData[i].t_img));
            titleDiv.appendChild(createTag("span", "font30 f-bold t-border", titleData[i].t_text));
            titleDiv.appendChild(createTag("span", "font12", titleData[i].t2_text));
            titleDiv.appendChild(createTag("a", "font10 more-link", titleData[i].t_link));
        }
        id.appendChild(titleDiv);

        var item1Div = createTag("div", "goods-list-item1");
        var item1InfoDiv = createTag("div", "goods-list-item1-info");
        item1InfoDiv.appendChild(createTag("div", "font18 f-bold line-height2", itemData.title));
        item1InfoDiv.appendChild(createTag("span", "font10 line-height2", itemData.item1_text));
        item1InfoDiv.appendChild(createTag("div", "margin-top20 align-center", chargeStr1));
        item1InfoDiv.appendChild(createTag("div", "align-center", chargeStr2));
        item1InfoDiv.appendChild(createTag("span", "mark", "立即预定"));
        item1Div.appendChild(item1InfoDiv);
        id.appendChild(item1Div);

        for (var j = 0; j < 4; j++) {
            var classVal = (j % 2 != 0) ? "goods-list-item2" : "goods-list-item2 margin-right5";
            var item2Div = createTag("div", classVal);
            var item2InfoDiv = createTag("div", "goods-list-item2-info");
            item2InfoDiv.appendChild(createTag("div", "font15 f-bold", itemData.title));
            item2InfoDiv.appendChild(createTag("div", "font10", itemData.item2_text));
            item2InfoDiv.appendChild(createTag("div", "", chargeStr3));
            item2InfoDiv.appendChild(createTag("div", "mark", "立即预定"));
            item2Div.appendChild(item2InfoDiv);
            id.appendChild(item2Div);
        }
    }
}
function judgeTimeIfTrue() {
    var timeDivs = document.getElementsByClassName("t-ax-item"),
        currentTime = Date.now(),
        dateTimeMs = 24 * 60 * 60 * 1000,
        timeSpans,
        flag = false;
    for (var i = 0; i < timeDivs.length; i++) {
        timeSpans = timeDivs[i].getElementsByClassName("font23");
        var tempTimeStr = timeSpans[0].innerHTML.replace(/\./g, "/");
        var tempTime = Date.parse(tempTimeStr);
        if (currentTime < tempTime + dateTimeMs) {
            timeDivs[i].className = timeDivs[i].className + ' active';
            if (!flag) {
                var tempSpans = timeDivs[i].getElementsByClassName("t-ax-text-item");
                tempSpans[0].className = tempSpans[0].className + " active";
                flag = true;
            }
        }

    }
}
function goToNewTag() {
    window.open("index.html", "_blank");
}
function goToTop() {
    var scrollVal = getScrollValue();
    if (scrollVal == 0) {
    }
    else {
        if (scrollVal / 50 > 0) {
            setScrollValue(scrollVal, 50);
        } else {
            setScrollValue(scrollVal, scrollVal / 50);
        }
        setTimeout(goToTop, 5);
    }
}
function getScrollValue() {
    var scrollVal;
    if(document.compatMode && document.compatMode == "CSS1Compat" && document.documentElement.scrollTop){
        scrollVal = document.documentElement.scrollTop;
    }else {
        scrollVal = document.body.scrollTop;
    }
    return scrollVal;
}
function setScrollValue(currentScroll, scrollVal) {
    if(document.compatMode && document.compatMode == "CSS1Compat" && document.documentElement.scrollTop){
        document.documentElement.scrollTop = currentScroll - scrollVal;
    }else {
        document.body.scrollTop = currentScroll - scrollVal;
    }
}
window.onload = function () {
    var titleArrData = [
        {
            "t_img": "img/group1-logo.png",
            "t_text": '人生第一“泡”',
            "t_link": "查看全部温泉酒店》",
            "t2_text": "经济实惠，泡之初体验"
        },
        {
            "t_img": "img/group2-logo.png",
            "t_text": '双享“泡”',
            "t_link": "查看全部温泉酒店》",
            "t2_text": '此水只应天上有，人间难得几回"泡"'
        },
        {
            "t_img": "img/group3-logo.png",
            "t_text": '团圆“泡”',
            "t_link": "查看全部温泉酒店》",
            "t2_text": '感情，是"泡"出来的！'
        },
        {
            "t_text": "更多超值酒店精选",
            "t_link": "查看全部温泉酒店》"
        }
    ];
    var itemData = {
        "title": "北京天上人间温泉酒店",
        "charge": 1999,
        "charge2": 2999,
        "item1_text": "北京天上人间温泉酒店北京天上人间温泉酒店北京天上人间",
        "item2_text": "北京天上人间温泉酒店北京天上人间温泉"
    };

    addHTMLToID(titleArrData, itemData);
    judgeTimeIfTrue();
};
