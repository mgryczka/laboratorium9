package com.example.lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDB {
    private static MyDB instance;  // Singleton - instancja klasy
    private String host;
    private int port;
    private String dbName;
    private String user;
    private String password;
    private Connection conn;

    private MyDB(String host, int port, String dbName) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
    }

    public static MyDB getInstance(String host, int port, String dbName) {
        if (instance == null) {
            instance = new MyDB(host, port, dbName);
        }
        return instance;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void connect() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("serverTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        try {
            conn = DriverManager.getConnection(jdbcString, connectionProps);
        } catch (SQLException e) {
            System.out.println("Błąd podłączenia do bazy: " + jdbcString);
            System.out.println("Komunikat błędu: " + e.getMessage());
            conn = null;
        }
        System.out.println("Connected to database: " + dbName);
    }

    public Connection getConnection() {
        if (conn == null)
            connect();
        return conn;
    }

    public void closeConnection(){
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e){
                System.out.println("Błąd przy zamykaniu połączenia bazodanowego: " +e.getMessage());
            }
        conn = null;
    }

}
