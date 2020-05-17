package com.ibm.cams.gh.online.banktransaction.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class PhoneService {
	
	public double getPhoneTotalByAccountNumber(String accountNumber) {
		// call phoneservice endpoint
		URL url;
		HttpURLConnection con = null;
		double phoneTotal = 0;
		try {
			url = new URL("http://3.84.33.3:8080/phone-service/phoneservice/getByAccountNumber/?acctNumber="+accountNumber);
			
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			int status = con.getResponseCode();
			
			System.out.println("STATUS: " + Integer.toString(status));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

			    JsonParser jsonParser = new JsonParser();
			    JsonArray phoneJsonArray = jsonParser.parse(inputLine).getAsJsonArray();
			    System.out.println(phoneJsonArray);

			    for(int i=0; i<phoneJsonArray.size(); i++) {
			    	 phoneTotal += phoneJsonArray.get(i).getAsJsonObject().get("amount").getAsDouble();
			    }
				
				System.out.println(phoneTotal);
			}

			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.disconnect();
		}
		
		return phoneTotal;
	}
	

}
