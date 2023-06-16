package com.example.lab9.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Properties;
import java.sql.DriverManager;
public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       String serverName = "127.0.0.1";
       String database = "mydb";
       Number portNumber = 3306;
       Connection conn = null;
       Properties connectionProps = new Properties();
       connectionProps.put("user", "root");
       connectionProps.put("password", "zaq1@WSX");
       connectionProps.put("serverTimezone", "Europe/Warsaw");

       conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + database, connectionProps);


       System.out.println("Connected to database");
    }
}
