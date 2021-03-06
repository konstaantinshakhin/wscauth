package ru.shakhin.wscauth.model;

import java.util.Date;
import java.util.UUID;



/**
 * Created by kshahin on 10/8/2015.
 */
public class Token {
    private String token;
    private Date date;
    private String email;
    public Token(){
        token = UUID.randomUUID().toString();
        date = new Date();
    }
    public static void main(String[] args){
        for(int i = 0;i<5;i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid);
        }
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
