package dbClasses;

import Interface.IAccountsJDBCDAO;
import Interface.IActivityJDBCDAO;
import Interface.IClientJDBCDAO;
import Interface.IDepositsJDBCDAO;
import Interface.IPropertiesJDBCDAO;

public class MysqlDAOFactory extends DAOFactory {
	
	public MysqlDAOFactory(){
		
		
	}

	
	@Override
	public IClientJDBCDAO getClientJDBCDAO() {
		
		return new ClientJDBCDAO();
		
	}

	@Override
	public IAccountsJDBCDAO getAccountsJDBCDAO() {
		
		return new AccountsJDBCDAO();
	}


	@Override
	public IActivityJDBCDAO getActivityJDBCDAO() {
		
		return new ActivityJDBCDAO();
	}


	@Override
	public IDepositsJDBCDAO getDepositsJDBCDAO() {
		
		return new DepositsJDBCDAO();
	}


	@Override
	public IPropertiesJDBCDAO getPropertiesJDBCDAO() {
		
		return new PropertiesJDBCDAO();
	}

	
}
