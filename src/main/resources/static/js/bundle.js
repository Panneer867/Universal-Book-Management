
$(".add-row").click(function() {

	const $this = $("table tbody");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const rowname = $("#books").val();
	const itemPrice = $("#itemPrice").val();

	if (rowname.length > 0) {
		$('.msg-show').hide();

		let markup = "<tr><td class='sl_no bg-secondary py-1 text-dark'>" + incremented + "</td><td class='bg-info py-1'>" + rowname;
		markup += '</td><td class="bg-success py-1">' + quantity;
		markup += '</td><td class="bg-warning py-1">' + itemPrice;
		markup += '</td><td class="bg-danger py-1">' + gst;
		markup += '</td><td class="bg-dark py-1 text-center">';
		markup += '<button class="p-0 btn btn-sm bg-transparent deleterow" type="submit"><i class="glyphicon glyphicon-trash text-danger"></i>';
		markup += '</td><td class="bg-warning py-1"><i class="fa fa-inr pr-1" aria-hidden="true" style="font-size:13px"></i>' + itemPrice;
		markup += '</td><td class="bg-danger py-1 text-center">';
		markup += '<button class="p-0 btn btn-sm bg-transparent deleterow" type="submit"><i class="glyphicon glyphicon-trash text-white"></i>';
		markup += '</button></td></tr>';

		$("table tbody").append(markup);
	}

});

