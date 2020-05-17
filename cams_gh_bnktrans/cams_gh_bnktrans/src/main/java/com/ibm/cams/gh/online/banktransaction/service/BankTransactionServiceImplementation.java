package com.ibm.cams.gh.online.banktransaction.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.ibm.cams.gh.online.banktransaction.dao.BankTransactionDao;
import com.ibm.cams.gh.online.banktransaction.dao.BankTransactionDaoImpl;
import com.ibm.cams.gh.online.banktransaction.domain.BankTransaction;

public class BankTransactionServiceImplementation implements BankTransactionService{
	
	BankTransactionDao banktransactionDao;
	
	public BankTransactionServiceImplementation() {
		banktransactionDao = new BankTransactionDaoImpl();
	}
	
	@Override
	public List<BankTransaction> findAllTransaction(){
		return banktransactionDao.findAllTransaction();
	}
	
	@Override
	public List<BankTransaction> findTransactionByType(String transactionType){
		return banktransactionDao.findTransactionByType(transactionType);
	}
	
	@Override
	public List<BankTransaction> findTransactionByID(String transactionID){
		return banktransactionDao.findTransactionByID(transactionID);
	}
	
	@Override
	public double findBalanceByAccNumber(String fromAccNo){
		return banktransactionDao.getBalance(fromAccNo);
	}
	

	public String addTransaction(BankTransaction btransaction) {
		String out = "";
		double balance, newBalance;
		try {
			String transactionType = btransaction.getTransactionType();
			if(transactionType.equalsIgnoreCase("transfer")) {
				// check balance
				balance = banktransactionDao.getBalance(btransaction.getFromAccNo());
				if(balance >= btransaction.getAmount()) {
					banktransactionDao.transfer(btransaction);
					
					out += "Transaction was successful.";
				} else {
					out += "Insufficient balance. Current Balance: Php " + balance;
				}
			} else if(transactionType.equalsIgnoreCase("deposit")) {
				balance = banktransactionDao.getBalance(btransaction.getToAccNo());
				System.out.println("Current balance of " + btransaction.getToAccNo() + " = " + balance);
				banktransactionDao.deposit(btransaction);				
				out += "Transaction was successful.";
			} else if(transactionType.equalsIgnoreCase("withdraw")) {
				// check balance
				balance = banktransactionDao.getBalance(btransaction.getFromAccNo());
				System.out.println("Withdraw. CurBalance = " + balance);
				if(balance >= btransaction.getAmount()) {
					banktransactionDao.withdraw(btransaction);
					out += "Transaction was successful.";
				} else {
					out += "Insufficient balance. Current Balance of "+btransaction.getFromAccNo()+": Php " + balance;
				}
			} else {
				out = "Invalid transaction type.";
			}
			
			
			System.out.println(btransaction);
			
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
		return out;
	}

	@Override
	public List<BankTransaction> getAllTransactionByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		
		List<BankTransaction> transactions = banktransactionDao.getTransactionsByAccountNumber(accountNumber);
		
		return transactions;
	}

}
