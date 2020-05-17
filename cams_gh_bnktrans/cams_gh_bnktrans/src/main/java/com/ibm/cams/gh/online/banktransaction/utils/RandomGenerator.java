package com.ibm.cams.gh.online.banktransaction.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
	     
	    public static final String character = "ABCDEFGHIJKMNOPQRSTUVWXYZ" ;
	    public static int number = 0;
	    public static void main(String[] args) {
	    	RandomGenerator rg = new RandomGenerator();
	        System.out.println("Userid = " + rg.generateUserid());
	        System.out.println("Accountnumber" + rg.generateAccountnumber());
	         
	    }
	    
	    public String generateTransID() {
	    	return "TRN" + getRandomNum() + getRandomNum();
	    }
	    
	    public String generateUserid() {
	    	return getRandomAlpha() + getRandomNum() + getRandomAlpha();
	    }
	    
	    public String generateAccountnumber() {
	    	return getRandomNum2()+""+getRandomNum2()+""+getRandomNum2()+""+getRandomNum2()+""+getRandomNum2();
	    }
	    
	    private static String getRandomAlpha() {
	        Random r = new Random();
	        int offset = r.nextInt(character.length());
	        return character.substring(offset, offset+3);
	    }
	    public static int getRandomNum()
		{
	    	number = (int)((Math.random() * 900)+100);
			return number;
		}
	    
	    public static int getRandomNum2() {
	    	number = (int)((Math.random() * 90)+10);
			return number;
		}
	 
	}