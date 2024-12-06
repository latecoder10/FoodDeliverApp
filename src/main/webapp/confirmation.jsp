<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Confirmation</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome for checkmark icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS for animation -->
    <style>
        .checkmark {
            font-size: 100px;
            color: #28a745;
            animation: pulse 1s infinite;
        }

        @keyframes pulse {
            0% {
                transform: scale(1);
                opacity: 1;
            }
            50% {
                transform: scale(1.1);
                opacity: 0.7;
            }
            100% {
                transform: scale(1);
                opacity: 1;
            }
        }

        .message {
            margin-top: 20px;
            font-size: 24px;
            font-weight: 500;
        }

        .container {
            text-align: center;
            padding: 50px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="checkmark">
            <i class="fas fa-check-circle"></i>
        </div>
        <div class="message">
            Payment Successful! Your order is on the way!
        </div>
    </div>
    
    
    <!-- JavaScript to redirect to cart page after a delay -->
    <script>
        // Function to redirect to cart page
        function redirectToCart() {
            window.location.href = 'cart_page.jsp'; // Change 'cart.jsp' to the actual path of your cart page
        }

        // Redirect after 3 seconds
        setTimeout(redirectToCart, 3000);
    </script>

    <!-- Bootstrap JS and dependencies (jQuery and Popper.js) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
