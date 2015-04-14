package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewClientDetails {

	
	@Test
	public void testViewAllClientDerails() throws CheckedExceptions, UncheckedRuntimeException{
		
		IAdminActions admin = new AdminAction();
		
		//ClientAction c = new ClientAction();
		//c.ViewClientDetails((long)2);
		admin.ViewClientDetails((long)3);
		
		
	}
}
