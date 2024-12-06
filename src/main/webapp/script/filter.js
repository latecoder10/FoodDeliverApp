/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
	// Select all filter buttons and product items
	const filterButtons = document.querySelectorAll('.filter-button-group button');
	const productItems = document.querySelectorAll('#product-list .product-item');

	// Function to show/hide items based on filter
	function filterItems(filter) {
		productItems.forEach(item => {
			// Show all items or items that match the filter
			if (filter === 'all' || item.classList.contains(filter)) {
				item.classList.remove('hidden');
			} else {
				item.classList.add('hidden');
			}
		});
	}

	// Set up event listeners for filter buttons
	filterButtons.forEach(button => {
		button.addEventListener('click', () => {
			const filter = button.getAttribute('data-filter');
			filterItems(filter);
		});
	});
});
