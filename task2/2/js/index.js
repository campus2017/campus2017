currentActiveImgIndex = 0;
minWidth = 180;
maxWidth = 475;
timerId = -1;

function changeImgWidth(imgDivs, originIndex, desIndex, eachTime, eachChange){
    var tempMinWidth = maxWidth,
        tempMaxWidth = minWidth;
    function changeWidth(){
        if(tempMinWidth > minWidth && tempMaxWidth < maxWidth){
            tempMinWidth = tempMinWidth - eachChange;
            tempMaxWidth = tempMaxWidth + eachChange;
            imgDivs[originIndex].style.width = tempMinWidth + "px";
            imgDivs[desIndex].style.width = tempMaxWidth + "px";
            setTimeout(changeWidth, eachTime);
        }else {

        }
    }
    setTimeout(changeWidth, eachTime);
}

function getImgDivs() {
    var scrollId = document.getElementById("scroll-img");
    var imgDivs = scrollId.getElementsByTagName("div");
    return imgDivs;
}

function changeImgItem(index) {
    if (index == currentActiveImgIndex)return;
    var imgDivs = getImgDivs();
    if(timerId != -1){
        clearTimeout(timerId);
    }
    timerId = setTimeout(function(){
        changeImgWidth(imgDivs, currentActiveImgIndex, index, 10, 10);
        currentActiveImgIndex = index;
    }, 200);
}