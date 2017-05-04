$(function(){
    $(".room-seller .room-wrap").each(function(){
        let $roomPayment = $(this).find(".room-payment");
        $roomPayment.css({
            marginTop: ($(this).height()-$roomPayment.height())/2
        });      
    });
});