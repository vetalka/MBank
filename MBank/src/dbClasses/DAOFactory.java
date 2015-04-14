package dbClasses;

import Interface.IAccountsJDBCDAO;
import Interface.IActivityJDBCDAO;
import Interface.IClientJDBCDAO;
import Interface.IDepositsJDBCDAO;
import Interface.IPropertiesJDBCDAO;

//Abstract class DAO Factory

public abstract class DAOFactory {

	
	  // List of DAO types supported by the factory
	  public static final int MYSQL = 1;
	  

	  // There will be a method for each DAO that can be 
	  // created. The concrete factories will have to 
	  // implement these methods.
	  public abstract IClientJDBCDAO getClientJDBCDAO();
	  public abstract IAccountsJDBCDAO getAccountsJDBCDAO();
	  public abstract IActivityJDBCDAO getActivityJDBCDAO();
	  public abstract IDepositsJDBCDAO getDepositsJDBCDAO();
	  public abstract IPropertiesJDBCDAO getPropertiesJDBCDAO();
	  
	  public static DAOFactory getDAOFactory(
	      int whichFactory) {
	  
	    switch (whichFactory) {
	    
	      case MYSQL: 
	    	  
	    	  return new MysqlDAOFactory();
	    			  
	      default:
	    	  
	          return null;
	          
			} 
	  }
	  
	  
	}


