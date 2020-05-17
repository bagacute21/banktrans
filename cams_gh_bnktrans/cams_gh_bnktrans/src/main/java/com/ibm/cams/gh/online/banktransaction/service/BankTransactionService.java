package com.ibm.cams.gh.online.banktransaction.service;

import java.util.List;

import com.ibm.cams.gh.online.banktransaction.domain.BankTransaction;

public interface BankTransactionService {

	public List<BankTransaction> findAllTransaction();

	public List<BankTransaction> getAllTransactionByAccountNumber(String accountNumber);
	
	public List<BankTransaction> findTransactionByType(String transactionType);
	
	public List<BankTransaction> findTransactionByID(String transactionID);
	
	public double findBalanceByAccNumber(String fromAccNo);

	public String addTransaction(BankTransaction btransaction);
	
	
//	public void update(BankTransaction withdraw);
	
   
}
