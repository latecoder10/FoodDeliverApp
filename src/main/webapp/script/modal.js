document.addEventListener('DOMContentLoaded', function() {
    // Select the modal element
    const loginModalElement = document.getElementById('loginModal');

    // Select the login form within the modal
    const loginForm = loginModalElement.querySelector('form[action="login"]');
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle login form submission
            console.log('Login form submitted');
            // Hide the modal
            const loginModal = bootstrap.Modal.getInstance(loginModalElement);
            loginModal.hide();
        });
    }

    // Select the signup form within the modal
    const signupForm = loginModalElement.querySelector('form[action="register"]');
    
    if (signupForm) {
        signupForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle signup form submission
            console.log('Signup form submitted');
            // Hide the modal
            const signupModal = bootstrap.Modal.getInstance(loginModalElement);
            signupModal.hide();
        });
    }
});
