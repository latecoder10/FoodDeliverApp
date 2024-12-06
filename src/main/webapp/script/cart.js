document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('updateCartButton').addEventListener('click', function() {
        updateCart();
        updateCartCount();
    });

   
});

function updateCart() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "updateCart", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var formData = new FormData(document.getElementById('cart-form'));
    var params = [];
    formData.forEach(function(value, key) {
        params.push(encodeURIComponent(key) + "=" + encodeURIComponent(value));
    });
    var paramString = params.join("&");

    xhr.onload = function() {
        if (xhr.status === 200) {
            var responseText = xhr.responseText;
            if (responseText.startsWith("success")) {
                location.reload(); // Reload the page to reflect updates
                updateCartCount();
            } else {
                alert("Failed to update cart.");
            }
        }
    };

    xhr.send(paramString);
}

function removeItem(menuId) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "updateCart", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var params = "remove=" + encodeURIComponent(menuId);

    xhr.onload = function() {
        if (xhr.status === 200) {
            var responseText = xhr.responseText;
            if (responseText.startsWith("success")) {
                document.getElementById("item-" + menuId).remove();
            } else {
                alert("Failed to remove item.");
            }
        }
    };

    xhr.send(params);
}

function updateCartCount() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'getCartCount', true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var responseText = xhr.responseText;
            var cartCount = parseInt(responseText, 10);
            var cartCountElement = document.getElementById('cart-count');
            cartCountElement.textContent = cartCount;
            cartCountElement.style.display = 'inline'; // Show cart count if hidden
        }
    };
    xhr.send();
}

function proceedToPayment() {
    var form = document.getElementById('payment-form');
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "process_payment", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Serialize FormData to URL-encoded string
    var params = [];
    formData.forEach(function(value, key) {
        params.push(encodeURIComponent(key) + "=" + encodeURIComponent(value));
    });
    var paramString = params.join("&");

    console.log("Sending data: ", paramString); // Debug log

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var responseText = xhr.responseText;
            console.log("Response received: ", responseText); // Debug log
            if (responseText.startsWith("success")) {
                var message = responseText.substring(responseText.indexOf(":") + 1).trim();
                alert("Payment Successful! " + message);
                setTimeout(function() {
                    window.location.href = 'confirmation.jsp';
                }, 500); // Delay to ensure alert is shown
            } else if (responseText.startsWith("error")) {
                alert("Payment Failed: " + responseText.substring(responseText.indexOf(":") + 1).trim());
            } else {
                alert("Unexpected response: " + responseText);
            }
        } else {
            alert("Server responded with HTTP Status: " + xhr.status);
        }
    };

    xhr.onerror = function() {
        alert("An error occurred while processing the payment.");
    };

    xhr.send(paramString);
}

