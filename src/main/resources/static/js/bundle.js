$(document).ready(function() {
	$('#itemId').change(function(e) {
		if (e.target.value === "") {
			$("#itemId").val('')
			alert('Select the Item');
			return false;
		}
		var itemId = $(this).val();
		$.ajax({
			type: "get",
			url: "/get/bundle/item",
			data: { "itemId": itemId },
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				$("#itemMrp").val(jsonobject.mrpPrice);
				$("#itemCost").val(jsonobject.costPrice);
			},
			error: function(e) {
				alert('error occured while fetching hsnCode' + e);
			}
		});
	});
});

$(".add-row").click(function() {
	const $this = $(".add-item");
	const lastIndex = Number($this.find("td.sl_no:last").text());
	const incremented = lastIndex + 1;
	const itemId = $("#itemId").val();
	const itemPrice = $("#itemMrp").val();
	var book = {};
	book.item = incremented;
	book.itemId = itemId;
	book.itemMrp = itemPrice;
	book.quantity = 1;
	book.slNo = incremented;
	var itemObject = JSON.stringify(book);
	if (itemId > 0) {
		$.ajax({
			url: '/post/bundle/item',
			method: 'POST',
			data: itemObject,
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				console.log(jsonobject);
				if (jsonobject.itemExists === "YES") {
					alert("This Item has been already added !");
				} else { window.location.reload(); }
			},
			error: function(e) {
				alert('error occured while posting data' + e);
			}
		});
	}
});

$(function() {
	calculate();
	$(".button").on("click", function() {
		var $button = $(this);
		var oldQty = $button.parent().parent().find("input").val();
		var quantity = {};
		var $row = $(this).closest("tr");
		quantity.itemId = $row.find(".item-id").text();
		if ($button.html() == '<i class="fa fa-plus bg-success" style="font-size: 0.6em; color: #fff; padding: 5px;"></i>') {
			var newQty = parseFloat(oldQty) + 1;
			quantity.quantity = newQty;
			var json = JSON.stringify(quantity);
			$.ajax({
				url: '/post/bundle/item/quantity',
				method: 'POST',
				data: json,
				contentType: "application/json; charset=utf-8",
				success: function() {
				},
				error: function(e) {
					alert('error occured while posting data' + e);
				}
			});

		} else {
			if (oldQty > 1) {
				var newQty = parseFloat(oldQty) - 1;
				quantity.quantity = newQty;
				var json = JSON.stringify(quantity);
				$.ajax({
					url: '/post/bundle/item/quantity',
					method: 'POST',
					data: json,
					contentType: "application/json; charset=utf-8",
					success: function() {
					},
					error: function(e) {
						alert('error occured while posting data' + e);
					}
				});
			} else {
				newQty = 1;
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
		if (confirm("Do you really want to remove this item?")) {

			var $row = $(this).closest("tr");
			var itemId = $row.find(".item-id").text();

			$(this).parent().parent().remove();
			calculate();
			$.ajax({
				url: '/post/bundle/item/id',
				method: 'POST',
				data: { "itemId": itemId },
				contentType: "application/json; charset=utf-8",
				success: function() {
				},
				error: function(e) {
					alert('error occured while deleting data' + e);
				}
			});
		}
	});
});

$("#clear-all").click(function() {
	if (confirm("Do you really want to remove all items?")) {
		location.href = "/master/bundle/clear";
	}
});
