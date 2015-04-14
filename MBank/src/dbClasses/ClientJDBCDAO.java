package dbClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IClientJDBCDAO;
import mainClasses.Client;
import mainClasses.ClientType;


public class ClientJDBCDAO implements IClientJDBCDAO {

	Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	 private Connection getConnection() throws SQLException {
         Connection conn;
         //conn =  ConnectionFactory.getInstance().getConnection();
         conn = ConnectionPoll.getInstance().getConnection();
         return conn;
 }
	 
	   /* (non-Javadoc)
	 * @see dbClasses.IClientJDBCDAO#addClient(mainClasses.Client)
	 */
	@Override
	public void addClientAdmin(Client client ) throws CheckedExceptions, UncheckedRuntimeException{
		
           try {
        	   long client_id = PKGenerator.getNextPk("Clients", "client_id");
                   String queryString = "INSERT INTO Clients (client_id , client_name , password , "
                   		+ "client_type , address , email , phone , comment ) "
                   		+ "VALUES(?,?,?,?,?,?,?,?)";
                   connection = getConnection();
                   ptmt = connection.prepareStatement(queryString);
                   ptmt.setLong(1, client_id);
                   ptmt.setString(2, client.getClientName());
                   ptmt.setString(3, client.getPassword());
                   ptmt.setString(4, client.getClientType().name());
                   ptmt.setString(5, client.getAddress());
                   ptmt.setString(6, client.getEmail());
                   ptmt.setString(7, client.getPhone());
                   ptmt.setString(8, client.getComment());
                   ptmt.executeUpdate();
                   System.out.println("Data Added Successfully");
           } catch (SQLException e) {
        	   throw new CheckedExceptions("Add Client Admin is not working!");
           } finally {
                   try {
                           if (ptmt != null)
                                   ptmt.close();
                           if (connection != null)
                                   connection.close();
                   } catch (SQLException e) {
                	   throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
                   } catch (Exception e) {
                	   throw new UncheckedRuntimeException("You have no connection!");
                   }

           }
	   }
	

	   
	   /* (non-Javadoc)
	 * @see dbClasses.IClientJDBCDAO#updateClient(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateClient(long clientId , String newAddress , String newEmail , String newPhone) throws CheckedExceptions, UncheckedRuntimeException{

           try {
                   String queryString = "UPDATE Clients"
                		  +" SET address = ?, email = ? , phone = ? "
                		  +" WHERE client_id = ?";
                   connection = getConnection();
                   ptmt = connection.prepareStatement(queryString);
                   ptmt.setString(1, newAddress);
                   ptmt.setString(2, newEmail);
                   ptmt.setString(3, newPhone);
                   ptmt.setLong(4, clientId);
                   ptmt.executeUpdate();
                   System.out.println("Table Updated Successfully");
           } catch (SQLException e) {
        	   throw new CheckedExceptions("Update Client is not working!");
           } finally {
                   try {
                           if (ptmt != null)
                                   ptmt.close();
                           if (connection != null)
                                   connection.close();
                   }

                   catch (SQLException e) {
                	   throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
                   } catch (Exception e) {
                	   throw new UncheckedRuntimeException("You have no connection!");

                   }
           }
   }

	   /* (non-Javadoc)
	 * @see dbClasses.IClientJDBCDAO#removeClient(int)
	 */
	@Override
	public void removeClient(long clientId ) throws CheckedExceptions, UncheckedRuntimeException{

           try {
                   String queryString = "DELETE FROM Clients "
                   		+ "WHERE client_id =" +clientId;
                   connection = getConnection();
                   ptmt = connection.prepareStatement(queryString);
                   ptmt.executeUpdate();
                   System.out.println("Data deleted Successfully");
           } catch (SQLException e) {
        	   throw new CheckedExceptions("Remove Client is not working!");
           } finally {
                   try {
                           if (ptmt != null)
                                   ptmt.close();
                           if (connection != null)
                                   connection.close();
                   } catch (SQLException e) {
                	   throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
                   } catch (Exception e) {
                	   throw new UncheckedRuntimeException("You have no connection!");
                   }

           }
	   }

