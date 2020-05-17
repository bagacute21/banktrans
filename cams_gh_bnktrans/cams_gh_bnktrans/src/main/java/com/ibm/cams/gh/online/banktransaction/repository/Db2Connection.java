package com.ibm.cams.gh.online.banktransaction.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db2Connection {
	
	String dbname = "BLUDB";
	
	public String getDbName() {
		return dbname;
	}
	
	public Connection getConnection() {
		Connection connection = null;
//		"jdbc:db2://dashdb-txn-sbox-yp-dal09-03.services.dal.bluemix.net:50001/BLUDB:user=ccf18245;password=nrf@mn5b654tlk2q;sslConnection=true;"
		String url = "jdbc:db2://";
		String host = "dashdb-txn-sbox-yp-dal09-03.services.dal.bluemix.net";
		String port = "50000";
		String username = "ccf18245";
		String password = "nrf@mn5b654tlk2q";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection(url+host+":"+port+"/"+dbname, username, password);
			System.out.println("Successfully connected to the cloud.");
		} catch(Exception e) {
			System.out.println("Error in making connection. " + e.getMessage());
			e.printStackTrace();
		}
		
		return connection;
	}
}
