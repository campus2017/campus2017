window.onload = function() {
	var liList = document.getElementsByClassName("date");
	for ( var liEle in liList) {
		liList[liEle].onclick = function() {
			setTab(this.id);
		}
	}
}
function setTab(eleId) {
	var flightContent = document.getElementsByClassName("flight-content");
	if (eleId == "date4") {
		flightContent[0].style.display = "block";
	} else {
		flightContent[0].style.display = "none";
	}
	var classNameUnclick = "date";
	var classNameClick = "nav-click";
	var liList = document.getElementsByClassName(classNameUnclick);
	for (var i = 0; i < liList.length; i++) {
		liList[i].classList.remove(classNameClick);
	}
	var eleClick = document.getElementById(eleId);
	eleClick.classList.add(classNameClick);
}