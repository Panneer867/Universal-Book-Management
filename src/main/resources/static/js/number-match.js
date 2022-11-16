var check = function() {
	if (document.getElementById('mobile').value ==
		document.getElementById('alternateMobile').value) {
		document.getElementById('message').style.color = "red";
		document.getElementById('message').innerHTML = "Alternate mobile no shouldn't same as mobile no field";
	} else {
		document.getElementById('message').innerHTML = "";
	}
}

var checkMobile = function() {
	if (document.getElementById('emobileCopy').value ==
		document.getElementById('ealternateMobileCopy').value) {
		document.getElementById('message').style.color = "red";
		document.getElementById('message').innerHTML = "Alternate mobile no shouldn't same as mobile no field";
	} else {
		document.getElementById('message').innerHTML = "";
	}
}

var checkMobileOwner = function() {
	if (document.getElementById('mobileNoCopy').value ==
		document.getElementById('alternateMobileCopy').value) {
		document.getElementById('message').style.color = "red";
		document.getElementById('message').innerHTML = "Alternate mobile no shouldn't same as mobile no field";
	} else {
		document.getElementById('message').innerHTML = "";
	}
}