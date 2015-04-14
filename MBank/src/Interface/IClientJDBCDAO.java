package Interface;

import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import mainClasses.Client;
import mainClasses.ClientType;

public interface IClientJDBCDAO {

	public abstract void addClientAdmin(Client client) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void updateClient(long clientId, String newAddress,
			String newEmail, String newPhone) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void removeClient(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void updateClientAdmin(long clientId , ClientType clientType, String comment) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void DepositToAccount(Client clientBean) throws CheckedExceptions, UncheckedRuntimeException;

	public Map<Long, String> getClient() throws CheckedExceptions, UncheckedRuntimeException;
	
	public abstract void ViewClientDetails(long clientId) throws CheckedExceptions, UncheckedRuntimeException;
	
	public Client getClientBean(long clientId ) throws CheckedExceptions, UncheckedRuntimeException;

	public ArrayList <Client>  ViewAllClientDetails() throws CheckedExceptions, UncheckedRuntimeException;

	public Map<String, String> LoginForClient() throws CheckedExceptions, UncheckedRuntimeException;

	public ArrayList<Client> LogIn() throws CheckedExceptions, UncheckedRuntimeException;

}