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
	$(this).html('<div class="loader"></div> Loading...');
});


