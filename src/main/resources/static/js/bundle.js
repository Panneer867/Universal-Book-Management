$(".add-row").click(function() {

	const $this = $(".add-item");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const itemName = $("#itemName").val();
	const itemPrice = $("#itemMrp").val();

	var book = {};
	book.slNo = incremented;
	book.itemName = itemName;
	book.itemMrp = itemPrice;
	var bookJSON = JSON.stringify(book);

	if (itemName.length > 0) {
		$.ajax({
			url: '/post/bundle/item',
			method: 'POST',
			data: bookJSON,
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				if (jsonobject.itemExists === "True") {
					$('.no-records').hide();
					let markup = "<tr><td class='sl_no bg-secondary py-1 text-dark'>" + incremented + "</td><td class='bg-info py-1 item_name'>" + itemName;
					markup += '</td><td class="bg-warning py-1"><i class="fa fa-inr pr-1" aria-hidden="true" style="font-size:13px"></i>' + itemPrice
					markup += '</td><td class="bg-danger py-1 text-center">'
					markup += '<button class="p-0 btn btn-sm bg-transparent delete-row"><i class="glyphicon glyphicon-trash text-white"></i>'
					markup += '</button></td></tr>';
					$(".add-item").append(markup);
				} else { alert("This Item has been already added !"); }
			},
			error: function(e) {
				console.log('error occured while posting data');
			}
		});
	}
});

$(".add-item").on("click", ".delete-row", function() {

	var bookData = {};
	var $row = $(this).closest("tr");
	bookData.itemName = $row.find(".item_name").text();
	var bookName = JSON.stringify(bookData);

	$.ajax({
		url: '/post/bundle/item/id',
		method: 'POST',
		data: bookName,
		contentType: "application/json; charset=utf-8",
		success: function() {
		},
		error: function(e) {
			console.log('error occured while deleting data');
		}
	});

	$(this).parent().parent().remove();
	$("tr .sl_no").each(function(i) {
		$(this).text(i + 1);
	});
});

