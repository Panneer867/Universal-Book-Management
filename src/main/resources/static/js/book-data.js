$(document).ready(function() {

	$('#books').change(function(e) {
		if (e.target.value === "") {
			$("#books").val('')
			alert('Select the Book');
			return false;
		}
		var itemName = $(this).val();
		$.ajax({
			type: "get",
			url: "/get/bundle/item",
			data: { "itemName": itemName },
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				$("#itemPrice").val(jsonobject.mrpPrice);
				$("#costPrice").val(jsonobject.costPrice);
				
			},
			error: function(e) {
				console.log('error occured while fetching hsnCode');
			}
		});

	});
});