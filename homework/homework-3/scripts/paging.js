window.onload = function() {
	var eTabRecord = document.getElementById("e-tab-record");
	var eTabDetail = document.getElementById("e-tab-detail");
	var eTabExchange = document.getElementById("e-tab-exchange");
	eTabRecord.onclick = function() {
		setTab(this.id);
	};
	eTabDetail.onclick = function() {
		setTab(this.id);
	};
	eTabRecord.onclick = function() {
		setTab(this.id);
	};
	eTabExchange.onclick = function() {
		setTab(this.id);
	};
}
function setTab(eleID) {
	var classNameClick = "tab-click-on";
	var eTabRecord = document.getElementById("e-tab-record");
	var eTabDetail = document.getElementById("e-tab-detail");
	var eTabExchange = document.getElementById("e-tab-exchange");
	var eExchangeContainer = document
			.getElementById("e-exchange-container");
	var eDetailContainer = document.getElementById("e-detail-container");
	var eRecordContainer = document.getElementById("e-record-container");
	eTabRecord.classList.remove(classNameClick);
	eTabDetail.classList.remove(classNameClick);
	eTabExchange.classList.remove(classNameClick);
	if (eleID == "e-tab-record") {
		eTabRecord.classList.add(classNameClick);
		eExchangeContainer.style.display = "none";
		eDetailContainer.style.display = "none";
		eRecordContainer.style.display = "block";
	} else if (eleID == "e-tab-detail") {
		eTabDetail.classList.add(classNameClick);
		eExchangeContainer.style.display = "none";
		eDetailContainer.style.display = "block";
		eRecordContainer.style.display = "none";
	} else {
		eTabExchange.classList.add(classNameClick);
		eExchangeContainer.style.display = "block";
		eDetailContainer.style.display = "none";
		eRecordContainer.style.display = "none";
	}
};