	@Override
	public void updateClientAdmin(long clientId , ClientType clientType, String comment) throws CheckedExceptions, UncheckedRuntimeException{
		try {
            String queryString = "UPDATE Clients"
         		  +" SET  client_type = ? , comment = ? "
         		  +" WHERE client_id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, clientType.name());
            ptmt.setString(2, comment);
            ptmt.setLong(3 , clientId);
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
    } catch (SQLException e) {
    	throw new CheckedExceptions("Update Client Admin is not working!");
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            }

            catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");

            }
    }
		
	}


	@Override
	public void DepositToAccount(Client clientBean) throws CheckedExceptions, UncheckedRuntimeException{
		
		try {
            String queryString = "UPDATE Clients"
         		  +" SET client_type = ?"
         		  +" WHERE client_id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, clientBean.getClientType().toString());
            ptmt.setLong(2, clientBean.getClientId());
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
    } catch (SQLException e) {
    	throw new CheckedExceptions("Deposit to Account is not working!");
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            }

            catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");

            }
    }
	}

	
	@Override
	public Map<Long, String> getClient() throws CheckedExceptions, UncheckedRuntimeException{
		
	    Map<Long , String > client = new HashMap<>();

		try {
			
            //String queryString = "SELECT * FROM Properties";
            connection = getConnection();
            Statement ptmt = connection.createStatement();
           // ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery("SELECT * FROM Clients");
            while (rs.next()){
            	
                client.put(rs.getLong("client_id"),rs.getString( "client_type"));
            		
            }
            
    } catch (SQLException e) {
    	throw new CheckedExceptions("Get Client is not working!");
    } finally {
            try {
                    if (resultSet != null)
                            resultSet.close();
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		return  client;
		
}

	public void ViewClientDetails(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
	
        	 try {
                 String queryString = "SELECT * FROM Clients "
                		 +"WHERE client_id ="+clientId;
                 connection = getConnection();
                 ptmt = connection.prepareStatement(queryString);
                 resultSet = ptmt.executeQuery();
                 while (resultSet.next()) { 
            		
                        System.out.println("Client Id:" + resultSet.getLong("client_id")
                        		+" , Name:" + resultSet.getString("client_name")
                                        + " , Password: " + resultSet.getString("password") + " , Client Type: "
                                        + resultSet.getString("client_type") + " , Address: "
                                        + resultSet.getString("Address") + " , Email: " + resultSet.getString("email")
                                        + " , Phone: " + resultSet.getString("phone") 
                                        + " , Comment: " + resultSet.getString("comment"));
                 }
             } catch (SQLException e) {
            	 throw new CheckedExceptions("View Client Details is not working!");
             } finally {
                     try {
                             if (resultSet != null)
                                     resultSet.close();
                             if (ptmt != null)
                                     ptmt.close();
                             if (connection != null)
                                     connection.close();
                     } catch (SQLException e) {
                    	 throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
                     } catch (Exception e) {
                    	 throw new UncheckedRuntimeException("You have no connection!");
                     }
             }

}


	
	@Override
	public Client getClientBean(long clientId ) throws CheckedExceptions, UncheckedRuntimeException{
		
		Client clientBean = new Client();
		
		    try {
			    String queryString = "SELECT * FROM Clients "
            		                 +"WHERE client_id ="+clientId;
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                resultSet = ptmt.executeQuery();
	            while (resultSet.next()){
	            	

	            	clientBean.setClientName(resultSet.getString("client_name").toString());
	            	clientBean.setComment( resultSet.getString("comment").toString());
	            	clientBean.setEmail(resultSet.getString("email").toString());
	            	clientBean.setPassword(resultSet.getString("password").toString());
	            	clientBean.setPhone(resultSet.getString("phone").toString());
	            	clientBean.setAddress( resultSet.getString("Address").toString());
	            	clientBean.setClientType(ClientType.valueOf(resultSet.getString("client_type")));
	            	clientBean.setClientId(clientId);
	
	                }
	        } catch (SQLException e) {
	        	throw new CheckedExceptions("Get Client Bean is not working!");
	        } finally {
	                try {
	                        if (resultSet != null)
	                                resultSet.close();
	                        if (ptmt != null)
	                                ptmt.close();
	                        if (connection != null)
	                                connection.close();
	                } catch (SQLException e) {
	                	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
	                } catch (Exception e) {
	                	throw new UncheckedRuntimeException("You have no connection!");
	                }

	        }

		return clientBean;
	}

	@Override
	public ArrayList<Client> ViewAllClientDetails() throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Client> c = new ArrayList<Client>();
		
	    try {
	    	
		    String queryString = "SELECT * FROM Clients ";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()){
            	
            	Client clientBean = new Client();
            	
            	clientBean.setClientId(resultSet.getLong("client_id"));
            	clientBean.setClientType(ClientType.valueOf(resultSet.getString("client_type")));
            	clientBean.setClientName(resultSet.getString("client_name").toString());
            	clientBean.setComment( resultSet.getString("comment").toString());
            	clientBean.setEmail(resultSet.getString("email").toString());
            	clientBean.setPassword(resultSet.getString("password").toString());
            	clientBean.setPhone(resultSet.getString("phone").toString());
            	clientBean.setAddress( resultSet.getString("Address").toString());
            	c.add(clientBean);
            
                }
	  
        } catch (SQLException e) {
        	throw new CheckedExceptions("View All Client Details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }

        }

	return c ;

	}

 public Map<String, String> LoginForClient() throws CheckedExceptions, UncheckedRuntimeException{
		

	 Map<String, String> c = new HashMap<>();
	    
		try {
			
            connection = getConnection();
            Statement ptmt = connection.createStatement();
            ResultSet rs = ptmt.executeQuery("SELECT * FROM Clients");
            while (rs.next()){
            	
            	 c.put(rs.getString("client_name"),rs.getString( "password"));
            }
            
    } catch (SQLException e) {
    	throw new CheckedExceptions("Login Client is not working!");
    } finally {
            try {
                    if (resultSet != null)
                            resultSet.close();
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		return  c;
		
}
	
 
 public ArrayList<Client> LogIn() throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Client> c = new ArrayList<Client>();
		
	    try {
	    	
		    String queryString = "SELECT * FROM Clients ";
         connection = getConnection();
         ptmt = connection.prepareStatement(queryString);
         resultSet = ptmt.executeQuery();
         while (resultSet.next()){
         	
         	Client clientBean = new Client();
         	
         
         	clientBean.setClientName(resultSet.getString("client_name").toString());
         	clientBean.setPassword(resultSet.getString("password").toString());
         	c.add(clientBean);
         
             }
	  
     } catch (SQLException e) {
     	throw new CheckedExceptions("View All Client Details is not working!");
     } finally {
             try {
                     if (resultSet != null)
                             resultSet.close();
                     if (ptmt != null)
                             ptmt.close();
                     if (connection != null)
                             connection.close();
             } catch (SQLException e) {
             	throw new UncheckedRuntimeException("Your parametrs in Client can't be null!");
             } catch (Exception e) {
             	throw new UncheckedRuntimeException("You have no connection!");
             }

     }

	return c ;

	}
	
	}		
		
	
