package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewActivitiesDetails {

	
	@Test
	public void testViewAllActivityDerails() throws CheckedExceptions, UncheckedRuntimeException{
		
		IAdminActions admin = new AdminAction();
		
		admin.ViewActivitiesDetails((long)1);
		
		
	}
}
