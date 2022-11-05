google.charts.load('current', {
	packages: ['bar']
}).then(drawChart);

function drawChart() {
	var data = google.visualization.arrayToDataTable([
		['Year', 'Sales', 'Expenses', 'Profit'],
		['2014', 1000, 400, 200],
		['2015', 1170, 460, 250],
		['2016', 660, 1120, 300],
		['2017', 1030, 540, 350]
	]);

	var options = {
		height: 360,
		width: 660,
		bars: 'vertical',
		vAxis: { format: 'decimal' },
		colors: ['#1b9e77', '#d95f02', '#7570b3']
	};

	var chart = new google.charts.Bar(document.getElementById('column_chart'));
	chart.draw(data, google.charts.Bar.convertOptions(options));
	window.addEventListener('resize', drawChart, false);
}