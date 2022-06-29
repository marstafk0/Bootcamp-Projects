'use strict';

/* Chart.js docs: https://www.chartjs.org/ */

window.chartColors = {
	blue: '#6c99fa',
	gray: '#a9b5c9',
	text: '#252930',
	border: '#e7e9ed'
};

/* Random number generator for demo purpose */
var randomDataPoint = function(){ return Math.round(Math.random()*10000)};

var weeksArray = [];
var moneyArray = [];
var oldMoneyArray = [];

var grades = [];
var gradeMoney = [];
$(document).ready(function () {
	$.get("chartData", function (data, status) {
		var weeks = data[0];
		var money = data[1];
		var oldMoney = data[2];
		weeks.forEach(function (item) {
			weeksArray.push("Week " + item)
		});
		money.forEach(function (item) {
			moneyArray.push(item)
		});
		oldMoney.forEach(function (item) {
			oldMoneyArray.push(item)
		});
	});
	$.get("chartGradeData", function (data, status) {  
		var grade = data[0];
		var moneyGrade = data[1];
		grade.forEach(function (item) {
			grades.push(item)
		});
		moneyGrade.forEach(function (item) {
			gradeMoney.push(item)
		});
	})
});
//Chart.js Line Chart 
var lineChartConfig = {
	type: 'line',

	data: {
		labels: weeksArray,
		
		datasets: [{
			label: 'Current Jog-A-Thon',
			fill: false,
			backgroundColor: window.chartColors.blue,
			borderColor: window.chartColors.blue,
			data: moneyArray,
		}, {
			label: 'Previous Jog-A-Thon',
		    borderDash: [3, 5],
			backgroundColor: window.chartColors.gray,
			borderColor: window.chartColors.gray,
			
			data: oldMoneyArray,
			fill: false,
		}]
	},
	options: {
		responsive: true,	
		aspectRatio: 1.5,
		
		legend: {
			display: true,
			position: 'bottom',
			align: 'end',
		},
		
		title: {
			display: true,
			text: 'Jog-A-Thon Line Chart',
			
		}, 
		tooltips: {
			mode: 'index',
			intersect: false,
			titleMarginBottom: 10,
			bodySpacing: 10,
			xPadding: 16,
			yPadding: 16,
			borderColor: window.chartColors.border,
			borderWidth: 1,
			backgroundColor: '#fff',
			bodyFontColor: window.chartColors.text,
			titleFontColor: window.chartColors.text,

            callbacks: {
	            //Ref: https://stackoverflow.com/questions/38800226/chart-js-add-commas-to-tooltip-and-y-axis
                label: function(tooltipItem, data) {
	                if (parseInt(tooltipItem.value) >= 1000) {
						var res = "$" + tooltipItem.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						console.log(res);
                        return "$" + tooltipItem.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                    } else {
						var res = "$" + tooltipItem.value;
						console.log(res);
	                    return '$' + tooltipItem.value;
                    }
                }
            },

		},
		hover: {
			mode: 'nearest',
			intersect: true
		},
		scales: {
			x: {
				display: true,
				gridLines: {
					drawBorder: false,
					color: window.chartColors.border,
				},
				scaleLabel: {
					display: false,
				
				}
			},
			y: {
				display: true,
				gridLines: {
					drawBorder: false,
					color: window.chartColors.border,
				},
				scaleLabel: {
					display: false,
				},
				ticks: {
		            beginAtZero: true,
		            userCallback: function(value, index, values) {
		                return '$' + value.toLocaleString();   //Ref: https://stackoverflow.com/questions/38800226/chart-js-add-commas-to-tooltip-and-y-axis
		            }
		        },
			}
		}
	}
};



// Chart.js Bar Chart

var barChartConfig = {
	type: 'bar',

	data: {
		labels: grades,
		datasets: [{
			label: 'Donations',
			backgroundColor: window.chartColors.blue,
			borderColor: window.chartColors.blue,
			borderWidth: 1,
			maxBarThickness: 16,
			
			data: gradeMoney
		}]
	},
	options: {
		responsive: true,
		aspectRatio: 1.5,
		legend: {
			position: 'bottom',
			align: 'end',
		},
		title: {
			display: true,
			text: 'Chart.js Bar Chart By Grade'
		},
		tooltips: {
			mode: 'index',
			intersect: false,
			titleMarginBottom: 10,
			bodySpacing: 10,
			xPadding: 16,
			yPadding: 16,
			borderColor: window.chartColors.border,
			borderWidth: 1,
			backgroundColor: '#fff',
			bodyFontColor: window.chartColors.text,
			titleFontColor: window.chartColors.text,

		},
		scales: {
			x: {
				display: true,
				gridLines: {
					drawBorder: false,
					color: window.chartColors.border,
				},

			},
			y: {
				display: true,
				gridLines: {
					drawBorder: false,
					color: window.chartColors.borders,
				},

				
			}
		}
		
	}
}


// Generate charts on load
window.addEventListener('load', function(){
	
	var lineChart = document.getElementById('canvas-linechart').getContext('2d');
	window.myLine = new Chart(lineChart, lineChartConfig);
	
	var barChart = document.getElementById('canvas-barchart').getContext('2d');
	window.myBar = new Chart(barChart, barChartConfig);
	

});	