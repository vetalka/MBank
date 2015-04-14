package Interface;

import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import mainClasses.Accounts;

public interface IAccountsJDBCDAO {

	public abstract void addAccounts(Accounts accounts) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void removeAccounts(long accountId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void DepositToAccount(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException;

	public Map<Long, Double> getAccount() throws CheckedExceptions, UncheckedRuntimeException;

	public Map<Long, String> getAccount1() throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void updateClientAdmin(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void ViewAccountDetails(long accountId) throws CheckedExceptions, UncheckedRuntimeException;

	public ArrayList <Accounts> ViewAllAccountDetails() throws CheckedExceptions, UncheckedRuntimeException;
	
	public Accounts getAccountsBean(long accountId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void updateAccountBean(Accounts accountBean) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void addNewAccounts(Accounts accountBean, long clientId) throws CheckedExceptions, UncheckedRuntimeException;
	
	public ArrayList<Accounts> ViewAccountsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void removeAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public Accounts getAccountByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException;
	
}