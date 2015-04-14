package TestClasses;

import mainClasses.ClientAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class UpdateClient {

	
	@Test(expected=UncheckedRuntimeException.class)

	public void testUpdateClient() throws CheckedExceptions, UncheckedRuntimeException{
		
		IClientActions client = new ClientAction();
		
		client.updateClient((long) 1, "work", "", "work");
		
		
	}

}
