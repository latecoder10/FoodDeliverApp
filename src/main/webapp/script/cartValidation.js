/**
 * Get login status from the body attribute
 */
const isLoggedIn = document.body.getAttribute('data-logged-in') === 'true';

/**
 * Update the quantity of an item
 * @param {string} itemId - The ID of the item
 * @param {number} delta - The amount to change the quantity by
 */
function changeQuantity(itemId, delta) {
    console.log(`Changing quantity for itemId: "${itemId}" by delta: ${delta}`);
    
    if (!itemId) {
        console.error('Item ID is missing');
        return;
    }

    const quantityInput = document.getElementById(`${itemId}-quantity`);
    
    if (!quantityInput) {
        console.error(`Element with ID "${itemId}-quantity" not found`);
        return;
    }

    let currentQuantity = parseInt(quantityInput.value, 10);
    
    if (isNaN(currentQuantity)) {
        currentQuantity = 0; // Handle cases where value is not a number
    }

    currentQuantity += delta;
    
    if (currentQuantity < 0) {
        currentQuantity = 0; // Prevent negative quantity
    }

    quantityInput.value = currentQuantity;
    
    // Update the hidden input field in the form to match the displayed quantity
    const quantityInputForm = document.getElementById(`${itemId}-quantity-form`);
    if (quantityInputForm) {
        quantityInputForm.value = currentQuantity;
    }
}



function checkLoginAndAddToCart(itemId) {
	if (isLoggedIn) {
		addToCart(itemId);
	} else {
		// Show login modal
		alert(`You need to login to continue`);
		const loginModal = new bootstrap.Modal(document
			.getElementById('loginModal'));
		loginModal.show();
		
	}
}

/**
 * Add an item to the cart
 * @param {string} itemId - The ID of the item
 */
function addToCart(itemId) {
	const quantityElement = document.getElementById(`${itemId}-quantity`);
    const quantity = parseInt(quantityElement.value, 10);
    
    if (isNaN(quantity) || quantity <= 0) {
        alert('Please select a valid quantity.');
        return;
    }

    const form = document.getElementById(`cart-form-${itemId}`);
    if (!form) {
        console.error(`Form with ID "cart-form-${itemId}" not found`);
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'addToCart', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            // Request was successful
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
                // Update UI or notify the user of success
                alert('Item added to cart successfully!');
                // Optionally, update cart display or other UI elements
                quantityElement.value = '0';
                updateCartCount();
            } else {
                // Handle server-side errors or issues
                alert('Failed to add item to cart: ' + response.error);
            }
        } else {
            // Server responded with an error status code
            alert('An error occurred while adding the item to the cart. Please try again.');
        }
    };

    xhr.onerror = function() {
        alert('An error occurred while processing the request. Please try again.');
    };

    xhr.send(new URLSearchParams(new FormData(form)).toString());
}

// cartValidation.js
function updateCartCount() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'getCartCount', true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const cartCount = JSON.parse(xhr.responseText).count;
            const cartCountElement = document.getElementById('cart-count');
            cartCountElement.textContent = cartCount;
            cartCountElement.style.display = 'inline'; // Show cart count if hidden
        }
    };
    xhr.send();
}

document.addEventListener('DOMContentLoaded', function() {
    updateCartCount(); // Update cart count when the page loads
});




