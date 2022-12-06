$(document).ready(function() {

	$("#cstates").change(function() {
		var stateName = $(this).val().trim();
		var s = '<option value="">Select</option>';
		if (stateName != null) {
			$.ajax({
				type: 'GET',
				url: '/get/city',
				data: { "stateName": stateName },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][1] + '">' + result[i][1] + '</option>';

					}
					$('#ccities').html(s);
				}
			});
		}

		//reset data
		$('#ccities').html(s);
	});

	$("#ostates").change(function() {
		var stateName = $(this).val().trim();

		var s = '<option value="">Select</option>';
		if (stateName != null) {
			$.ajax({
				type: 'GET',
				url: '/get/city',
				data: { "stateName": stateName },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][1] + '">' + result[i][1] + '</option>';

					}
					$('#ocities').html(s);
				}
			});
		}

		//reset data
		$('#ocities').html(s);
	});

	$("#bstates").change(function() {
		var stateName = $(this).val().trim();

		var s = '<option value="">Select</option>';
		if (stateName != null) {
			$.ajax({
				type: 'GET',
				url: '/get/city',
				data: { "stateName": stateName },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][1] + '">' + result[i][1] + '</option>';

					}
					$('#bcities').html(s);
				}
			});
		}

		//reset data
		$('#bcities').html(s);
	});


	$("#estates").change(function() {
		var stateName = $(this).val().trim();

		var s = '<option value="">Select</option>';
		if (stateName != null) {
			$.ajax({
				type: 'GET',
				url: '/get/city',
				data: { "stateName": stateName },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][1] + '">' + result[i][1] + '</option>';

					}
					$('#ecities').html(s);
				}
			});
		}

		//reset data
		$('#ecities').html(s);
	});
	
	$(document).ready(function() {
			    $('.js-example-basic-single').select2();
			});

});
