package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewAccountDetails {

	
	@Test
	public void testViewClientDerails() throws CheckedExceptions, UncheckedRuntimeException{
		
		IAdminActions admin = new AdminAction();
		
		admin.ViewAccountDetails((long)3);
		
		
	}
}
