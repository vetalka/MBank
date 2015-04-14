package TestClasses;

import java.util.ArrayList;
import mainClasses.Client;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import MBank.MBank;

public class TestMBan {

	public static void main(String[] args)  {
		
		
	   MBank m = MBank.getInstance();
	   
	
	   
	try {
		
		for(Client c : m.ViewAllClientDetails()){
			
			System.out.println(c);
		}
	} catch (CheckedExceptions e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UncheckedRuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
