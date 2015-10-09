package ru.shakhin.wscauth.dao;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ru.shakhin.wscauth.model.Token;



/**
* Created by kshahin on 10/8/2015.
*/

public class TokenDAO {

    final static String INSERTTOKEN = "Insert into TOKENS (TOKEN,USER_MAIL,DATE_TOKEN) values (?,?,?)";
    final static String SELECTETOKENSBYUSER = "Select * from TOKENS Where USER_MAIL = ?";


    public static void createToken(String email,String token, Date date){
        DataSource ds = null;
        ds = FactoryDAO.getOracleDataSource();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERTTOKEN);
            pstmt.setString(1,token);
            pstmt.setString(2,email);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


            //Date today = new Date();

            String sdate = df.format(date);
            pstmt.setString(3, sdate);
            pstmt.executeQuery();

    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
        try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    public static List<Token> getEtokenByUser(String email) throws ParseException {
        DataSource ds = null;
        ds = FactoryDAO.getOracleDataSource();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Token> elist = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SELECTETOKENSBYUSER);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            elist = new ArrayList<Token>();
            while(rs.next()){
                Token token = new Token();
                token.setToken(rs.getString("TOKEN"));
                token.setEmail(rs.getString("USER_MAIL"));
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
                if(rs.getString("DATE_TOKEN") != null) {
                    Date date = (Date) format.parse(rs.getString("DATE_TOKEN"));
                    token.setDate(date);
                } else {
                    token.setDate(null);
                }

                elist.add(token);
                System.out.println(" "+token.getEmail()+" "+token.getDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return elist;

    }

    private static void testDataSource() {
        DataSource ds = null;
        ds = FactoryDAO.getOracleDataSource();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from Items");
            while(rs.next()){
                System.out.println("name_item"+rs.getString("name_item")+", path="+rs.getString("path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws ParseException {

       // System.out.println(getOracleDataSource());
        //testDataSource();
        getEtokenByUser("mail@mail.ru");
        getEtokenByUser("mail@yandex.ru");
        getEtokenByUser("mail@rambler.ru");
        createToken("mail@yandex.ru",UUID.randomUUID().toString(),new Date());
    }

}
