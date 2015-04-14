package mainClasses;

import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import Interface.IAccountsJDBCDAO;
import Interface.IActivityJDBCDAO;
import Interface.IClientJDBCDAO;
import Interface.IDepositsJDBCDAO;
import Interface.IPropertiesJDBCDAO;
import dbClasses.DAOFactory;

public abstract class Managers {

	private static Managers managers = null; 

	protected DAOFactory Mysql = null;
	
	protected IClientJDBCDAO clientDBManager = null;
	protected IAccountsJDBCDAO accountsDBManager = null;
	protected IActivityJDBCDAO activityDBManager = null;
	protected IDepositsJDBCDAO depositsDBManager = null;
	protected IPropertiesJDBCDAO propertiesDBManager = null;
		
	private Managers(){
		
		Mysql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		clientDBManager = Mysql.getClientJDBCDAO();
		accountsDBManager = Mysql.getAccountsJDBCDAO();
		activityDBManager = Mysql.getActivityJDBCDAO();
		depositsDBManager = Mysql.getDepositsJDBCDAO();
		propertiesDBManager = Mysql.getPropertiesJDBCDAO();
		
	}
	
	public static Managers getInstance(){
		
		if(managers == null ){
			
			 managers = new Managers() {
			};
			
		}else{ 
			
			return managers; 
		}		
		
		return managers;
		
	}
	

	public void updateClient(long clientId, String newAddress, String newEmail,
			String newPhone) throws CheckedExceptions, UncheckedRuntimeException{
		clientDBManager.updateClient(clientId, newAddress, newEmail, newPhone);
	}

	public ArrayList<Deposits> getAllDeposits() throws CheckedExceptions, UncheckedRuntimeException {
		return depositsDBManager.ViewAllDepositsDetails();
		
	}

	public ArrayList<Client> getAllClients() throws CheckedExceptions, UncheckedRuntimeException{
		return clientDBManager.ViewAllClientDetails();
	}

	public Accounts getAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		return accountsDBManager.getAccountByClientId(clientId);
	}

	public void preOpenDeposite(long depositId) throws CheckedExceptions, UncheckedRuntimeException{
		depositsDBManager.preOpenDeposite(depositId);
	}

	public ArrayList<Deposits> ViewDepositsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		return depositsDBManager.ViewDepositsByClientId(clientId);
	}

	public Map<String, String> getProperties() throws CheckedExceptions, UncheckedRuntimeException{
		return propertiesDBManager.getProperties();
	}

	public void addClientAdmin(Client clientBean) throws CheckedExceptions, UncheckedRuntimeException{
		clientDBManager.addClientAdmin(clientBean);
	}

	public void addAccounts(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException {
		accountsDBManager.addAccounts(accountBean);
	}

	public void addFirstActivity(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException{
		activityDBManager.addFirstActivity(activityBean);
	}

	public Client getClientBean(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		return clientDBManager.getClientBean(clientId);
	}

	public void updateClientAdmin(Long clientId, ClientType clientType,
			String comment) throws CheckedExceptions, UncheckedRuntimeException{
		clientDBManager.updateClientAdmin(clientId, clientType, comment);
		
	}

	public void updateClientAdmin(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException{
		
		accountsDBManager.updateClientAdmin(accountBean);
	}

	public Deposits getDepositeBean(long depositeId) throws CheckedExceptions, UncheckedRuntimeException{
		return depositsDBManager.getDepositeBean(depositeId);
	}

	public void removeClient(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		clientDBManager.removeClient(clientId);
		
	}

	public void removeAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		accountsDBManager.removeAccountByClientId(clientId);
		
	}

	public void removeActivityByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		activityDBManager.removeActivityByClientId(clientId);
	}

	public ArrayList<Accounts> ViewAccountsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException {
		return accountsDBManager.ViewAccountsByClientId(clientId);
	}

	public ArrayList<Activity> ViewActivityDetailsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		return activityDBManager.ViewActivityDetailsByClientId(clientId);
	}

	public Activity getActivityBean(long id) throws CheckedExceptions, UncheckedRuntimeException{
	
		return activityDBManager.getActivityBean(id);
	}

	public Accounts getAccountsBean(long accountId) throws CheckedExceptions, UncheckedRuntimeException {
		return accountsDBManager.getAccountsBean(accountId);
	}

	public ArrayList<Client> ViewAllClientDetails() throws CheckedExceptions, UncheckedRuntimeException {
	
		return clientDBManager.ViewAllClientDetails();
	}

	public ArrayList<Accounts> ViewAllAccountDetails() throws CheckedExceptions, UncheckedRuntimeException {
		return accountsDBManager.ViewAllAccountDetails();
	}

	public ArrayList<Deposits> ViewAllDepositsDetails() throws CheckedExceptions, UncheckedRuntimeException {
		
		return depositsDBManager.ViewAllDepositsDetails();
	}

	public ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions, UncheckedRuntimeException {
		
		return activityDBManager.ViewAllActivitiesDetails();
	}

	public void updateSystemProperty(String propKey, String propValue) throws CheckedExceptions, UncheckedRuntimeException {
		
		propertiesDBManager.updateSystemProperty(propKey, propValue);
		
	}

	public void addNewAccounts(Accounts accountBean, long clientId) throws CheckedExceptions, UncheckedRuntimeException {
	
		accountsDBManager.addNewAccounts(accountBean, clientId);
		
	}

	public void CrateNewDeposit(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		activityDBManager.CrateNewDeposit(activityBean);
	}

	public void removeAccounts(long accountId) throws CheckedExceptions, UncheckedRuntimeException {
		
		accountsDBManager.removeAccounts(accountId);
		
	}

	public void CrateNewDeposit(Deposits depositsBean) throws CheckedExceptions, UncheckedRuntimeException {
		depositsDBManager.CrateNewDeposit(depositsBean);
	}

	public Map<Long, Double> getAccount() throws CheckedExceptions, UncheckedRuntimeException {
		return accountsDBManager.getAccount();
	}

	public void addActivity(Activity activityBean , long clientId) throws CheckedExceptions, UncheckedRuntimeException {
		
		activityDBManager.addActivity(activityBean , clientId);
		
	}

	public void DepositToAccount(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		accountsDBManager.DepositToAccount(accountBean);
		
	}

	public void DepositToAccount(Client clientBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		clientDBManager.DepositToAccount(clientBean);
		
	}

	public void updateAccountBean(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		accountsDBManager.updateAccountBean(accountBean);
		
	}

	public Map<String, String> LoginForClient() throws CheckedExceptions, UncheckedRuntimeException{
		
		return clientDBManager.LoginForClient();
		
	}

	public ArrayList<Client> LogIn() throws CheckedExceptions, UncheckedRuntimeException{
		
		return clientDBManager.LogIn();
		
	}
	
	
	
		
	
}
