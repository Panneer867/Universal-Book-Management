$(".add-row").click(function() {
	// $(".demorow").remove();
	const $this = $("table tbody");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const rowname = $("#new_row").val();
	const quantity = $("#qty").val();
	const itemPrice = $("#itemPrice").val();
	const gst = $("#gst").val();

	if (rowname.length > 0) {
		$('.msg-show').hide();
		let markup = "<tr><td class='sl_no bg-secondary py-1 text-dark'>" + incremented + "</td><td class='bg-info py-1'>" + rowname;
		markup += '</td><td class="bg-success py-1">' + quantity
		markup += '</td><td class="bg-warning py-1">' + itemPrice
		markup += '</td><td class="bg-danger py-1">' + gst
		markup += '</td><td class="bg-dark py-1 text-center">'
		markup += '<button class="p-0 btn btn-sm bg-transparent deleterow" type="submit"><i class="glyphicon glyphicon-trash text-danger"></i>'
		markup += '</button></td></tr>';
		$("table tbody").append(markup);
	}
});

$("table tbody").on("click", ".deleterow", function() {
	$(this).parent().parent().remove();
	$("tr .sl_no").each(function(i) {
		// Table tr seriaal  number update.
		$(this).text(i + 1);
	});
});