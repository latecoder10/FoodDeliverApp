<%@ page import="com.food.modules.Restaurant" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="dashboard/dashboard.css">
    <title>Update Restaurant Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .container-fluid {
            padding: 20px;
        }

        .card {
            border: none;
            border-radius: 10px;
        }

        h3 {
            color: #343a40;
        }

        .form-label {
            font-weight: bold;
        }

        .form-control {
            border-radius: 5px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .text-center img {
            max-width: 100%;
            border-radius: 10px;
        }

        #imagePreview {
            max-height: 300px;
            width: 100%;
            object-fit: cover;
            border-radius: 1rem;
            border: 5px solid #f0f0f0;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            margin-bottom: 20px;
        }

        @media (min-width: 768px) {
            .container-fluid {
                max-width: 1200px;
            }
        }
    </style>
</head>

<body>
    <div class="d-flex" id="wrapper">
        <%@ include file="dashboard/sidebar.jsp" %>

        <div id="page-content-wrapper">
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-2 m-0">Update Restaurant Profile</h2>
                </div>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user me-2"></i>John Doe
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <%
                Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
                if (restaurant == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            %>

            <div class="container-fluid px-4">
                <div class="row my-5">
                    <h3 class="fs-4 mb-3">Update Restaurant Profile</h3>
                    <div class="col">
                        <div class="card p-4 bg-white rounded shadow-sm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="text-center mb-4">
                                        <img id="imagePreview" src="<%=restaurant.getImagePath() %>" alt="Restaurant Image" onerror="this.onerror=null; this.src='images/placeholder.jpg';">
                                        <%System.out.println("inside update jsp :"+restaurant.getImagePath()); %>
                                    </div>
                                    <div class="mb-3">
                                        <label for="restaurantImage" class="form-label">Restaurant Image</label>
                                        <input type="file" class="form-control" id="restaurantImage" accept="image/*"  onchange="previewImage(event)">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Restaurant ID</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-utensils"></i></span>
                                            <input type="text" class="form-control" id="restaurantId" value="<%= restaurant.getRestaurantId() %>" readonly>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Rating</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-star"></i></span>
                                            <input type="number" class="form-control" id="rating" value="<%= restaurant.getRating() %>" readonly>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="restaurantName" class="form-label">Restaurant Name</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-store"></i></span>
                                            <input type="text" class="form-control" id="restaurantName" value="<%= restaurant.getName() %>" placeholder="Enter restaurant name" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address" class="form-label">Address</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                                            <input type="text" class="form-control" id="address" value="<%= restaurant.getAddress() %>" placeholder="Enter restaurant address" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="phoneNumber" class="form-label">Phone Number</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                            <input type="text" class="form-control" id="phoneNumber" value="<%= restaurant.getPhoneNumber() %>" placeholder="Enter phone number" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="cuisineType" class="form-label">Cuisine Type</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-utensils"></i></span>
                                            <input type="text" class="form-control" id="cuisineType" value="<%= restaurant.getCuisineType() %>" placeholder="Enter cuisine type" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="estimateTimeArrival" class="form-label">Estimated Time of Arrival (min)</label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fas fa-clock"></i></span>
                                            <input type="number" class="form-control" id="estimateTimeArrival" value="<%= restaurant.getEstimateTimeArrival() %>" placeholder="Enter estimated arrival time" required>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary" onclick="updateRestaurantProfile()">Update Profile</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="dashboard/dashboardIndex.js"></script>
    <script src="dashboard/updateRestaurantProfile.js"></script>

</body>

</html>
