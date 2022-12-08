$(".add-row").click(function() {
	// $(".demorow").remove();
	const $this = $("table tbody");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const rowname = $("#books").val();
	const quantity = $("#qty").val();
	const itemPrice = $("#itemPrice").val();



	if (rowname.length > 0) {
		$('.msg-show').hide();
		$(".grand-total").show();
		let markup = "<tr><td class='sl_no bg-secondary py-1 text-dark'>" + incremented + "</td><td class='bg-info py-1'>" + rowname;
		markup += '</td><td class="bg-success py-1 pick-quantity">' + quantity
		markup += '</td><td class="bg-warning py-1"><i class="fa fa-inr pr-1" aria-hidden="true" style="font-size:13px"></i>' + itemPrice
		markup += '</td><td class="bg-danger py-1 text-center">'
		markup += '<button class="p-0 btn btn-sm bg-transparent deleterow" type="submit"><i class="glyphicon glyphicon-trash text-white"></i>'
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
	var totalQuantity = $("td .pick-quantity").text();
		
		alert(totalQuantity);
		var rowQuantity = Number($this.find("td .pick-quantity").val());
		tQ =  totalQuantity - rowQuantity;
		$("#totalQty").val(totalQuantity);
	
});


$(document).ready(function() {
	$('.grand-total').hide();
});

$(".total-sum").click(function() {

	const rowname = $("#books").val();
	if (rowname.length > 0) {
		var tQ = $("#totalQty").val();
		var totalQuantity = $("#qty").val();
		tQ = +tQ + +totalQuantity;
		$("#totalQty").val(tQ);
	}
});