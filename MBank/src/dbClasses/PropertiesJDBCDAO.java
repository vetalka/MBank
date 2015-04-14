package dbClasses;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IPropertiesJDBCDAO;

public class PropertiesJDBCDAO implements IPropertiesJDBCDAO {

	Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	 private Connection getConnection() throws SQLException {
         Connection conn;
         //conn =  ConnectionFactory.getInstance().getConnection();
         conn = ConnectionPoll.getInstance().getConnection();
         return conn;
 }

	@Override
	public Map<String, String> getProperties() throws CheckedExceptions, UncheckedRuntimeException{
		
	    Map<String , String > prop = new HashMap<>();

		try {
			
            connection = getConnection();
            Statement ptmt = connection.createStatement();
            ResultSet rs = ptmt.executeQuery("SELECT * FROM Properties");
            while (rs.next()){
            	
                prop.put(rs.getString("prop_key"),rs.getString( "prop_value"));
            		
            }
            
    } catch (SQLException e) {
    	throw new CheckedExceptions("Get Properties is not working!");
    } finally {
            try {
                    if (resultSet != null)
                            resultSet.close();
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Properties can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		return  prop;
		
}

	@Override
	public void ViewSystemProperty() throws CheckedExceptions, UncheckedRuntimeException{
		
		try {
            String queryString = "SELECT * FROM Properties ";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
                   System.out.println("Prop Key: " + resultSet.getString("prop_key") + " , Prop Value : " + resultSet.getString("prop_value"));
                   
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View System Property is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Properties can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		
	}

	@Override
	public void updateSystemProperty(String propKey , String propValue) throws CheckedExceptions, UncheckedRuntimeException{
		
		 try {
             String queryString = "UPDATE Properties"
          		  +" SET prop_value = ? "
          		  +" WHERE prop_key = ?";
             connection = getConnection();
             ptmt = connection.prepareStatement(queryString);
             ptmt.setString(1, propValue);
             ptmt.setString(2, propKey);
             ptmt.executeUpdate();
             System.out.println("Table Updated Successfully");
     } catch (SQLException e) {
    	 throw new CheckedExceptions("Update System Property is not working!");
     } finally {
             try {
                     if (ptmt != null)
                             ptmt.close();
                     if (connection != null)
                             connection.close();
             }

             catch (SQLException e) {
            	 throw new UncheckedRuntimeException("Your parametrs in Properties can't be null!");
             } catch (Exception e) {
            	 throw new UncheckedRuntimeException("You have no connection!");

             }
     }
		
	}
	
}
