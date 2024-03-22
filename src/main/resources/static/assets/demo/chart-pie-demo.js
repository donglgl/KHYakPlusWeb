// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';


var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["10대", "20대", "30대", "40대", "50대 이상"],
    datasets: [{
      data: [/* 데이터 실행 */],
      backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', '#6c757d'],
    }],
  },
});

// 서버에서 연령대별 통계 데이터를 가져와서 차트에 적용합니다.
document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/agechart')
    .then(response => response.json())
    .then(data => {
        // 서버에서 받아온 데이터를 차트에 적용합니다.
        myPieChart.data.datasets[0].data = Object.values(data);
        myPieChart.update();
    })
    .catch(error => {
        console.error('연령대별 통계 데이터를 가져오는 중 오류 발생:', error);
    });
});