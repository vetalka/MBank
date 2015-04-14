package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewAllClientDetails {
	
	@Test
	public void testViewAllClientDerails() throws CheckedExceptions, UncheckedRuntimeException{
		
		IAdminActions admin = new AdminAction();
		
		System.out.println(admin.ViewAllClientDetails());
		
		
	}

}
