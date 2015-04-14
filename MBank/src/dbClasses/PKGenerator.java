package dbClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PKGenerator {
	

	Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	
	

	public static Long getNextPk(String tableName , String pkColumnName) throws SQLException{
		
	
		//Connection connection =  ConnectionFactory.getInstance().getConnection();
		//connection = ConnectionFactory.getInstance().getConnection();
		Connection connection = ConnectionPoll.getInstance().getConnection();
		connection = ConnectionPoll.getInstance().getConnection();
        Statement ptmt = connection.createStatement();
		ResultSet rs = ptmt.executeQuery("SELECT MAX("+pkColumnName+")AS max_id FROM ("+tableName+")");
		rs.next();
		Long result = rs.getLong("max_id")+1;
		//ConnectionFactory.getInstance().returnConnection(connection);
		return result;
		
	}
	
}
