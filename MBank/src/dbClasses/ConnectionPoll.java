package dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class ConnectionPoll {
	

    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/MbankDB";
    String dbUser = "root";
    String dbPwd = "";
    
    private long exsepshionTime;
    
    private static ConnectionPoll connectionPoll = null;
    private Hashtable<Connection, Long> usedConnection ;
    private Hashtable<Connection, Long> unUsedConnection;
    
    
    private ConnectionPoll() {
    	
    	usedConnection = new Hashtable<Connection, Long>();
    	unUsedConnection = new Hashtable<Connection, Long>();
    	
    	exsepshionTime = 30000;
    	
        try {
                Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
    }
    
    public static ConnectionPoll getInstance(){
    	
    	if (connectionPoll == null){
    		
    		connectionPoll = new ConnectionPoll();
    	}
    	return connectionPoll;
    }
    
    public Connection getConnection() throws SQLException{
		
    	try{
    		
    		return (DriverManager.getConnection(connectionUrl, dbUser, dbPwd));
    		
    	}catch(SQLException e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}	
    }
    
    public synchronized Connection checkOut() throws SQLException {
    	
    	long now = System.currentTimeMillis();
    	
    	Connection connection;
    	
    	if (unUsedConnection.size() > 0 ){
    		
    		Enumeration<Connection> e = unUsedConnection.keys();
    		while (e.hasMoreElements()){
    			
    			connection = e.nextElement();
    		    if((now - unUsedConnection.get(connection)) > exsepshionTime ){
    			 
    			   unUsedConnection.remove(connection);
    			   expire(connection);
    			   connection = null;
    			
    		   }else {
    			
    			   if (validate(connection)){
    				
    				  unUsedConnection.remove(connection);
    				  usedConnection.put(connection, now);
    				  return (connection);
    				
    		    	}else{
    				
    			    	unUsedConnection.remove(connection);
    				    expire(connection);
    				    connection = null;
    				
    			    }
    		    }
         	}
     	} 
    	
        connection = getConnection();
        usedConnection.put(connection, now);
        return connection;
    		
    }
    
    public synchronized void checIn(Connection connection){
    
    	usedConnection.remove(connection);
    	unUsedConnection.put(connection, System.currentTimeMillis());
    	
    }

    private boolean validate (Connection connection){
    	
    	try{
    		return !(connection.isClosed());
    	}catch(SQLException e){
    		e.printStackTrace();
    		return (false);
    	}
    }
    
    public void expire(Connection connection){
    
    	try{
    		connection.close();
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
}
}