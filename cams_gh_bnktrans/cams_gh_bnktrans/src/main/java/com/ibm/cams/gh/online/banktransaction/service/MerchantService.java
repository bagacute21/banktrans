package com.ibm.cams.gh.online.banktransaction.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MerchantService {
	
//	public static void main(String[] args) {
//		MerchantService ms = new MerchantService();
//		System.out.println(ms.getMerchantTotalByAccountNumber("MR111001"));
//	}
	
	public double getMerchantTotalByAccountNumber(String accountNumber) {
		// call merchant end-point
		URL url;
		BufferedReader in = null;
		HttpURLConnection con = null;
		double merchantTotal = 0;
		try {
			url = new URL("http://13.90.22.89:8080/v1/merchant/rest-api/transaction/AccountNumber/"+accountNumber);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");		
			con.setRequestProperty("Content-Type", "application/json");
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				JsonParser jsonParser = new JsonParser();
				JsonArray merchantJsonArray = jsonParser.parse(inputLine).getAsJsonArray();
				for(int i = 0; i < merchantJsonArray.size(); i++) {
					merchantTotal += merchantJsonArray.get(i).getAsJsonObject().get("amount").getAsDouble();
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		
		return merchantTotal;
	}
}
