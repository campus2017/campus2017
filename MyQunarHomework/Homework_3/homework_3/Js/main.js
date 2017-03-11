window.onload =function () {
    head();
    $(".input-date input").datepicker({
        /* 区域化周名为中文 */
        dayNamesMin : ["日", "一", "二", "三", "四", "五", "六"],
        /* 每周从周一开始 */
        firstDay : 1,
        /* 区域化月名为中文习惯 */
        monthNames : ["1月", "2月", "3月", "4月", "5月", "6月",
            "7月", "8月", "9月", "10月", "11月", "12月"],
        /* 月份显示在年后面 */
        showMonthAfterYear : true,
        /* 年份后缀字符 */
        yearSuffix : "年",
        /* 格式化中文日期
         （因为月份中已经包含“月”字，所以这里省略） */
        dateFormat : "yy年MMdd日"
    });
}
