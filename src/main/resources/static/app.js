(function() {
  const context = document.getElementById('chart');
  const chart = new Chart(context, {
    type: 'line',
    data: {
      labels: ['Tick'],
      datasets: [{
        data: [],
        label: 'Failed requests percentage',
        backgroundColor: '#6897BB',
        borderColor: '#6897BB',
        fill: true,
        lineTension: 0
      }]
    },
    options: {
      responsive: false,
      elements: {
        point: {
          radius: 0
        }
      },
      tooltips: {
        intersect: false
      },
      scales: {
        xAxes: [{
          display: false
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: 100
          },
          scaleLabel: {
            display: true,
            labelString: '%'
          }
        }]
      },
      legend: {
        display: false
      }
    }
  });
  const addPoint = (data) => {
    const failed = data.failed;
    const total = data.total;
    const failRatio = (failed / total * 100).toFixed(2);

    chart.data.labels.push(data.timestamp);
    chart.data.datasets[0].data.push(failRatio);
    chart.update();
  };

  // TODO: Add an EventSource

})();