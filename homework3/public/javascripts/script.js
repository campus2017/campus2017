(function () {

    var status = 2;

    var allStatus = document.getElementById('all');
    var successStatus = document.getElementById('success');
    var defaultStatus = document.getElementById('default');
    var setStatus = (function () {
        var now = allStatus;

        return function () {
            this.className = 'u-select';
            if(now != this){
                now.className = 'u-select anti';
                now = this;
            }

            if(now === allStatus) status = 2;
            if(now === successStatus) status = 1;
            if(now === defaultStatus) status = 0;
            filterData();
        }

    })();
    allStatus.onclick = setStatus;
    successStatus.onclick = setStatus;
    defaultStatus.onclick = setStatus;

    var startTime, endTime;

    var startTimeEle = document.getElementById('start-time');
    var endTimeEle = document.getElementById('end-time');

    var search = document.getElementById('search');
    var reset = document.getElementById('reset');

    search.onclick = function () {
        function setDateTime(dateObj,dates) {
            if(dates.length ===3) {
                dateObj.setFullYear(dates[0]);
                dateObj.setMonth(dates[1]-parseInt(1));
                dateObj.setDate(dates[2]);
            }
        }
        startTime || (startTime = new Date());
        endTime || (endTime = new Date());
        var _start = startTimeEle.value.split(/-|\//);
        var _end = endTimeEle.value.split(/-|\//);

        setDateTime(startTime, _start);
        setDateTime(endTime, _end);
        filterData();
    };

    reset.onclick = function () {
        (startTime = null) && (startTimeEle.value = '');
        (endTime = null) && (endTimeEle.value = '');
        filterData();
    };

    var allData, nowData;
    var numOnePage = 10;
    var nowPage;
    var allPageNum = 1;

    var pageNum = document.getElementById('page-num');
    var pages = document.getElementById('pages');
    var page = document.createElement('span');
    var pageNodes = [page];
    var pdf = document.createDocumentFragment();

    var tBody = document.getElementById('tBody');
    var tr = document.createElement('tr');
    for(var j = 0; j < 8; j++){
        var td =document.createElement('td');
        if(j === 6){
            var _span = document.createElement('span');
            _span.className = 'u-select';
            _span.innerText = '兑换信息';
            td.appendChild(_span);
        }
        tr.appendChild(td);
    }
    var trNodes = [tr];
    var tdf = document.createDocumentFragment();

    function filterData() {
        nowData = [];
        allData.forEach(function (v) {
            if(!v._hasSet){
                v._status = (v.status === '兑换失败')? 0 : 1;
                v._time = new Date(v.time);
                v._hasSet = true;
            }
            if((status === 2 || status === v._status) &&
                (!startTime || startTime < v._time) &&
                (!endTime || endTime > v._time)){
                nowData.push(v);
            }
        });
        renderFirstPage();
    }

    function renderFirstPage() {

        allPageNum = Math.ceil(nowData.length / numOnePage);
        if(allPageNum === 0)allPageNum = 1;
        pageNum.innerText = allPageNum;
        for (var i = 1; i <= allPageNum; i++) {
            var _page = (pageNodes[i-1]) || (pageNodes[i-1] = page.cloneNode(true));
            _page.innerText = i;
            _page.className = (i === 1)? 'u-page-num current' : 'u-page-num';
            _page.onclick = function () {
                exchangeNowPageNode(this);
                renderOnePage(this.innerText);
            };
            pdf.appendChild(_page);
        }
        nowPage = pageNodes[0];
        pages.innerHTML='';
        pages.appendChild(pdf);

        renderOnePage(1);
    }

    function renderTbody(from, offset) {
        while (tBody.hasChildNodes()){
            tBody.removeChild(tBody.firstChild);
        }
        for (var j = from; j < from + offset; j++) {
            var _tr = (trNodes[j - from]) || (trNodes[j - from] = tr.cloneNode(true));
            var _tds = _tr.getElementsByTagName('td');
            _tds[0].innerText = nowData[j].time;
            _tds[1].innerText = nowData[j].name;
            _tds[2].innerText = nowData[j].num;
            _tds[3].innerText = nowData[j].money;
            _tds[4].innerText = nowData[j].status;
            _tds[5].innerText = nowData[j].message;
            tdf.appendChild(_tr);
        }
        tBody.appendChild(tdf);
    }

    function renderOnePage(num) {
        var from = ( num - 1 ) * numOnePage;
        if(from < nowData.length || nowData.length === 0) {
            var offset = Math.min(numOnePage, nowData.length - from);
            renderTbody(from, offset);
        }
    }

    function exchangeNowPageNode(node) {
        if(nowPage === node)return;
        node.className = 'u-page-num current';
        nowPage.className = 'u-page-num';
        nowPage = node;
    }

    var verify = document.getElementById('verify');
    var jump = document.getElementById('jump');
    var firstPage = document.getElementById('first-page');
    var endPage = document.getElementById('end-page');

    firstPage.onclick = function () {
        exchangeNowPageNode(pageNodes[0]);
        renderOnePage(1);
    };

    endPage.onclick = function () {
        exchangeNowPageNode(pageNodes[allPageNum - 1]);
        renderOnePage(allPageNum);
    };

    verify.onclick = function () {
        var num = jump.value;
        exchangeNowPageNode(pageNodes[num - 1]);
        renderOnePage(num);
    };
    
    var request = new XMLHttpRequest();
    request.open('GET','/record');
    request.onreadystatechange = function () {

        if(request.readyState === 4 && (request.status === 200 || request.status === 304)) {
            var type = request.getResponseHeader('Content-Type');
            if(type.indexOf('application/json') !== -1 ){
                allData = JSON.parse(request.responseText);
                filterData();
            }
        }
    };
    request.send(null);

})();