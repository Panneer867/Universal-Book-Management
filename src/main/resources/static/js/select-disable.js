$('.js-custom select').on('change', function() {

	var $node = $(this).parents('.js-custom');
	var $select = $node.find('select');
	var $field = $node.find('input[type="text"]');

	if ($select.length !== 1 || $field.length !== 1) {
		throw new Error('Error');
	}

	var selectedVal = $select.val().toLowerCase();
	var fieldShouldBeDisabled = selectedVal !== 'other';

	$field.prop('disabled', fieldShouldBeDisabled);
});