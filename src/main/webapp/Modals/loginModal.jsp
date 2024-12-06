	
	  <!-- Modal for Login/Signup -->
    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel">Login / Register</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" id="loginRegisterTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <a class="nav-link active" id="login-tab" data-bs-toggle="tab" href="#login" role="tab" aria-controls="login" aria-selected="true">Login</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" id="register-tab" data-bs-toggle="tab" href="#register" role="tab" aria-controls="register" aria-selected="false">Register</a>
                        </li>
                    </ul>
                    <!-- Tab content -->
                    <div class="tab-content mt-2" id="loginRegisterTabContent">
                        <div class="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="login-tab">
                            <form action="login" method="post">
                                <div class="mb-3">
                                    <label for="loginUsername" class="form-label">Username</label>
                                    <input type="text" class="form-control" id="loginUsername" name="user_name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="loginPassword" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="loginPassword" name="password" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Login</button>
                            </form>
                        </div>
                        <div class="tab-pane fade" id="register" role="tabpanel" aria-labelledby="register-tab">
                            <form action="register" method="post">
                                <div class="mb-3">
                                    <label for="registerName" class="form-label">Name</label>
                                    <input type="text" class="form-control" id="registerName" name="name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="registerUsername" class="form-label">Username</label>
                                    <input type="text" class="form-control" id="registerUsername" name="user_name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="registerPassword" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="registerPassword" name="password" required>
                                </div>
                                <div class="mb-3">
                                    <label for="registerEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="registerEmail" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="registerPhoneNumber" class="form-label">Phone Number</label>
                                    <input type="text" class="form-control" id="registerPhoneNumber" name="phone_number">
                                </div>
                                <div class="mb-3">
                                    <label for="registerAddress" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="registerAddress" name="address">
                                </div>
                                <div class="mb-3">
                                    <label for="registerUserType" class="form-label">User Type</label>
                                    <select class="form-select" id="registerUserType" name="user_type" required>
                                        <option value="" disabled selected>Select user type</option>
                                        <option value="CUSTOMER">Customer</option>
                                        <option value="ADMIN">Admin</option>
                                        <option value="STAFF">Staff</option>

                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>