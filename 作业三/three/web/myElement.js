/**
 * Created by chao on 2017/7/3.
 */
document.addEventListener("DOMContentLoaded", function () {
    //----------------------button_group
    var buttonGroups = document.getElementsByClassName("button_group");
    for (var i = 0; i < buttonGroups.length; i++) {
        var buttonGroup = buttonGroups[i];
        buttonGroup.addEventListener("click", function (event) {
            event = event || window.event;
            var buttons = this.getElementsByTagName("button");
            for (var h = 0; h < buttons.length; h++) {
                var classNames = buttons[h].className.split(" ");
                if (classNames.indexOf("button_no") != -1) {
                    classNames.splice(classNames.indexOf("button_no"), 1);
                }
                if (classNames.indexOf("button_light") != -1) {
                    classNames.splice(classNames.indexOf("button_light"), 2);
                }
                if (event.target === buttons[h]) {
                    classNames.push("button_light");
                    buttons[h].className = classNames.join(" ");
                } else {
                    classNames.push("button_no");
                    buttons[h].className = classNames.join(" ");
                }
            }
        })
    }
})
