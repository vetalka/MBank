package mainClasses;

import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public class Login {
	
	public static Map <String , String> prop = null;
	//public static Map<String, String> client1 = null;
	public static ArrayList<Client> client1 = null;

	
	private String name;
    private String password;
    
	
   
    
    public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public static Action Login1(String name , String password) throws CheckedExceptions, UncheckedRuntimeException{
		
		prop = Managers.getInstance().getProperties();
	   // client1 = Managers.getInstance().LoginForClient();

		client1 = Managers.getInstance().LogIn();
		
		if (name.equals(prop.get("admin_username")) && password.equals(prop.get("admin_password"))){
			
			System.out.println("admin");
			return  new AdminAction();
			
		}else 
			
			for(Client client : client1){
			
			    if(name.equals(client.getClientName())&& password.equals(client.getPassword())){
			
			    	System.out.println("client");
			    	return new ClientAction();
			    	
			}
			}
		
	
		
		System.out.println("not");
		throw new  UncheckedRuntimeException("Name or Paswword is not correct Try Again!");
		
	}
   

}
