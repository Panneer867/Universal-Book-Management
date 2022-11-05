google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          is3D: true,
         height:400, 
         width:690,
          chartArea: {
            left: "25%",
            top: "3%",
            height: "100%",
            width: "100%"
        }
              
  		 
        };

        var chart = new google.visualization.PieChart(document.getElementById('purchase_chart'));
        chart.draw(data, options);
      }