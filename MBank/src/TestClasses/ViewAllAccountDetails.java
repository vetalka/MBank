package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewAllAccountDetails {

	
	@Test
	public void testViewAllClientDerails() throws CheckedExceptions, UncheckedRuntimeException{
		
		IAdminActions admin = new AdminAction();
		
		admin.ViewAllAccountDetails();
		
		
	}
}
