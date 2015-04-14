package TestClasses;

import mainClasses.Accounts;
import mainClasses.AdminAction;
import mainClasses.Client;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class AddClientTest {


	@Test(expected=UncheckedRuntimeException.class)
	public void testAddClient() throws CheckedExceptions, UncheckedRuntimeException {
		
		
		
		IAdminActions admin = new AdminAction();
		
		Client c = new Client( "Ilia", "123", "natania", "www.www", "0543162272", "ASDF");
		Accounts a = new Accounts(10);
		
		admin.addClientAdmin(c, a );
	
		
	}

}
