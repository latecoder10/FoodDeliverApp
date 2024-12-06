let ordersReceivedChart, ordersDeliveredChart, ordersCancelledChart, totalOrderAmountChart;

document.addEventListener("DOMContentLoaded", function() {
    // Get dynamic data from HTML elements
    const ordersReceived = parseInt(document.getElementById('ordersReceived').value);
    const ordersDelivered = parseInt(document.getElementById('ordersDelivered').value);
    const ordersCancelled = parseInt(document.getElementById('ordersCancelled').value);
    const ordersPending = parseInt(document.getElementById('ordersPending').value);
    const ordersInProgress = parseInt(document.getElementById('ordersInProgress').value);
    const totalOrderAmount = parseFloat(document.getElementById('totalOrderAmount').value);

    // Initialize chart contexts
    const ordersReceivedCtx = document.getElementById('ordersReceivedChart').getContext('2d');
    const ordersDeliveredCtx = document.getElementById('ordersDeliveredChart').getContext('2d');
    const ordersCancelledCtx = document.getElementById('ordersCancelledChart').getContext('2d');
    const totalOrderAmountCtx = document.getElementById('totalOrderAmountChart').getContext('2d');

    // Create doughnut charts
    ordersReceivedChart = new Chart(ordersReceivedCtx, {
        type: 'doughnut',
        data: {
            labels: ['Received', 'Pending'],
            datasets: [{
                data: [ordersReceived, ordersPending],
                backgroundColor: ['#36A2EB', '#FF6384'],
            }]
        },
        options: { responsive: true, maintainAspectRatio: true }
    });

    ordersDeliveredChart = new Chart(ordersDeliveredCtx, {
        type: 'doughnut',
        data: {
            labels: ['Delivered', 'In Progress'],
            datasets: [{
                data: [ordersDelivered, ordersInProgress],
                backgroundColor: ['#4BC0C0', '#FF6384'],
            }]
        },
        options: { responsive: true, maintainAspectRatio: true }
    });

    ordersCancelledChart = new Chart(ordersCancelledCtx, {
        type: 'doughnut',
        data: {
            labels: ['Cancelled', 'Accepted'],
            datasets: [{
                data: [ordersCancelled, ordersReceived - ordersCancelled],
                backgroundColor: ['#FF6384', '#36A2EB'],
            }]
        },
        options: { responsive: true, maintainAspectRatio: true }
    });

    totalOrderAmountChart = new Chart(totalOrderAmountCtx, {
        type: 'doughnut',
        data: {
            labels: ['Total Amount'],
            datasets: [{
                data: [totalOrderAmount],
                backgroundColor: ['#FFCE56'],
            }]
        },
        options: { responsive: true, maintainAspectRatio: true }
    });

    // Duration button event listeners
    document.querySelectorAll('.duration-btn').forEach(button => {
        button.addEventListener('click', function() {
            const duration = this.dataset.duration;
            fetch(`chartDataServlet?duration=${duration}`) // Ensure correct path
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Data received for charts:', data); // Debugging line
                    // Update your charts with the received data
                    updateCharts(data);
                })
                .catch(error => console.error('Error fetching chart data:', error));
        });
    });
});

function updateCharts(data) {
    // Update your chart data here using the received data
    ordersReceivedChart.data.datasets[0].data = [data.ordersInProgress + data.ordersPending+data.ordersDelivered, data.ordersPending];
    ordersDeliveredChart.data.datasets[0].data = [data.ordersDelivered, data.ordersInProgress];
    ordersCancelledChart.data.datasets[0].data = [data.ordersCancelled, data.ordersInProgress + data.ordersPending +data.ordersDelivered - data.ordersCancelled];
    totalOrderAmountChart.data.datasets[0].data = [data.totalOrderAmount];

    ordersReceivedChart.update();
    ordersDeliveredChart.update();
    ordersCancelledChart.update();
    totalOrderAmountChart.update();
}


// Functionality for accept and cancel buttons
document.querySelectorAll('.accept-btn').forEach(function(button) {
	button.onclick = function() {
		var row = button.closest('tr');
		var orderId = button.getAttribute('data-order-id');
		var statusCell = row.querySelector('.status');

		// Debugging: Log the row and the statusCell
		console.log(row);
		console.log(statusCell);

		if (statusCell) { // Check if statusCell exists
			// AJAX call to update the order status
			fetch('/FoodDeliverApp/updateOrderStatus', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: new URLSearchParams({
					orderId: orderId,
					status: 'In Progress'
				})
			})
				.then(response => response.json())
				.then(data => {
					statusCell.textContent = 'In Progress';
					statusCell.style.color = 'green';
					disableButtons(row);
					console.log(data.message); // Show success message
				})
				.catch(error => console.error('Error:', error));
		} else {
			console.error('Status cell not found for order ID:', orderId);
		}
	};
});

document.querySelectorAll('.cancel-btn').forEach(function(button) {
	button.onclick = function() {
		var row = button.closest('tr');
		var orderId = button.getAttribute('data-order-id');
		var statusCell = row.querySelector('.status');

		// Debugging: Log the row and the statusCell
		console.log(row);
		console.log(statusCell);

		if (statusCell) { // Check if statusCell exists
			// AJAX call to update the order status
			fetch('/FoodDeliverApp/updateOrderStatus', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: new URLSearchParams({
					orderId: orderId,
					status: 'Cancelled'
				})
			})
				.then(response => response.json())
				.then(data => {
					statusCell.textContent = 'Cancelled';
					statusCell.style.color = 'red';
					disableButtons(row);
					console.log(data.message); // Show success message
				})
				.catch(error => console.error('Error:', error));
		} else {
			console.error('Status cell not found for order ID:', orderId);
		}
	};
});

function disableButtons(row) {
	row.querySelectorAll('button').forEach(button => {
		button.disabled = true; // Disable buttons to prevent multiple clicks
	});
}


document.getElementById("availabilityBtn").addEventListener("click", function() {
	var currentStatus = this.textContent === "Available";
	var newStatus = !currentStatus;

	fetch("updateAvailabilityServlet", {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: "available=" + newStatus
	})
		.then(response => {
			if (response.ok) {
				this.textContent = newStatus ? "Available" : "Unavailable";
				this.className = newStatus ? "btn btn-outline-success availability-btn" : "btn btn-outline-danger availability-btn";

				alert("Restaurant Status Updated");
			} else {
				alert("Failed to update availability");
			}
		})
		.catch(error => {
			console.error("Error:", error);
		});
});


