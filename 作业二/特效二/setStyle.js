/**
 * Created by chao on 2016/10/14.
 */
function setStyle(element, styleObj) {
    if (typeof styleObj != "object") {
        return;
    }
    var styleName = "";
    var style = "";
    var tem;
    for (var i in styleObj) {
        styleName = i;
        style = styleObj[i];
        if (styleName.indexOf('-') != -1) {
            styleName = styleName.split('-');
            tem = "";
            for (var h = 0; h < styleName.length; h++) {
                if (h == 0) {
                    tem += styleName[h];
                    continue;
                }
                tem += (styleName[h].charAt(0).toLocaleUpperCase() + styleName[h].substring(1));
            }
            styleName = tem;
        }
        element.style[styleName] = style;
    }
}