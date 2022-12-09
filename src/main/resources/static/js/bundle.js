
$(".add-row").click(function() {

	const $this = $("table tbody");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const rowname = $("#books").val();
	const itemPrice = $("#itemPrice").val();

	$('.bookName').text(rowname);
	$('.price').val(itemPrice);

	if (rowname.length > 0) {
		$('.msg-show').hide();
		$('.grand').show();

		let markup = "<tr><td class='sl_no'><label class='mb-0'>" + incremented + "</label></td></tr>";

		$("table tbody").append(markup);
	}

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

		$(".vat").val(((subtotalVal / 100) * 18).toFixed(2));

		var vatVal = ((subtotalVal / 100) * 18).toFixed(2);
		var total = parseFloat(subtotalVal) + parseFloat(vatVal);
		$(".total").val((total).toFixed(2));
	}

	$(".glyphicon-trash").click(function() {
		$(this).parent().parent().remove();
		calculate();
	});

});

$(document).ready(function() {
	$('.grand').hide();
});
