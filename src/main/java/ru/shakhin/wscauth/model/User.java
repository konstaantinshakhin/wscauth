package ru.shakhin.wscauth.model;

import java.util.List;

/**
 * Created by kshahin on 10/8/2015.
 */
public class User {
    private String email;
    private String password;
    private String etoken;
    private List<String> eList;

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

    public String getEtoken() {
        return etoken;
    }

    public void setEtoken(String etoken) {
        this.etoken = etoken;
    }
}
