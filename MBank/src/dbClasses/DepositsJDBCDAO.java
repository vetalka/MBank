package dbClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mainClasses.DepositType;
import mainClasses.Deposits;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IDepositsJDBCDAO;

public class DepositsJDBCDAO implements IDepositsJDBCDAO {
	
	Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
	 private Connection getConnection() throws SQLException {
         Connection conn;
        // conn =  ConnectionFactory.getInstance().getConnection();
         conn = ConnectionPoll.getInstance().getConnection();
         return conn;
 }

	@Override
	public void CrateNewDeposit(Deposits depositsBean)  throws CheckedExceptions, UncheckedRuntimeException{
		
		try {
     	   long deposite_id = PKGenerator.getNextPk("Deposits ", "deposit_id");
                String queryString = "INSERT INTO Deposits (deposit_id , client_id , balance , "
                		+ " deposit_type , estimated_balance , opening_date , closing_date ) "
                		+ "VALUES(?,?,?,?,?,?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setLong(1, deposite_id);
                ptmt.setLong(2, depositsBean.getClientId());
                ptmt.setDouble(3, depositsBean.getBalance());
                ptmt.setString(4, depositsBean.getDepositType().name());
                ptmt.setDouble(5, depositsBean.getEstimatedBalance());
                ptmt.setDate(6,  depositsBean.getOpeningDate());
                ptmt.setDate(7, depositsBean.getClosingDate());
                
                ptmt.executeUpdate();
                System.out.println("Data Added Successfully");
        } catch (SQLException e) {
        	throw new CheckedExceptions("Crate New Deposit is not working!");
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }

        }

		
	}

	@Override
	public void ViewDepositsDetails(long clientId)  throws CheckedExceptions, UncheckedRuntimeException{
		
		try {
            String queryString = "SELECT * FROM Deposits "
           		 +"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
                   System.out.println("Deposit Id :" + resultSet.getLong("deposit_id")
                   		+" , Balance:" + resultSet.getString("balance")
                                   + " , Deposit Type: " + resultSet.getString("deposit_type")
                                   + " , Estimated Balance: " + resultSet.getString("estimated_balance")
                                   + " , Opening Date: " + resultSet.getDate("opening_date") 
                                   + " , Closing Date: " + resultSet.getDate("closing_date"));
                   
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Deposits Details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }

		
	}

	@Override
	public ArrayList<Deposits> ViewAllDepositsDetails() throws CheckedExceptions, UncheckedRuntimeException {
		
		ArrayList<Deposits> d = new ArrayList<Deposits>();
		
		
		
		try {
            String queryString = "SELECT * FROM Deposits ";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Deposits depositsBean = new Deposits();
            	
            	depositsBean.setClientid(resultSet.getLong("client_id"));
            	depositsBean.setDepositId(resultSet.getLong("deposit_id"));
            	depositsBean.setBalance(resultSet.getDouble("balance"));
            	depositsBean.setClosingDate(resultSet.getDate("closing_date"));
            	depositsBean.setOpeningDate(resultSet.getDate("opening_date"));
            	depositsBean.setEstimatedBalance(resultSet.getDouble("estimated_balance"));
            	depositsBean.setDepositType(DepositType.valueOf(resultSet.getString("deposit_type")));
            	
            	d.add(depositsBean);
                   
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View All Deposits Details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return d;
		
	}

	@Override
	public Deposits getDepositeBean(long depositeId)  throws CheckedExceptions, UncheckedRuntimeException{
		
		Deposits depositsBean = new Deposits();
		
	    try {
		    String queryString = "SELECT * FROM Deposits "
        		                 +"WHERE deposit_id ="+depositeId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()){
            	
            	depositsBean.setClientid(resultSet.getLong("client_id"));
            	depositsBean.setDepositId(depositeId);
            	depositsBean.setBalance(resultSet.getDouble("balance"));
            	depositsBean.setClosingDate(resultSet.getDate("closing_date"));
            	depositsBean.setOpeningDate(resultSet.getDate("opening_date"));
            	depositsBean.setEstimatedBalance(resultSet.getDouble("estimated_balance"));
            	depositsBean.setDepositType(DepositType.valueOf(resultSet.getString("deposit_type")));
            	
                }
        } catch (SQLException e) {
        	throw new CheckedExceptions("Get Deposite Bean is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }

        }

	return depositsBean;
	
	}

	@Override
	public Map<Long, String> getDepositeType()  throws CheckedExceptions, UncheckedRuntimeException{
		
		 Map<Long , String > deposit = new HashMap<>();

			try {
				
	            connection = getConnection();
	            Statement ptmt = connection.createStatement();
	            ResultSet rs = ptmt.executeQuery("SELECT * FROM Deposits");
	            while (rs.next()){
	            	
	                deposit.put(rs.getLong("deposit_id"),rs.getString( "deposit_type"));
	            		
	            }
	            
	    } catch (SQLException e) {
	    	throw new CheckedExceptions("Get Deposite Type is not working!");
	    } finally {
	            try {
	                    if (resultSet != null)
	                            resultSet.close();
	                    if (ptmt != null)
	                            ptmt.close();
	                    if (connection != null)
	                            connection.close();
	            } catch (SQLException e) {
	            	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
	            } catch (Exception e) {
	            	throw new UncheckedRuntimeException("You have no connection!");
	            }

	    }
			return  deposit;
	}

	@Override
	public Deposits getDepositeBeanByDepositeId(long depositId , long clientId)  throws CheckedExceptions, UncheckedRuntimeException{
		
		
        Deposits depositsBean = new Deposits();
		
	    try {
		    String queryString = "SELECT * FROM Deposits "
        		                 +"WHERE deposit_id ="+depositId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()){
            	
            	depositsBean.setClientid(clientId);
            	depositsBean.setDepositId(depositId);
            	depositsBean.setBalance(resultSet.getDouble("balance"));
            	depositsBean.setClosingDate(resultSet.getDate("closing_date"));
            	depositsBean.setOpeningDate(resultSet.getDate("opening_date"));
            	depositsBean.setEstimatedBalance(resultSet.getDouble("estimated_balance"));
            	depositsBean.setDepositType(DepositType.valueOf(resultSet.getString("deposit_type")));
            	
                }
        } catch (SQLException e) {
        	throw new CheckedExceptions("Get Deposite Bean By Deposite Id is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }

        }

	return depositsBean;
	}

	@Override
	public void preOpenDeposite(long depositId)  throws CheckedExceptions, UncheckedRuntimeException{
		
		try {
            String queryString = "DELETE FROM Deposits WHERE deposit_id=" +depositId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
    } catch (SQLException e) {
    	throw new CheckedExceptions("Pre Open Deposite is not working!");
    } finally {
            try {
                    if (ptmt != null)
                            ptmt.close();
                    if (connection != null)
                            connection.close();
            } catch (SQLException e) {
            	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
            } catch (Exception e) {
            	throw new UncheckedRuntimeException("You have no connection!");
            }

    }
		
	}

	@Override
	public ArrayList<Deposits> ViewDepositsByClientId(long clientId)  throws CheckedExceptions, UncheckedRuntimeException{
		
        ArrayList<Deposits> d = new ArrayList<Deposits>();
	
		try {
            String queryString = "SELECT * FROM Deposits "
            		+"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Deposits depositsBean = new Deposits();
            	
            	depositsBean.setClientid(clientId);
            	depositsBean.setDepositId(resultSet.getLong("deposit_id"));
            	depositsBean.setBalance(resultSet.getDouble("balance"));
            	depositsBean.setClosingDate(resultSet.getDate("closing_date"));
            	depositsBean.setOpeningDate(resultSet.getDate("opening_date"));
            	depositsBean.setEstimatedBalance(resultSet.getDouble("estimated_balance"));
            	depositsBean.setDepositType(DepositType.valueOf(resultSet.getString("deposit_type")));
            	
            	d.add(depositsBean);
                   
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Deposits By Client Id is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Deposite can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return d;
	}

	
	

}
