function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function() {
        const output = document.getElementById('imagePreview');
        output.src = reader.result;
    }
    reader.readAsDataURL(event.target.files[0]);
}

function updateRestaurantProfile() {
    const restaurantId = document.getElementById('restaurantId').value;
    const restaurantName = document.getElementById('restaurantName').value;
    const address = document.getElementById('address').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const rating = document.getElementById('rating').value;
    const cuisineType = document.getElementById('cuisineType').value;
    const estimateTimeArrival = document.getElementById('estimateTimeArrival').value;
    const restaurantImage = document.getElementById('restaurantImage').files[0];

    const formData = new FormData();
    formData.append("restaurantId", restaurantId);
    formData.append("name", restaurantName);
    formData.append("address", address);
    formData.append("phoneNumber", phoneNumber);
    formData.append("rating", rating);
    formData.append("cuisineType", cuisineType);
    formData.append("estimateTimeArrival", estimateTimeArrival);
    formData.append("restaurantImage", restaurantImage);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "updateRestaurant", true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            const response = xhr.responseText;
            if (response.includes("success")) {
                alert("Profile updated successfully!");
                window.location.href = "adminDashboard.jsp?updateSuccess=true"; // Redirect on success
            } else {
                alert("Failed to update profile.");
            }
        }
    };
    xhr.send(formData); // Send the FormData object
}
