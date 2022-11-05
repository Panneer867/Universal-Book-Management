function deleteCompany(cid) {
	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this  profile details!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {

				window.location = "/company/deleteCompany/" + cid;

			} else {
				swal("Your profile details  is safe!");
			}
		});
}