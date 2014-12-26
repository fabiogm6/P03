package br.com.fgm.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() throws SQLException {
        try {
        	System.out.println("ConnectionFactory - conectando 1... fábio");
              Class.forName("com.mysql.jdbc.Driver");
              System.out.println("ConnectionFactory - conectando 2... beleza fábio");
       } catch (ClassNotFoundException e ) {
               throw new SQLException(e);
       }
       
        return DriverManager.getConnection( "jdbc:mysql://localhost/fgm",
                      "root", "2563" );
}
}
