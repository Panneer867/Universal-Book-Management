$(document).ready(function() {

	if (window.location.href === 'http://localhost:8080/company/register?success') {
		setTimeout(function() { window.location = "http://localhost:8080/login"; }, 3000);
	}
	
	if (window.location.href === 'http://localhost:8080/company/profile?passwordChanged') {
		setTimeout(function() { window.location = "http://localhost:8080/logout"; }, 3000);
	}
	
	if (window.location.href === 'http://localhost:8080/company/profile?usernameChanged') {
		setTimeout(function() { window.location = "http://localhost:8080/login?usernameChanged"; }, 3000);
	}
	
	if (window.location.href === 'http://localhost:8080/login?usernameChanged') {
		setTimeout(function() { window.location = "http://localhost:8080/logout"; }, 2000);
	}
});

$(document).ready(function() {

	if (window.location.href === 'http://192.168.0.203:9292/company/register?success') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/login"; }, 3000);
	}
	
	if (window.location.href === 'http://192.168.0.203:9292/company/profile?passwordChanged') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/logout"; }, 3000);
	}
	
	if (window.location.href === 'http://192.168.0.203:9292/company/profile?usernameChanged') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/login?usernameChanged"; }, 3000);
	}
	
	if (window.location.href === 'http://192.168.0.203:9292/login?usernameChanged') {
		setTimeout(function() { window.location = "http://192.168.0.203:9292/logout"; }, 2000);
	}
});

// caching the elements
var input = document.getElementById("companyName"),
	match = document.getElementById("match");

// the main function: get the content from source and display it in destination
function display(source, destination) {
	destination.textContent = source;
}

// events

input.onkeyup = function() { display(this.value, match); };

$('[href="#finish"]').click(function() {
	$('#example-form').submit();
})

