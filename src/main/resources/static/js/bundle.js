
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

});


$(function() {

	calculate();

	$(".button").on("click", function() {

		var $button = $(this);
		var oldQty = $button.parent().parent().find("input").val();

		if ($button.html() == '<i class="fa fa-plus bg-success" style="font-size: 0.6em; color: #fff; padding: 5px;"></i>') {
			var newQty = parseFloat(oldQty) + 1;
		} else {
			if (oldQty > 0) {
				var newQty = parseFloat(oldQty) - 1;
			} else {
				newQty = 0;
			}
		}

		$button.parent().parent().find("input").val(newQty);
		calculate();
	});

	function calculate() {
		$(".basket-tbl tr").each(function() {
			var priceVal = $(this).find('input.price').val();
			var qtyVal = $(this).find("input.qty").val();
			var costVal = (priceVal * qtyVal);
			$(this).find('input.cost').val((costVal).toFixed(2));
		});

		var subtotalVal = 0;
		$('.cost').each(function() {
			subtotalVal += parseFloat($(this).val());
		});
		$('.subtotal').val((subtotalVal).toFixed(2));

		$(".vat").val(((subtotalVal / 100) * 20).toFixed(2));

		var vatVal = ((subtotalVal / 100) * 20).toFixed(2);
		var total = parseFloat(subtotalVal) + parseFloat(vatVal);
		$(".total").val((total).toFixed(2));
	}


	$(".glyphicon-trash").click(function() {
		$(this).parent().parent().remove();
		calculate();
	});

});

