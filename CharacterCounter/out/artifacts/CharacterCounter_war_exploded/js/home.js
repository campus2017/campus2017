$('#btnCount').click(function () {
    var stringContent = $('#content').val();
    var chinese = stringContent.replace(/[^\u4E00-\u9FA5]/g,"");
    var english = stringContent.replace(/[^a-zA-Z]/g,"");
    var number = stringContent.replace(/[^\d]/g,"");
    var point = stringContent.replace(/[^.|,|!|，|。|！|、|；|‘|’|”|“|：|;|:|'|"|?|？|）|（|)|(|&|%]/g,"");

    resetTd(chinese.length, english.length, number.length, point.length);
    var chineseSort = getTop3(chinese);
    var first = chineseSort.pop();
    var second = chineseSort.pop();
    var third = chineseSort.pop();
    resetTd2(first, second, third);
});

$('#btnReset').click(function () {
    $('#content').val("");
    resetTd("", "", "", "");
    resetTd2(undefined, undefined, undefined);
});

function resetTd(chinese, english, number, point){
    $('#chineseNum').text(chinese);
    $('#englishNum').text(english);
    $('#numberNum').text(number);
    $('#pointNum').text(point);
}

function resetTd2(first, second, third) {
    $('#firstName').text("");
    $('#firstCount').text("");
    $('#secondName').text("");
    $('#secondCount').text("");
    $('#thirdName').text("");
    $('#thirdCount').text("");
    if(first != undefined){
        $('#firstName').text(first[0]);
        $('#firstCount').text(first.length);
    }
    if(second != undefined){
        $('#secondName').text(second[0]);
        $('#secondCount').text(second.length);
    }
    if(third != undefined){
        $('#thirdName').text(third[0]);
        $('#thirdCount').text(third.length);
    }
}

function getTop3(chinese){
    var chineseSort = chinese.split('').sort().join('').match(/(.)\1*/g);
    chineseSort = chineseSort.sort(function(a,b){return a.length - b.length});
    return chineseSort;
}