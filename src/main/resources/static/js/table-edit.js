$(document).ready(function() {

	$('.table .edit-branch').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(branch) {

			$('#branchIdShow').val(branch.branchId);
			$('#branchIdCopy').val(branch.branchId);
			$('#branchNameCopy').val(branch.branchName);
			$('#firstNameCopy').val(branch.firstName);
			$('#usernameCopy').val(branch.username);
			$('#emailCopy').val(branch.email);
			$('#mobileCopy').val(branch.mobile);
			$('#addressCopy').val(branch.address);
			$('#stateCopy').val(branch.state);
			$('#cityCopy').val(branch.city);
			$('#pinCodeCopy').val(branch.pinCode);

		});
		$('editBranch').modal();

	});


	$('.table .edit-userDetails').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(user) {

			$('#userIdCopy').val(user.userId);
			$('#branchCopy').val(user.branch);
			$('#roleCopy').val(user.userType);
			$('#firstNameCopy').val(user.firstName);
			$('#emailCopy').val(user.email);
			$('#mobileCopy').val(user.mobile);
			$('#usernameCopy').val(user.username);

		});
		$('edit-userDetails').modal();

	});


	$('.table .edit-employee').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(employee) {

			$('#employeeIdShow').val(employee.employeeId);
			$('#employeeIdCopy').val(employee.employeeId);
			$('#efirstNameCopy').val(employee.firstName);
			$('#elastNameCopy').val(employee.lastName);
			$('#emobileCopy').val(employee.mobile);
			$('#ealternateMobileCopy').val(employee.alternateMobile);
			$('#eemailCopy').val(employee.email);
			$('#eaddressCopy').val(employee.address);
			$('#estateCopy').val(employee.state);
			$('#ecityCopy').val(employee.city);
			$('#epinCodeCopy').val(employee.pinCode);
			$('#eaadhaarCopy').val(employee.aadhaar);
			$('#emaritalStatusCopy').val(employee.maritalStatus);
			$('#edrivingLicenseCopy').val(employee.drivingLicense);

		});
		$('.edit-employee').modal();

	});

});

$(document).ready(function() {
	$('.table .edit-unit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(unit) {
			$('#unitIdCopy').val(unit.unitId);
			$('#unitOfMeasureCopy').val(unit.unitOfMeasure);
			$('#remarksCopy').val(unit.remarks);
		});
		$('editUnit').modal();
	});
});

$(document).ready(function() {
	$('.table .edit-brand').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(brand) {
			$('#brandIdCopy').val(brand.brandId);
			$('#brandNameCopy').val(brand.brandName);
		});
		$('editBrand').modal();
	});
});

$(document).ready(function() {
	$('.table .edit-category').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(category) {
			$('#categoryIdCopy').val(category.categoryId);
			$('#categoryNameCopy').val(category.categoryName);
			$('#categoryStatusCopy').val(category.categoryStatus);
		});
		$('editCategory').modal();
	});

	$(document).ready(function() {
		$('.table .edit-hsn').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(hsn) {
				$('#hsnIdCopy').val(hsn.hsnId);
				$('#categoryNameCopy').val(hsn.categoryName);
				$('#hsnCodeCopy').val(hsn.hsnCode);
			});
			$('editHsn').modal();
		});
	});

	$(document).ready(function() {
		$('.table .edit-supplier').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(supplier) {
				$('#supplierIdCopy').val(supplier.supplierId);
				$('#ssupplierNameCopy').val(supplier.supplierName);
				$('#sreciptNoCopy').val(supplier.reciptNo);
				$('#sgstTypeCopy').val(supplier.gstType);
				$('#sgstinCopy').val(supplier.gstin);
				$('#semailCopy').val(supplier.email);
				$('#stelephoneCopy').val(supplier.telephone);
				$('#smobileCopy').val(supplier.mobile);
				$('#sfaxNoCopy').val(supplier.faxNo);
				$('#saddressCopy').val(supplier.address);
				$('#sstateCopy').val(supplier.state);
				$('#scityCopy').val(supplier.city);
				$('#spinCodeCopy').val(supplier.pinCode);
				$('#sbankNameCopy').val(supplier.bankName);
				$('#scaccountNumberCopy').val(supplier.accountNumber);
				$('#saccountNumberCopy').val(supplier.accountNumber);
				$('#sifscCodeCopy').val(supplier.ifscCode);
				$('#stotalPurchasedAmountCopy').val(supplier.totalPurchasedAmount);
				$('#spaidAmountCopy').val(supplier.paidAmount);
				$('#sbalanceAmountCopy').val(supplier.balanceAmount);
				$('#csbalanceAmountCopy').val(supplier.balanceAmount);
			});
			$('editSupplier').modal();

		});
	});

	$(document).ready(function() {
		$('.table .view-supplier').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(supplier) {
				$('#vsupplierIdCopy').val(supplier.supplierId);
				$('#vssupplierNameCopy').val(supplier.supplierName);
				$('#vsreciptNoCopy').val(supplier.reciptNo);
				$('#vsgstTypeCopy').val(supplier.gstType);
				$('#vsgstinCopy').val(supplier.gstin);
				$('#vsemailCopy').val(supplier.email);
				$('#vstelephoneCopy').val(supplier.telephone);
				$('#vsmobileCopy').val(supplier.mobile);
				$('#vsfaxNoCopy').val(supplier.faxNo);
				$('#vsaddressCopy').val(supplier.address);
				$('#vsstateCopy').val(supplier.state);
				$('#vscityCopy').val(supplier.city);
				$('#vspinCodeCopy').val(supplier.pinCode);
				$('#vsbankNameCopy').val(supplier.bankName);
				$('#vsaccountNumberCopy').val(supplier.accountNumber);
				$('#vsifscCodeCopy').val(supplier.ifscCode);
				$('#vstotalPurchasedAmountCopy').val(supplier.totalPurchasedAmount);
				$('#vspaidAmountCopy').val(supplier.paidAmount);
				$('#vsbalanceAmountCopy').val(supplier.balanceAmount);
				$('#vcsbalanceAmountCopy').val(supplier.balanceAmount);
			});
			$('viewSupplier').modal();

		});
	});

	$(document).ready(function() {
		$('.table .edit-publisher').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(publisher) {
				$('#publisherIdCopy').val(publisher.publisherId);
				$('#brandCopy').val(publisher.brand);
				$('#categoryCopy').val(publisher.category);
				$('#supplierCopy').val(publisher.supplier);
				$('#publisherNameCopy').val(publisher.publisherName);
				$('#remarksCopy').val(publisher.remarks);
			});
			$('editPublisher').modal();
		});

	});

	$(document).ready(function() {
		$('.table .view-item').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(publisher) {
				$('#itemIdCopy').val(publisher.itemId);
				$('#iItemNameCopy').val(publisher.itemName);
				$('#iCategoryCopy').val(publisher.categoryName);
				$('#iItemImageCopy').val(publisher.itemImage);
				$('#iBrand').val(publisher.brandName);
				$('#iSupplier').val(publisher.supplierName);
				$('#iUnitOfMeasure').val(publisher.unitOfMeasure);
				$('#iStatus').val(publisher.itemStatus);
				$('#iHsnCode').val(publisher.hsnCode);
				$('#iRemarks').val(publisher.remarks);
				$("#itemImg").attr("src", "/Company/" + publisher.companyName + "/Items/" + publisher.itemImage);
			});
			$('viewItem').modal();

		});

	});


});