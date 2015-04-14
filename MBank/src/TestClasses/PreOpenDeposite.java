package TestClasses;

import mainClasses.ClientAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class PreOpenDeposite {

	
	@Test
	public void testPreOpenDeposite() throws CheckedExceptions, UncheckedRuntimeException {
		
		IClientActions client = new ClientAction();
		
		client.PreOpenDeposite((long)1, (long)1);
		
	}
}
