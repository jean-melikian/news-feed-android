package fr.esgi.newsfeed.models;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public class LoginInformations {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginInformations(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
