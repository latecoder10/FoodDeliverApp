<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.food.DAO.RestaurantDAO,com.food.DAOImpl.RestaurantDAOImpl,java.util.List,com.food.modules.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet" href="CSS_Properties/test.css">
<link rel="stylesheet" href="CSS_Properties/filter.css">
<link rel="stylesheet" href="CSS_Properties/footer.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	>
</head>
<body>
	
	<%@ include file="Header/navbar.jsp"%>
	<%@ include file="Modals/loginModal.jsp"%>
	
	

	
	
	
	<%
		RestaurantDAO restaurantDAO=new RestaurantDAOImpl();
		List<Restaurant> restaurants=restaurantDAO.getAllRestaurant();
		
		
	%>
	
	
	
	

	<section class="filters">

		<div class="container">
			<div class="row">
				<div class="col text-center my-4">
					<h1 class="fs-2">Popular Restaurants</h1>
					<div class="underline mx-auto mt-3"></div>
				</div>
			</div>
			
			<div class="row mt-3 mb-4 button-group filter-button-group">
				<div class="col d-flex justify-content-center">
					<button type="button" data-filter="all"
						class="btn btn-primary mx-1 text-dark">All</button>
					<button type="button" data-filter="french"
						class="btn btn-primary mx-1 text-dark">French</button>
					<button type="button" data-filter="indian"
						class="btn btn-primary mx-1 text-dark">Indian</button>
					<button type="button" data-filter="italian"
						class="btn btn-primary mx-1 text-dark">Italian</button>
					<button type="button" data-filter="japanese"
						class="btn btn-primary mx-1 text-dark">Japanese</button>
					<button type="button" data-filter="american"
						class="btn btn-primary mx-1 text-dark">American</button>
					<button type="button" data-filter="andhra"
						class="btn btn-primary mx-1 text-dark">Andhra Style</button>
				</div>
			</div>
			
			<div class="row justify-content-center g-4" id="product-list">
			
			<%for(Restaurant restaurant:restaurants){ %>
				
				<div class="col-lg-3 col-md-6 product-item  <%=restaurant.getCuisineType().toLowerCase() %>" onclick="window.location.href='restaurants?id=<%= restaurant.getRestaurantId()%>'">
					<div class="product-img">
					
						<img src="<%=restaurant.getImagePath() %>"
							class="img-fluid d-block mx-auto" 
							alt="Electronic Product"
							onerror="this.src='images/err.png'; this.onerror=null;" 
							>
					
					</div>
					<div class="product-content text-left py-3">
						<span class="d-block text-uppercase fw-bold"><%=restaurant.getName()%></span>
						<span
							class="d-block">Under 200 rs</span>
						<div class="d-flex align-items-center mt-2">
							<div class="rating me-2">⭐ <span><%=restaurant.getRating() %></span></div>
							<div class="etr me-2"> <%=restaurant.getEstimateTimeArrival() %> mins</div>
							<div class="<%=restaurant.isActive()?"status-open":"status-closed" %> me-2"> <%=restaurant.isActive()?"Active":"Inactive" %></div>
							
						</div>
						<span class="d-block">description</span>
					</div>
				
					
				</div>
				
				
			<%} %>
			
			</div>
		</div>

	</section>
		<footer class="bg-dark text-light pt-5 mt-4 ">
    <div class="container px-5">
        <div class="row">
            <div class="col-6 col-lg-4">
                <h3 class="fw-bold">Tap Food Delivery</h3>
                <p class="pt-2">321, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                <p class="mb-2">0987654321</p>
                <p>1234567890</p>
            </div>
            <div class="col">
                <h4>Menu</h4>
                <ul class="list-unstyled pt-2">
                    <li class="py-1">Home</li>
                    <li class="py-1">Shorts</li>
                    <li class="py-1">About</li>
                    <li class="py-1">Contact</li>
                </ul>
            </div>
            <div class="col">
                <h4>More</h4>
                <ul class="list-unstyled pt-2">
                    <li class="py-1">Landing Pages</li>
                    <li class="py-1">FAQs</li>
                </ul>
            </div>
            <div class="col">
                <h4>Categories</h4>
                <ul class="list-unstyled pt-2">
                    <li class="py-1">Navbars</li>
                    <li class="py-1">Cards</li>
                    <li class="py-1">Buttons</li>
                    <li class="py-1">Carousels</li>
                </ul>
            </div>
            <div class="col-6 col-lg-3 text-lg-end">
                <h4>Social Media Links</h4>
                <div class="social-media pt-2">
                    <a href="#" class="text-light fs-2 me-3"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-light fs-2 me-3"><i class="bi bi-pinterest"></i></a>
                    <a href="#" class="text-light fs-2 me-3"><i class="bi bi-instagram"></i></a>
                    <a href="#" class="text-light fs-2"><i class="bi bi-linkedin"></i></a>
                </div>
            </div>
        </div>
        <hr>
        <div class="d-sm-flex justify-content-between py-1">
            <p>2024 © Tap. All Rights Reserved. </p>
            <p>
                <a href="#" class="text-light text-decoration-none pe-4">Terms of use</a>
                <a href="#" class="text-light text-decoration-none"> Privacy policy</a>
            </p>
        </div>
    </div>
</footer>
	
	
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		></script>
	<script src="script/filter.js"></script>
</body>
</html>
