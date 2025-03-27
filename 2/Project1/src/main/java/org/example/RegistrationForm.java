package org.example;
import java.util.regex.Pattern;
public class RegistrationForm {
    private String name;
    private String email;
    private String password;
    public  boolean CheckName(String name){
        return name != null && !name.trim().isEmpty();
    }
    public  boolean CheckEmail(String email){
        return email != null && Pattern.matches("^[A-Za-z0-9]+@[A-Za-z]+\\.[a-z]{2,3}$", email);
    }

    public boolean CheckPassword(String password){
        return password != null && password.length() >= 10;
    }

    public boolean Check(String name, String email, String password){
        if (CheckName(name) && CheckEmail(email) && CheckPassword(password)){
            this.name = name;
            this.email = email;
            this.password = password;
            return true;
        }
        return false;
    }
}
