/**
 * Created by chao on 2017/7/5.
 */
var compareButtons = document.getElementsByClassName("button_compare");
for (var i = 0; i < compareButtons.length; i++) {
    compareButtons[i].addEventListener("click", function (event) {
        var rowGroup = findParent(this, "row_group");
        if (rowGroup) {
            var groupHide = rowGroup.querySelector(".row_group_hide");
            if (this.isHide == undefined || this.isHide) {
                this.getElementsByTagName("img")[0].src = "images/bottom_arrow.png";
                groupHide.style.display = "block";
                this.isHide = false;
            } else {
                this.getElementsByTagName("img")[0].src = "images/top_arrow.png";
                groupHide.style.display = "none";
                this.isHide = true;
            }
        }
    })
}

function findParent(element, parentClass) {
    var parent = element.parentElement;
    if (parent === document.body) {
        return null;
    }
    if (parent.className.split(" ").indexOf(parentClass) != -1) {
        return parent;
    } else {
        return findParent(parent, parentClass);
    }
}
