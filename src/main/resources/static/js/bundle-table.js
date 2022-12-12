$(".bundle-it").click(function() {
	$.ajax({
		type: "GET",
		url: "/get/temp/item",
		dataType: "json",
		success: function(data) {
			var json = JSON.stringify(data);
			var jsonData = JSON.parse(json);
			console.log(data);
			var html = "<tbody>";
			$.each(jsonData, function() {
				html +=
					'<tr class="cart-item">'  +
					"<td class='text-center'>" + "<label class='mb-0'>" + this.slNo + "</label>" + "</td>" +
					"<td class='product'>" + "<label class='mb-0'>" + this.itemName + "</label>" + "</td>" +
					"<td>" + this.itemMrp + "</td>" +
					"<td>" + '<input class="input is-primary cart-item-qty" style="width:100px" type="number" min="1" value="' + this.quantity + '" data-price="' + this.itemMrp + '">' + "</td>" +
					'<td class="cart-item-total">' + this.total + "</td>" +
					"<td>" + '<a class="button is-small">删除</a>' + "</td>" +
					"</tr>";
			});
			html += "</tbody>";
			$(".shopping-cart").append(html);

			recalculateCart();

			$(".cart-item-check").change(function() {
				recalculateCart();
			});

			$(".cart-item-qty").change(function() {
				var $this = $(this);
				var parent = $this.parent().parent();
				parent.find(".cart-item-check").prop("checked", "checked");
				var itemMrp = $this.attr("data-price");
				var quantity = $this.val();
				var total = itemMrp * quantity;
				parent.find(".cart-item-total").html(total.toFixed(2));
				recalculateCart();
			});

			$(".button").click(function() {
				var parent = $(this)
					.parent()
					.parent();
				parent.remove();
				recalculateCart();
			});
		},
		error: function(e) {
			console.log('error occured while fetching hsnCode');
		}
	});
});

function recalculateCart() {

	var taxRate = 0.05;
	var shipping = 15.0;
	var subTotal = 0;
	var grandTotal = 0;
	var tax = 0;
	var items = $(".cart-item");
	$.each(items, function() {
		var itemCheck = $(this).find(".cart-item-check");
		var itemQuantity = $(this).find(".cart-item-qty");
		if (itemCheck.prop("checked")) {
			var itemTotal = itemQuantity.val() * itemQuantity.attr("data-price");
			subTotal += itemTotal;
		}
	});
	if (subTotal > 0) {
		tax = subTotal * taxRate;
		grandTotal = subTotal + tax + shipping;
		$(".totals,.checkout").show();
	} else {
		$(".totals,.checkout").hide();
	}
	$("#cart-subtotal").html(subTotal.toFixed(2));
	$("#cart-total").html(grandTotal.toFixed(2));
	$("#cart-tax").html(tax.toFixed(2));
	$("#cart-shipping").html(shipping.toFixed(2));
}