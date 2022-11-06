$(document).ready(function() {

	$('#clickEditCompany').on('click', function(event) {

		document.getElementById('companyNameCopy').value = document.getElementById('companyName').value;
		document.getElementById('emailCopy').value = document.getElementById('email').value;
		document.getElementById('telephoneCopy').value = document.getElementById('telephone').value;
		document.getElementById('gstinCopy').value = document.getElementById('gstin').value;
		document.getElementById('faxCopy').value = document.getElementById('fax').value;
		document.getElementById('websiteCopy').value = document.getElementById('website').value;
		document.getElementById('serviceTypeCopy').value = document.getElementById('serviceType').value;
		document.getElementById('noOfBranchCopy').value = document.getElementById('noOfBranch').value;
		document.getElementById('companyAddressCopy').value = document.getElementById('companyAddress').value;
		document.getElementById('companyStateCopy').value = document.getElementById('companyState').value;
		document.getElementById('companyCityCopy').value = document.getElementById('companyCity').value;
		document.getElementById('companyPinCodeCopy').value = document.getElementById('companyPinCode').value;

	});

	$('#clickEditOwner').on('click', function(event) {

		document.getElementById('firstNameCopy').value = document.getElementById('firstName').value;
		document.getElementById('lastNameCopy').value = document.getElementById('lastName').value;
		document.getElementById('drivingLicenseCopy').value = document.getElementById('drivingLicense').value;
		document.getElementById('ownerEmailCopy').value = document.getElementById('ownerEmail').value;
		document.getElementById('mobileNoCopy').value = document.getElementById('mobileNo').value;
		document.getElementById('alternateMobileCopy').value = document.getElementById('alternateMobile').value;
		document.getElementById('ownerAddressCopy').value = document.getElementById('ownerAddress').value;
		document.getElementById('ownerStateCopy').value = document.getElementById('ownerState').value;
		document.getElementById('ownerCityCopy').value = document.getElementById('ownerCity').value;
		document.getElementById('ownerPinCodeCopy').value = document.getElementById('ownerPinCode').value;

	});


	$('#clickEditBank').on('click', function(event) {

		document.getElementById('bankNameCopy').value = document.getElementById('bankName').value;
		document.getElementById('beneficiaryNameCopy').value = document.getElementById('beneficiaryName').value;
		document.getElementById('accountTypeCopy').value = document.getElementById('accountType').value;
		document.getElementById('accountNumberCopy').value = document.getElementById('accountNumber').value;
		document.getElementById('ifscCodeCopy').value = document.getElementById('ifscCode').value;
		document.getElementById('bankAddressCopy').value = document.getElementById('bankAddress').value;
		document.getElementById('bankStateCopy').value = document.getElementById('bankState').value;
		document.getElementById('bankCityCopy').value = document.getElementById('bankCity').value;
		document.getElementById('bankPinCodeCopy').value = document.getElementById('bankPinCode').value;
	});

});