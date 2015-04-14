package dbClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IAccountsJDBCDAO;
import mainClasses.Accounts;

public class AccountsJDBCDAO implements IAccountsJDBCDAO {

	Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	 private Connection getConnection() throws SQLException {
         Connection conn;
        // conn =  ConnectionFactory.getInstance().getConnection();
         conn = ConnectionPoll.getInstance().getConnection();
         return conn;
 }
	
	/* (non-Javadoc)
	 * @see dbClasses.IAccountsJDBCDAO#addAccounts(mainClasses.Accounts)
	 */
	 //when administrator add a new client the he made a new account
	@Override
	public void addAccounts(Accounts account) throws CheckedExceptions, UncheckedRuntimeException {
           try {
        	   
        	   long account_id = PKGenerator.getNextPk("Accounts", "account_id");
        	   long client_id = ((PKGenerator.getNextPk("Clients", "client_id"))-1);
        	   
                   String queryString = "INSERT INTO Accounts (account_id , client_id , balance , "
                   		+ "credit_limit , comment ) "
                   		+ "VALUES(?,?,?,?,?)";
                   connection = getConnection();
                   ptmt = connection.prepareStatement(queryString);
                   ptmt.setLong(1, account_id);
                   ptmt.setLong(2, client_id);
                   ptmt.setDouble(3, account.getBalance());
                   ptmt.setString(4, account.getCreditLimit());
                   ptmt.setString(5, account.getComment());
                   ptmt.executeUpdate();
                   System.out.println("Data Added Successfully");
           } catch (SQLException e) {
        	   throw new CheckedExceptions("Add Account is not working!");
           } finally {
                   try {
                           if (ptmt != null)
                                   ptmt.close();
                           if (connection != null)
                                   connection.close();
                   } catch (SQLException e) {
                         
                	   throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
                   } catch (Exception e) {
                          
                	   throw new UncheckedRuntimeException("You have no connection!");
                   }

           }
	   }

