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