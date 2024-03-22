// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';


document.addEventListener('DOMContentLoaded', function() {
	
	  // 차트를 생성하는 코드
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ["test 1", "test 2", "test 3", "test 4", "test 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
        datasets: [{
          label: "판매량",
          lineTension: 0.3,
          backgroundColor: "rgba(2,117,216,0.2)",
          borderColor: "rgba(2,117,216,1)",
          pointRadius: 5,
          pointBackgroundColor: "rgba(2,117,216,1)",
          pointBorderColor: "rgba(255,255,255,0.8)",
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(2,117,216,1)",
          pointHitRadius: 50,
          pointBorderWidth: 2,
          data: [3, 2, 1, 5, 4, 6, 7, 8, 9, 7, 6, 4, 0],
        }],
      },
      options: {
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            ticks: {
              min: 0,
              max: 40000,
              maxTicksLimit: 5
            },
            gridLines: {
              color: "rgba(0, 0, 0, .125)",
            }
          }],
        },
        legend: {
          display: false
        }
      }
    });
    
	fetch('/api/items')
	.then(response => response.json())
	.then(data => {
	  // 아이템 리스트에서 데이터를 추출하여 차트에 적합한 형식으로 가공합니다.
	  const labels = data.map(item => item.itemName);
	  const difference = data.map(item => item.difference);
	  
	  // 차트 데이터를 설정합니다.
	  myLineChart.data.labels = labels;
	  myLineChart.data.datasets[0].data = difference;
	  
	  // 차트를 업데이트합니다.
	  myLineChart.update();
	})
	.catch(error => {
	  console.error('Error fetching items:', error);
	});

	});
