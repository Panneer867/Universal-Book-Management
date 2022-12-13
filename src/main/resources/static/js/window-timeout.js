$(document).ready(function() {

	if (window.location.href === 'http://localhost:8080/company/register?success') {
		setTimeout(function() { window.location = "http://localhost:8080/login"; }, 3000);
	}

	if (window.location.href === 'http://localhost:8080/login?userDetailsChanged') {
		setTimeout(function() { window.location = "http://localhost:8080/logout"; }, 1000);
	}

	if (window.location.href === 'http://localhost:8080//login?companyDeleted') {
		setTimeout(function() { window.location = "http://localhost:8080/logout"; }, 1000);
	}
	
	if (window.location.href === 'http://192.168.0.203:9292/company/register?success') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/login"; }, 3000);
	}

	if (window.location.href === 'http://192.168.0.203:9292/login?userDetailsChanged') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/logout"; }, 3000);
	}

	if (window.location.href === 'http://192.168.0.203:9292//login?companyDeleted') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/logout"; }, 1000);
	}

});