	@Override
	public void removeAccounts(long accountId) throws CheckedExceptions, UncheckedRuntimeException{
		
		  try {
              String queryString = "DELETE FROM Accounts "
            		  +"WHERE account_id ="+accountId;
              connection = getConnection();
              ptmt = connection.prepareStatement(queryString);
              ptmt.executeUpdate();
              System.out.println("Data deleted Successfully");
      } catch (SQLException e) {
    	  throw new CheckedExceptions("Remove Accounts is not working!");
      } finally {
              try {
                      if (ptmt != null)
                              ptmt.close();
                      if (connection != null)
                              connection.close();
              } catch (SQLException e) {
            	  throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
              } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");
              }

      }
		
	}

	@Override
	public void DepositToAccount(Accounts accountBean)throws CheckedExceptions, UncheckedRuntimeException {
		
		 try {
             String queryString = "UPDATE Accounts"
          		  +" SET comment = ?, balance = ? , credit_limit = ? "
          		  +" WHERE account_id = ?";
             connection = getConnection();
             ptmt = connection.prepareStatement(queryString);
             ptmt.setString(1, accountBean.getComment());
             ptmt.setDouble(2, accountBean.getBalance());
             ptmt.setString(3, accountBean.getCreditLimit());
             ptmt.setLong(4, accountBean.getAccountId());
             ptmt.executeUpdate();
             System.out.println("Table Updated Successfully");
     } catch (SQLException e) {
    	 throw new CheckedExceptions("Deposit To Account is not working!");
     } finally {
             try {
                     if (ptmt != null)
                             ptmt.close();
                     if (connection != null)
                             connection.close();
             }

             catch (SQLException e) {
            	 throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
             } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");

             }
     }

	}

	@Override
	public Map<Long, Double> getAccount()throws CheckedExceptions, UncheckedRuntimeException{
		
		Map<Long , Double > account = new HashMap<>();

		try {
			
            //String queryString = "SELECT * FROM Properties";
            connection = getConnection();
            Statement ptmt = connection.createStatement();
           // ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery("SELECT * FROM Accounts");
            while (rs.next()){
            	
                account.put(rs.getLong("account_id"),rs.getDouble( "balance"));
            		
            }
            
    } catch (SQLException e) {
    	throw new CheckedExceptions("Get Accounts is not working!");
    } finally {
            try {
                    if (resultSet != null)
                            resultSet.close();
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
            } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		return  account;
	}

	@Override
	public Map<Long, String> getAccount1() throws CheckedExceptions, UncheckedRuntimeException{
		
		Map<Long , String > account = new HashMap<>();

		try {
			
            //String queryString = "SELECT * FROM Properties";
            connection = getConnection();
            Statement ptmt = connection.createStatement();
           // ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery("SELECT * FROM Accounts");
            while (rs.next()){
            	
                account.put(rs.getLong("account_id"),rs.getString( "credit_limit"));
            		
            }
            
    } catch (SQLException e) {
    	throw new CheckedExceptions("Get Accounts1 is not working!");
    } finally {
            try {
                    if (resultSet != null)
                            resultSet.close();
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
            } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		
		return  account	;
				
	}

	@Override
	public void updateClientAdmin(Accounts accontBean)throws CheckedExceptions, UncheckedRuntimeException {
		
		try {
            String queryString = "UPDATE Accounts"
         		  +" SET  credit_limit = ?  "
         		  +" WHERE client_id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, accontBean.getCreditLimit());
            ptmt.setLong(2, accontBean.getClientId());
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
    } catch (SQLException e) {
    	throw new CheckedExceptions("Update Accounts is not working!");
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            }

            catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
            } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");

            }
    }
		
	}

	@Override
	public void ViewAccountDetails(long clientId)throws CheckedExceptions, UncheckedRuntimeException {
		
		try {
            String queryString = "SELECT * FROM Accounts "
           		 +"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
                   System.out.println("Account Id :" + resultSet.getLong("account_id")
                   		+" , Balance:" + resultSet.getString("balance")
                                   + " , Credit Limit: " + resultSet.getString("credit_limit")
                                   + " , Comment: " + resultSet.getString("comment"));
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Accounts details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
                } catch (Exception e) {
                	  throw new UncheckedRuntimeException("You have no connection!");
                }
        }

		
	}

	@Override
	public ArrayList<Accounts> ViewAllAccountDetails() throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Accounts> a = new ArrayList<Accounts>();
		
		try {
            String queryString = "SELECT * FROM Accounts ";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Accounts accountBean = new Accounts();
            	
            	accountBean.setAccountId(resultSet.getLong("account_id"));
            	accountBean.setClientId(resultSet.getLong("client_id"));
            	accountBean.setBalance(resultSet.getDouble("balance"));
            	accountBean.setComment(resultSet.getString("comment").toString());
            	accountBean.setCreditLimit(resultSet.getString("credit_limit").toString());
            	
            	a.add(accountBean);
            	
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View all Account details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
                } catch (Exception e) {
                	  throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return a;
		
	}

	@Override
	public Accounts getAccountsBean(long accountId)throws CheckedExceptions, UncheckedRuntimeException {
		
		Accounts accountBean = new Accounts();
		
		 try {
			    String queryString = "SELECT * FROM Accounts "
         		                 +"WHERE account_id ="+accountId;
             connection = getConnection();
             ptmt = connection.prepareStatement(queryString);
             resultSet = ptmt.executeQuery();
	            while (resultSet.next()){
	            	
	            	accountBean.setAccountId(accountId);
	            	accountBean.setClientId(resultSet.getLong("client_id"));
	            	accountBean.setBalance(resultSet.getDouble("balance"));
	            	accountBean.setComment(resultSet.getString("comment").toString());
	            	accountBean.setCreditLimit(resultSet.getString("credit_limit").toString());
	            	
	                }
	        } catch (SQLException e) {
	        	throw new CheckedExceptions("Get Account Bean is not working!");
	        } finally {
	                try {
	                        if (resultSet != null)
	                                resultSet.close();
	                        if (ptmt != null)
	                                ptmt.close();
	                        if (connection != null)
	                                connection.close();
	                } catch (SQLException e) {
	                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
	                } catch (Exception e) {
	                	  throw new UncheckedRuntimeException("You have no connection!");
	                }

	        }

		return accountBean;
	}

	@Override
	public void updateAccountBean(Accounts accountBean)throws CheckedExceptions, UncheckedRuntimeException {
		
		try {
            String queryString = "UPDATE Accounts"
         		  +" SET  credit_limit = ? , balance = ? , comment = ?  "
         		  +" WHERE account_id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, accountBean.getCreditLimit());
            ptmt.setDouble(2, accountBean.getBalance());
            ptmt.setString(3, accountBean.getComment());
            ptmt.setLong(4, accountBean.getAccountId());
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
    } catch (SQLException e) {
    	throw new CheckedExceptions("Update Account Bean is not working!");
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            }

            catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
            } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");

            }
    }
		
	}

	@Override
	public void addNewAccounts(Accounts accountBean, long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		try {
     	   
     	   long account_id = PKGenerator.getNextPk("Accounts", "account_id");

                String queryString = "INSERT INTO Accounts (account_id , client_id , balance , "
                		+ "credit_limit , comment ) "
                		+ "VALUES(?,?,?,?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setLong(1, account_id);
                ptmt.setLong(2, clientId);
                ptmt.setDouble(3, accountBean.getBalance());
                ptmt.setString(4, accountBean.getCreditLimit());
                ptmt.setString(5, accountBean.getComment());
                ptmt.executeUpdate();
                System.out.println("Data Added Successfully");
        } catch (SQLException e) {
        	throw new CheckedExceptions("Add new Account is not working!");
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
                } catch (Exception e) {
                	  throw new UncheckedRuntimeException("You have no connection!");
                }

        }
		
		
		
	}

	@Override
	public ArrayList<Accounts> ViewAccountsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException {
		
        ArrayList<Accounts> a = new ArrayList<Accounts>();
		
		try {
            String queryString = "SELECT * FROM Accounts "
            		+"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Accounts accountBean = new Accounts();
            	
            	accountBean.setAccountId(resultSet.getLong("account_id"));
            	accountBean.setClientId(resultSet.getLong("client_id"));
            	accountBean.setBalance(resultSet.getDouble("balance"));
            	accountBean.setComment(resultSet.getString("comment").toString());
            	accountBean.setCreditLimit(resultSet.getString("credit_limit").toString());
            	
            	a.add(accountBean);
            	
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Accounts by client Id is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
                } catch (Exception e) {
                	  throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return a;
	}

	@Override
	public void removeAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		 try {
             String queryString = "DELETE FROM Accounts "
           		  +"WHERE client_id ="+clientId;
             connection = getConnection();
             ptmt = connection.prepareStatement(queryString);
             ptmt.executeUpdate();
             System.out.println("Data deleted Successfully");
     } catch (SQLException e) {
    	 throw new CheckedExceptions("Remove Account by client id is not working!");
     } finally {
             try {
                     if (ptmt != null)
                             ptmt.close();
                     if (connection != null)
                             connection.close();
             } catch (SQLException e) {
            	 throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
             } catch (Exception e) {
            	  throw new UncheckedRuntimeException("You have no connection!");
             }

     }
	}

	@Override
	public Accounts getAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		Accounts accountBean = new Accounts();
		
		 try {
			    String queryString = "SELECT * FROM Accounts "
        		                 +"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
	            while (resultSet.next()){
	            	
	            	accountBean.setAccountId(resultSet.getLong("account_id"));
	            	accountBean.setClientId(clientId);
	            	accountBean.setBalance(resultSet.getDouble("balance"));
	            	accountBean.setComment(resultSet.getString("comment").toString());
	            	accountBean.setCreditLimit(resultSet.getString("credit_limit").toString());
	            	
	                }
	        } catch (SQLException e) {
	        	throw new CheckedExceptions("Get Account by client Id is not working!");
	        } finally {
	                try {
	                        if (resultSet != null)
	                                resultSet.close();
	                        if (ptmt != null)
	                                ptmt.close();
	                        if (connection != null)
	                                connection.close();
	                } catch (SQLException e) {
	                	throw new UncheckedRuntimeException("Your parametrs in Account can't be null!");
	                } catch (Exception e) {
	                	  throw new UncheckedRuntimeException("You have no connection!");
	                }

	        }

		return accountBean;
	}
		
	}


	   

