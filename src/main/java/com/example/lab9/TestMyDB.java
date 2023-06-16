package com.example.lab9;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMyDB {
    public static void main(String[] args) {
        MyDB db = MyDB.getInstance("127.0.0.1", 3306, "mydb");
        db.setUser("root");
        db.setPassword("zaq1@WSX");
        Connection conn = db.getConnection();
        if (conn != null) {
            try {
                Statement statement = conn.createStatement();
                Number dostawa = 100;
                statement.executeUpdate("UPDATE coffee_houses SET COFFEE = COFFEE + " + dostawa + " WHERE CITY = 'SF'");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM coffee_houses");
                System.out.printf("%10s%20s%7s%7s%7s\n", "STORE_ID", "CITY", "COFFEE", "MERCH", "TOTAL");
                while (resultSet.next()) {
                    System.out.printf("%10d%20s%7d%7d%7d\n", resultSet.getInt("STORE_ID"), resultSet.getString("CITY"),
                            resultSet.getInt("COFFEE"), resultSet.getInt("MERCH"), resultSet.getInt("TOTAL"));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Błąd wykonania zapytania SQL: " + e.getMessage());
            }
        }
        db.closeConnection();
        System.out.println("Połączenie z bazą zakończone");
    }
}
