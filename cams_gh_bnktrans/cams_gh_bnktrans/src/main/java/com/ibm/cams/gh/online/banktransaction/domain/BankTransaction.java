package com.ibm.cams.gh.online.banktransaction.domain;

import java.sql.Timestamp;

public class BankTransaction {

	private String transactionID;
	private Timestamp transactionDate;
	private String transactionType;
	private String fromAccNo;
	private String toAccNo;
	private double amount;
	private double balance;
	private String userId;

	public BankTransaction() {

	}
	
	public BankTransaction(String fromAccNo, double balance) {
		this.fromAccNo = fromAccNo;
		this.balance = balance;
	}

	public BankTransaction(String transactionID,Timestamp transactionDate,String transactionType,String fromAccNo,String toAccNo,double amount,double balance, String userId) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
        this.balance = balance;
        this.userId = userId;
	}
	
	public BankTransaction(String transactionID,Timestamp transactionDate,String transactionType,String fromAccNo,String toAccNo,double amount) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
	}
	
	public BankTransaction(String fromAccNo, double amount, double balance) {
		this.fromAccNo = fromAccNo;
		this.amount = amount;
		this.balance = balance;
	}
	

	
	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getFromAccNo() {
		return fromAccNo;
	}

	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}

	public String getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
