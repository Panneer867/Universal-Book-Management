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