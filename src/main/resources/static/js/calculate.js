$(document).ready(function() {

	sum();
	$("#totalPurchasedAmount, #paidAmount").on("keydown keyup", function() {
		sum();
	});
});

function sum() {
	var totalPurchasedAmount = document.getElementById('totalPurchasedAmount').value;
	var paidAmount = document.getElementById('paidAmount').value;
	var result = parseInt(totalPurchasedAmount) - parseInt(paidAmount);
	if (!isNaN(result)) {
		document.getElementById('balanceAmount').value = result;
		document.getElementById('balanceAmountCopy').value = result;
	}
}

$(document).ready(function() {

	sub();
	$("#stotalPurchasedAmountCopy, #spaidAmountCopy").on("keydown keyup", function() {
		sub();
	});
});

function sub() {
	var totalPurchasedAmount = document.getElementById('stotalPurchasedAmountCopy').value;
	var paidAmount = document.getElementById('spaidAmountCopy').value;
	var result = parseInt(totalPurchasedAmount) - parseInt(paidAmount);
	if (!isNaN(result)) {
		document.getElementById('sbalanceAmountCopy').value = result;
		document.getElementById('csbalanceAmountCopy').value = result;
	}
}