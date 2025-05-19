package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidUsername(String username) { 
    	String regex = "^(?=.{1,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) { 
    	String regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isValidOTP(String otp) {
        String regex = "^\\d{6}$";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(otp);
        return matcher.matches();
    }
    
    public static boolean isValidVietnameseName(String name) {
        String regex = "^(?!.*\\d)[\\p{L} '-]{2,50}$";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,32}$";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\d{10}$");
    }
}
