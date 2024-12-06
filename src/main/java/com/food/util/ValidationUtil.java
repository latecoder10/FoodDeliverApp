package com.food.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.mindrot.jbcrypt.BCrypt;

import com.food.modules.User;

public class ValidationUtil {

    // Validate if a string is null or empty
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Hash password using BCrypt
    public static String hashPassword(String password) {
        return password;
    }

    // Validate user type
    public static boolean isValidUserType(String userType) {
        try {
            User.UserType.valueOf(userType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
