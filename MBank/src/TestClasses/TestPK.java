package TestClasses;

import java.sql.SQLException;

import dbClasses.PKGenerator;

public class TestPK {

	public static void main(String[] args) throws SQLException {
		System.out.println(PKGenerator.getNextPk("Clients", "client_id"));

	}

}
