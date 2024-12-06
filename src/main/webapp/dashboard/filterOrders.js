
            function filterOrders() {
                const orderStatus = document.getElementById('orderStatus').value;
                const paymentMethod = document.getElementById('paymentMethod').value;

                const rows = document.querySelectorAll('#ordersTable tbody tr');

                rows.forEach(row => {
                    const status = row.getAttribute('data-order-status');
                    const payment = row.getAttribute('data-payment-method');

                    const matchesStatus = !orderStatus || status === orderStatus;
                    const matchesPayment = !paymentMethod || payment === paymentMethod;

                    if (matchesStatus && matchesPayment) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }

            function sortOrders() {
                const sortOption = document.getElementById('sortSelect').value;
                const table = document.getElementById('ordersTable');
                const rows = Array.from(table.querySelectorAll('tbody tr'));

                rows.sort((a, b) => {
                    const aValue = getSortValue(a, sortOption);
                    const bValue = getSortValue(b, sortOption);

                    if (aValue < bValue) return -1;
                    if (aValue > bValue) return 1;
                    return 0;
                });

                if (sortOption.includes('Desc')) rows.reverse();

                const tbody = table.querySelector('tbody');
                tbody.innerHTML = ''; // Clear existing rows
                rows.forEach(row => tbody.appendChild(row)); // Append sorted rows
            }

            function getSortValue(row, sortOption) {
                switch (sortOption) {
                    case 'orderIdAsc':
                    case 'orderIdDesc':
                        return parseInt(row.getAttribute('data-order-id'));
                    case 'userIdAsc':
                    case 'userIdDesc':
                        return parseInt(row.getAttribute('data-user-id'));
                    case 'orderDateAsc':
                    case 'orderDateDesc':
                        return new Date(row.getAttribute('data-order-date')).getTime();
                    case 'totalAmountAsc':
                    case 'totalAmountDesc':
                        return parseFloat(row.getAttribute('data-total-amount'));
                    default:
                        return '';
                }
            }
