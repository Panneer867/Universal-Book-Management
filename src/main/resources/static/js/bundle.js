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
				console.log(jsonobject);
				if (jsonobject.itemExists === "YES") {
					window.location.reload();
				} else { alert("This Item has been already added !"); }
			},
			error: function(e) {
				console.log('error occured while posting data');
			}
		});
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
		if (confirm("Do you really want to remove this item?")) {
			var bookData = {};
			var $row = $(this).closest("tr");
			bookData.itemName = $row.find(".item_name").text();
			var bookName = JSON.stringify(bookData);
			$(this).parent().parent().remove();
			calculate();
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
		}
	});
});

$("#clear-all").click(function() {
	if (confirm("Do you really want to remove all items?")) {
		location.href = "/master/bundle/clear";
	}
});
