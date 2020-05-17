package com.ibm.cams.gh.online.banktransaction.utils;

import java.util.Date;
import java.sql.Timestamp;

public class BankTransactionUtility {
	
	
	public static boolean strIsEmpty(String str) {
		
		if(str != null  && !str.equalsIgnoreCase("")) {
			return true;
		}
		
		return false;
	}
	
	
	 
	
	public Timestamp getTimeStamp() {
	 
		 Date date= new Date();
		 
		 long time = date.getTime();
		     System.out.println("Time in Milliseconds: " + time);
		 
		 Timestamp ts = new Timestamp(time);
	 
		 return ts;
	}

}
