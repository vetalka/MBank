package dbClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mainClasses.Activity;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IActivityJDBCDAO;

public class ActivityJDBCDAO implements IActivityJDBCDAO {
	
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
	public void addFirstActivity(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		try {
			
     	   long id = PKGenerator.getNextPk("Activity", "id");
     	   long client_id = ((PKGenerator.getNextPk("Clients", "client_id"))-1);
     	  
            String queryString = "INSERT INTO Activity (id , client_id , amount , "
            		+ "activity_date , commission , description ) "
            		+ "VALUES(?,?,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            ptmt.setLong(2, client_id);
            ptmt.setDouble(3, activityBean.getAmount());
            ptmt.setDate(4, activityBean.getActivityDate());
            ptmt.setDouble(5, activityBean.getCommission());
            ptmt.setString(6, activityBean.getDescription());
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
            } catch (SQLException e) {
            	throw new CheckedExceptions("Add first Activity is not working!");
            } finally {
                   try {
                       if (ptmt != null)
                              ptmt.close();
                       if (connection != null)
                              connection.close();
                  } catch (SQLException e) {
               	       throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
                  } catch (Exception e) {
                	  throw new UncheckedRuntimeException("You have no connection!");
                  }

    }
	}

	@Override
	public void CrateNewDeposit(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException {
		try {
			
	     	   long id = PKGenerator.getNextPk("Activity", "id");
	     	  
	            String queryString = "INSERT INTO Activity (id , client_id , amount , "
	            		+ "activity_date , commission , description ) "
	            		+ "VALUES(?,?,?,?,?,?)";
	            connection = getConnection();
	            ptmt = connection.prepareStatement(queryString);
	            ptmt.setLong(1, id);
	            ptmt.setLong(2, activityBean.getClientid());
	            ptmt.setDouble(3, activityBean.getAmount());
	            ptmt.setDate(4, activityBean.getActivityDate());
	            ptmt.setDouble(5, activityBean.getCommission());
	            ptmt.setString(6, activityBean.getDescription());
	            ptmt.executeUpdate();
	            System.out.println("Data Added Successfully");
	            } catch (SQLException e) {
	            	throw new CheckedExceptions("Crate New Deposit  is not working!");
	            } finally {
	                   try {
	                       if (ptmt != null)
	                              ptmt.close();
	                       if (connection != null)
	                              connection.close();
	                  } catch (SQLException e) {
	                	  throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
	                  } catch (Exception e) {
	                	  throw new UncheckedRuntimeException("You have no connection!");
	                  }

	    }
		
	}

	@Override
	public void ViewActivitiesDetails(long clientId) throws CheckedExceptions, UncheckedRuntimeException{

   	 try {
            String queryString = "SELECT * FROM Activity "
           		 +"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
                   System.out.println("Id:" + resultSet.getLong("id")
                   		+" , Amount:" + resultSet.getString("amount")
                                   + " , Activity Date: " + resultSet.getString("activity_date")
                                   + " , Commission: " + resultSet.getString("commission") + " , Description: "
                                   + resultSet.getString("description"));
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Activities Details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }

		
	}

	@Override
	public ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Activity> a = new ArrayList<Activity>();
		
		try {
            String queryString = "SELECT * FROM Activity ";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Activity activityBean = new Activity();
            	
            	activityBean.setClientid(resultSet.getLong("client_id"));
            	activityBean.setId(resultSet.getLong("id"));
            	activityBean.setActivityDate(resultSet.getDate("activity_date"));
            	activityBean.setAmount(resultSet.getDouble("amount"));
            	activityBean.setCommission(resultSet.getDouble("commission"));
            	activityBean.setDescription(resultSet.getString("description"));
            	
            	a.add(activityBean);
            	
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View All Activities Details is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return a;

		
	}

	@Override
	public Activity getActivityBean(long id) throws CheckedExceptions, UncheckedRuntimeException{
		
		Activity activityBean = new Activity();
		
		 try {
			    String queryString = "SELECT * FROM Activity "
        		                 +"WHERE id ="+id;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
	            while (resultSet.next()){
	            	
	            	activityBean.setClientid(resultSet.getLong("client_id"));
	            	activityBean.setId(id);
	            	activityBean.setActivityDate(resultSet.getDate("activity_date"));
	            	activityBean.setAmount(resultSet.getDouble("amount"));
	            	activityBean.setCommission(resultSet.getDouble("commission"));
	            	activityBean.setDescription(resultSet.getString("description"));
	            	
	                }
	        } catch (SQLException e) {
	        	throw new CheckedExceptions("Get Activities Bean is not working!");
	        } finally {
	                try {
	                        if (resultSet != null)
	                                resultSet.close();
	                        if (ptmt != null)
	                                ptmt.close();
	                        if (connection != null)
	                                connection.close();
	                } catch (SQLException e) {
	                	throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
	                } catch (Exception e) {
	                	throw new UncheckedRuntimeException("You have no connection!");
	                }

	        }

		return activityBean;
	}

	@Override
	public ArrayList<Activity> ViewActivityDetailsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException {
		
        ArrayList<Activity> a = new ArrayList<Activity>();
		
		try {
            String queryString = "SELECT * FROM Activity "
            		+"WHERE client_id ="+clientId;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) { 
       		
            	Activity activityBean = new Activity();
            	
            	activityBean.setClientid(resultSet.getLong("client_id"));
            	activityBean.setId(resultSet.getLong("id"));
            	activityBean.setActivityDate(resultSet.getDate("activity_date"));
            	activityBean.setAmount(resultSet.getDouble("amount"));
            	activityBean.setCommission(resultSet.getDouble("commission"));
            	activityBean.setDescription(resultSet.getString("description"));
            	
            	a.add(activityBean);
            	
            }
        } catch (SQLException e) {
        	throw new CheckedExceptions("View Activities Details By Client Id is not working!");
        } finally {
                try {
                        if (resultSet != null)
                                resultSet.close();
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                	throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
                } catch (Exception e) {
                	throw new UncheckedRuntimeException("You have no connection!");
                }
        }
		return a;

	}

