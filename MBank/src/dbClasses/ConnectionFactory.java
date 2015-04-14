package dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	

    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/MbankDB";
    String dbUser = "root";
    String dbPwd = "";

    private static ConnectionFactory connectionFactory = null;
	private Connection conn;

    
    
    private ConnectionFactory() {
            try {
                    Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
    }

    public Connection getConnection() throws SQLException {
            Connection conn = null;
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            return conn;
    }

    public static ConnectionFactory getInstance() {
            if (connectionFactory == null) {
                    connectionFactory = new ConnectionFactory();
            }
            return connectionFactory;
            
    }
    
    public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
		
		conn = null;
	}

}
