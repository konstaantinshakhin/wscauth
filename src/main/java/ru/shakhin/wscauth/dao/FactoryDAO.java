package ru.shakhin.wscauth.dao;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by kshahin on 10/9/2015.
 */
public class FactoryDAO {
    public static DataSource getOracleDataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleDataSource oracleDS = null;
        try {
//            fis = new FileInputStream("db.properties");
//            props.load(fis);
            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            oracleDS.setUser("skv");
            oracleDS.setPassword("skv");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
}
