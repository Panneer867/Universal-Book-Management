$(document).ready(function() {

	$('.table .edit-branch').on('click', function(event) {
		event.preventDefault();
		
		var href = $(this).attr('href');
		
		$.get(href, function(branch){
			
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

});