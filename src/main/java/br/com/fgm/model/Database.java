package br.com.fgm.model;

import java.sql.Connection;
import java.sql.DriverManager;


// classe para o data table do primefaces
public class Database {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/fgm",
            		"root", "2563");
            System.out.println("Database - getConnection()");
            return con;
        } catch (Exception ex) {
            System.out.println("Database - getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
