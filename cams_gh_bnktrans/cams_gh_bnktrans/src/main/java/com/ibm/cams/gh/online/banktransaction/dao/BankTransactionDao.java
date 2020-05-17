package com.ibm.cams.gh.online.banktransaction.dao;

import java.util.List;

import com.ibm.cams.gh.online.banktransaction.domain.BankTransaction;

public interface BankTransactionDao {

	public List<BankTransaction> findAllTransaction();
	
	public List<BankTransaction> getTransactionsByAccountNumber(String accoutNumber);

	public void add(BankTransaction btransaction);
	
	public void withdraw(BankTransaction btransaction);
	
	public void deposit(BankTransaction btransaction);
	
	public void transfer(BankTransaction btransaction);
	
	public List<BankTransaction> findTransactionByType(String transactionType);
	
	public List<BankTransaction> findTransactionByID(String transactionID);
	
	public List<BankTransaction> findBalanceByAccNumber(String fromAccNo);
	
	public double getBalance(String accountNumber);
	
	
}
