$(document).ready(function() {

	$('#itemName').change(function(e) {
		if (e.target.value === "") {
			$("#itemName").val('')
			alert('Select the Item');
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

				$("#itemMrp").val(jsonobject.mrpPrice);
				$("#itemCost").val(jsonobject.costPrice);
			},
			error: function(e) {
				console.log('error occured while fetching hsnCode');
			}
		});
	});
});