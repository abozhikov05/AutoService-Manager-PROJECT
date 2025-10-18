document.addEventListener("DOMContentLoaded", function() {
    const stats = window.chartData || [];
    const labels = [];
    const counts = [];

    for (let i = 0; i < stats.length; i++) {
        labels.push(stats[i][0]);
        counts.push(stats[i][1]);
    }

    new Chart(document.getElementById('carChart'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Number of visits',
                data: counts,
                borderColor: 'royalblue',
                backgroundColor: 'rgba(65, 105, 225, 0.2)',
                tension: 0.4,
                fill: true,
                pointBackgroundColor: 'royalblue',
                pointBorderColor: 'white',
                pointRadius: 5,
                pointHoverRadius: 7
            }]
        },
        options: {
            responsive: true,
            animation: {
                duration: 1800,
                easing: 'easeInOutQuart',
                delay: context => context.dataIndex * 120
            },
            plugins: {
                legend: {
                    labels: {
                        color: 'royalblue',
                        font: { size: 14, weight: 'bold' }
                    }
                }
            },
            scales: {
                x: {
                    title: { display: true, text: 'Brand', color: '#1e3a8a', font: { size: 14 } },
                    ticks: { color: '#royalblue' }
                },
                y: {
                    beginAtZero: true,
                    title: { display: true, text: 'Number of visits', color: '#1e3a8a', font: { size: 14 } },
                    ticks: { color: '#royalblue' }
                }
            }
        }
    });
});
