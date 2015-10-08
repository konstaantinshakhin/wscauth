package ru.shakhin.wscauth.model;

import java.util.Date;
import java.util.UUID;



/**
 * Created by kshahin on 10/8/2015.
 */
public class Token {
    private String token;
    private Date Date;
    public static void main(String[] args){
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
}
