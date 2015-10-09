package ru.shakhin.wscauth.dao;

import ru.shakhin.wscauth.model.Token;
import ru.shakhin.wscauth.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by kshahin on 10/8/2015.
 */
public class UserDAO {

    final static String INSERTUSER = "Insert into USERS (MAIL,PASSWORD) values (?,?)";
    final static String SELECTUSERBYEMAIL = "Select from users where email = ?";

    public static void createUser(String email, String password) {
        DataSource ds = null;
        ds = FactoryDAO.getOracleDataSource();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERTUSER);
            pstmt.setString(2, password);
            pstmt.setString(1, email);

            pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static User getUserByMail(String email)  {
        DataSource ds = null;
        ds = FactoryDAO.getOracleDataSource();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SELECTUSERBYEMAIL);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();

            while(rs.next()){
                 user = new User();
                user.setEmail(rs.getString("MAIL"));
                user.setPassword(rs.getString("PASSWORD"));

                System.out.println(" "+user.getEmail());
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
        return user;

    }
}