	@Override
	public void removeActivityByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		 try {
             String queryString = "DELETE FROM Activity "
           		  +"WHERE client_id ="+clientId;
             connection = getConnection();
             ptmt = connection.prepareStatement(queryString);
             ptmt.executeUpdate();
             System.out.println("Data deleted Successfully");
     } catch (SQLException e) {
    	 throw new CheckedExceptions("Remove Activity By Client Id is not working!");
     } finally {
             try {
                     if (ptmt != null)
                             ptmt.close();
                     if (connection != null)
                             connection.close();
             } catch (SQLException e) {
            	 throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
             } catch (Exception e) {
            	 throw new UncheckedRuntimeException("You have no connection!");
             }

     }
		
	}

	@Override
	public void addActivity(Activity activityBean, long clientId) throws CheckedExceptions, UncheckedRuntimeException {
		
		try {
			
	     	   long id = PKGenerator.getNextPk("Activity", "id");
	     	  
	            String queryString = "INSERT INTO Activity (id , client_id , amount , "
	            		+ "activity_date , commission , description ) "
	            		+ "VALUES(?,?,?,?,?,?)";
	            connection = getConnection();
	            ptmt = connection.prepareStatement(queryString);
	            ptmt.setLong(1, id);
	            ptmt.setLong(2, clientId);
	            ptmt.setDouble(3, activityBean.getAmount());
	            ptmt.setDate(4, activityBean.getActivityDate());
	            ptmt.setDouble(5, activityBean.getCommission());
	            ptmt.setString(6, activityBean.getDescription());
	            ptmt.executeUpdate();
	            System.out.println("Data Added Successfully");
	            } catch (SQLException e) {
	            	throw new CheckedExceptions("Add Activity is not working!");
	            } finally {
	                   try {
	                       if (ptmt != null)
	                              ptmt.close();
	                       if (connection != null)
	                              connection.close();
	                  } catch (SQLException e) {
	                	  throw new UncheckedRuntimeException("Your parametrs in Activity can't be null!");
	                  } catch (Exception e) {
	                	  throw new UncheckedRuntimeException("You have no connection!");
	                  }

	    }
	}

}
