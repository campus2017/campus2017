$(function(){  
    timelineChange();
    let $tabs = $(".tab ul li");
    for(let i = 0; i < $tabs.length; i++){
        $($tabs[i]).hover(function(){
            $($tabs[i]).addClass("tab-selected").siblings().removeClass("tab-selected");
        },function(){
            $($tabs[i]).removeClass("tab-selected");
        });
    }
});
/*时间轴根据当前时间变色*/
function timelineChange(){
   let $timeline = $(".timeline > div");
    let now = new Date();
    let signTime = new Date("2014-11-10");      //征集泡汤男女时间
    let chooseTime = new Date("2014-11-25");    //进入评选阶段时间
    let resultTime = new Date("2014-11-30");    //公布评选结果时间
    for(let i = 0; i < $timeline.length; i++){
        if(now < signTime){
            $($timeline[i]).find(".time-selected").css("display","none");
            $($timeline[i]).find(".time-normal").css("display","none");
            $($timeline[i]).find(".time-gray").css("display","inline");
        }else if (now < chooseTime){
            if(i == 0){
                $($timeline[i]).find(".time-selected").css("display","inline");
                $($timeline[i]).find(".time-normal").css("display","none");
                $($timeline[i]).find(".time-gray").css("display","none"); 
            }else{
                $($timeline[i]).find(".time-selected").css("display","none");
                $($timeline[i]).find(".time-normal").css("display","none");
                $($timeline[i]).find(".time-gray").css("display","inline");
            }
        }else if(now < resultTime){
            if(i < 1){
                $($timeline[i]).find(".time-selected").css("display","none");
                $($timeline[i]).find(".time-normal").css("display","inline");
                $($timeline[i]).find(".time-gray").css("display","none"); 
            }else if(i == 1){
                $($timeline[i]).find(".time-selected").css("display","inline");
                $($timeline[i]).find(".time-normal").css("display","none");
                $($timeline[i]).find(".time-gray").css("display","none"); 
            }else{
                $($timeline[i]).find(".time-selected").css("display","none");
                $($timeline[i]).find(".time-normal").css("display","none");
                $($timeline[i]).find(".time-gray").css("display","inline"); 
            }
        }else{
            if(i < 2){
                $($timeline[i]).find(".time-selected").css("display","none");
                $($timeline[i]).find(".time-normal").css("display","inline");
                $($timeline[i]).find(".time-gray").css("display","none");
            }else{
                $($timeline[i]).find(".time-selected").css("display","inline");
                $($timeline[i]).find(".time-normal").css("display","none");
                $($timeline[i]).find(".time-gray").css("display","none");
            }
        }
    } 
}