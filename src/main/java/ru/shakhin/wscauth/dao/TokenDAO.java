package ru.shakhin.wscauth.dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

/**
* Created by kshahin on 10/8/2015.
*/

public class TokenDAO {

    @Resource DataSource datasource;

    Connection getJNDIConnection(){
        String DATASOURCE_CONTEXT = "jdbc/oracle_ds";

        Connection result = null;
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
            env.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            env.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            env.put(Context.PROVIDER_URL, "localhost:8085");
            Properties props=new Properties();
            props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");//ur server ip
            props.setProperty("org.omg.CORBA.ORBInitialPort","3700"); //default is 3700
            //Context initialContext = new InitialContext();
            Context ctx = new InitialContext(props);
            //Context ctx = (Context) initialContext.lookup("java:global/env");
            if ( ctx == null){
                System.out.println("JNDI problem. Cannot get InitialContext.");
            }
            datasource = (DataSource)ctx.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            }
            else {
                System.out.println("Failed to lookup datasource.");
            }
        }
        catch ( NamingException ex ) {
            System.out.println("Cannot get connection: " + ex);
        }
        catch(SQLException ex){
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }
    public static void main(String[] args){

        System.out.println(new TokenDAO().getJNDIConnection());
    }
}
