//package ru.shakhin.wscauth.dao;
//
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
///**
// * Created by kshahin on 10/8/2015.
// */
//@Stateless
//public class TokenDAO {
//
//    @Resource DataSource datasource;
//
//    Connection getJNDIConnection(){
//        String DATASOURCE_CONTEXT = "jdbc/oracle_ds";
//
//        Connection result = null;
//        try {
//            Context initialContext = new InitialContext();
//            Context ctx = (Context) initialContext.lookup("java:global/env");
//            if ( initialContext == null){
//                System.out.println("JNDI problem. Cannot get InitialContext.");
//            }
//            datasource = (DataSource)ctx.lookup(DATASOURCE_CONTEXT);
//            if (datasource != null) {
//                result = datasource.getConnection();
//            }
//            else {
//                System.out.println("Failed to lookup datasource.");
//            }
//        }
//        catch ( NamingException ex ) {
//            System.out.println("Cannot get connection: " + ex);
//        }
//        catch(SQLException ex){
//            System.out.println("Cannot get connection: " + ex);
//        }
//        return result;
//    }
//    public static void main(String[] args){
//        System.out.println(new TokenDAO().getJNDIConnection());
//    }
//}
