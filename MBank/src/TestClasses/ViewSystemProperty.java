package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewSystemProperty {
	
	@Test
	public void testViewSystemProperty() throws CheckedExceptions, UncheckedRuntimeException{
		
		//ClientAction client = new ClientAction();
		IAdminActions admin = new AdminAction();
		
		admin.ViewSystemPropertyAdmin();
		//client.ViewSystemProperty();
		
		
	}

}
