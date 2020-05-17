package com.ibm.cams.gh.online.banktransaction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cams.gh.online.banktransaction.constants.BankTransactionConstants;
import com.ibm.cams.gh.online.banktransaction.domain.BankTransaction;
import com.ibm.cams.gh.online.banktransaction.repository.Db2Connection;
import com.ibm.cams.gh.online.banktransaction.service.MerchantService;
import com.ibm.cams.gh.online.banktransaction.service.PhoneService;
import com.ibm.cams.gh.online.banktransaction.utils.BankTransactionUtility;
import com.ibm.cams.gh.online.banktransaction.utils.RandomGenerator;

public class BankTransactionDaoImpl implements BankTransactionDao{

	Db2Connection dbConnection;
	BankTransactionUtility bankUtil;
	RandomGenerator randomGen;
	MerchantService merchantService;
	PhoneService phoneService;

	
	public BankTransactionDaoImpl() {
		dbConnection = new Db2Connection();
		bankUtil = new BankTransactionUtility();
		randomGen = new RandomGenerator();
	}
	
	public List<BankTransaction> findAllTransaction() {
		
		List<BankTransaction> banktransaction1 = new ArrayList<>();

		
		try (Connection conn1 = dbConnection.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(BankTransactionConstants.SQL_SELECTALL_STR)) {
			ResultSet results1 = ps1.executeQuery();
			System.out.println("Came here.");
			while(results1.next()) {
				
				BankTransaction banktransaction2 = new BankTransaction
						(results1.getString("transactionID"),
						 results1.getTimestamp("transactionDate"),
						 results1.getString("transactionType"),
						 results1.getString("fromAccNo"),
						 results1.getString("toAccNo"),
						 results1.getDouble("amount"),
						 results1.getDouble("balance"),
						 results1.getString("userId")
						);
				
				banktransaction1.add(banktransaction2);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return banktransaction1;
	}
	
public List<BankTransaction> getTransactionsByAccountNumber(String accountNumber) {
		
		List<BankTransaction> banktransaction1 = new ArrayList<>();

		
		try (Connection conn1 = dbConnection.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(BankTransactionConstants.SQL_SELECTBYACCOUNTNUMBER_STR)) {
			ps1.setString(1, accountNumber);
			ps1.setString(2, accountNumber);
			ResultSet results1 = ps1.executeQuery();
			System.out.println("Came here.");
			while(results1.next()) {
				
				BankTransaction banktransaction2 = new BankTransaction
						(results1.getString("transactionID"),
						 results1.getTimestamp("transactionDate"),
						 results1.getString("transactionType"),
						 results1.getString("fromAccNo"),
						 results1.getString("toAccNo"),
						 results1.getDouble("amount"),
						 results1.getDouble("balance"),
						 results1.getString("userId")
						);
				
				banktransaction1.add(banktransaction2);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return banktransaction1;
	}
	
	public Timestamp getTimestamp() {
		Timestamp timestamp = null;
		try (Connection conn1 = dbConnection.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(BankTransactionConstants.SQL_GET_TIMESTAMP)) {
			ResultSet results1 = ps1.executeQuery();
			
			timestamp = results1.getTimestamp(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return timestamp;
	}
	
	public List<BankTransaction> findTransactionByType(String transactionType){
		
		List<BankTransaction> banktransaction1 = new ArrayList<>();

		
		try (Connection conn1 = dbConnection.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(BankTransactionConstants.SQL_SELECTBYTYPE_STR)) {
			ps1.setString(1, transactionType);
			ResultSet results1 = ps1.executeQuery();
			System.out.println("Came here.");
			while(results1.next()) {
				
				BankTransaction banktransaction2 = new BankTransaction
						(results1.getString("transactionID"),
						 results1.getTimestamp("transactionDate"),
						 results1.getString("transactionType"),
						 results1.getString("fromAccNo"),
						 results1.getString("toAccNo"),
						 results1.getDouble("amount"),
						 results1.getDouble("balance"),
						 results1.getString("userId")
						);
				
				banktransaction1.add(banktransaction2);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return banktransaction1;
	}
	
	
	public List<BankTransaction> findTransactionByID(String transactionID){
		
		List<BankTransaction> banktransaction1 = new ArrayList<>();
	
		
		try (Connection conn1 = dbConnection.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(BankTransactionConstants.SQL_SELECTBYID_STR)) {
			ps1.setString(1, transactionID);
			ResultSet results1 = ps1.executeQuery();
			System.out.println("Came here.");
			while(results1.next()) {
				
				BankTransaction banktransaction2 = new BankTransaction
						(results1.getString("transactionID"),
						 results1.getTimestamp("transactionDate"),
						 results1.getString("transactionType"),
						 results1.getString("fromAccNo"),
						 results1.getString("toAccNo"),
						 results1.getDouble("amount"),
						 results1.getDouble("balance"),
						 results1.getString("userId")
						);
				
				banktransaction1.add(banktransaction2);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return banktransaction1;
	}

	@Override
	public void deposit(BankTransaction btransaction) {
		
		try(Connection conn1 = dbConnection.getConnection(); PreparedStatement ps = conn1.prepareStatement(BankTransactionConstants.SQL_INSERT_STR)) {
			
			ps.setString(1, randomGen.generateTransID());
			ps.setTimestamp(2, bankUtil.getTimeStamp());
			ps.setString(3, btransaction.getTransactionType());
			ps.setString(4, "");
			ps.setString(5, btransaction.getToAccNo());
			ps.setDouble(6, btransaction.getAmount());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
	@Override
	public void withdraw(BankTransaction btransaction) {
		try(Connection con = dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(BankTransactionConstants.SQL_INSERT_STR)){
			
			ps.setString(1, randomGen.generateTransID());
			ps.setTimestamp(2, bankUtil.getTimeStamp());
			ps.setString(3, btransaction.getTransactionType());
			ps.setString(4, btransaction.getFromAccNo());
			ps.setString(5, null);
			ps.setDouble(6, btransaction.getAmount());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void transfer(BankTransaction btransaction) {
		try(Connection con = dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(BankTransactionConstants.SQL_INSERT_STR)){
			
			ps.setString(1, randomGen.generateTransID());
			ps.setTimestamp(2, bankUtil.getTimeStamp());
			ps.setString(3, btransaction.getTransactionType());
			ps.setString(4, btransaction.getFromAccNo());
			ps.setString(5, btransaction.getToAccNo());
			ps.setDouble(6, btransaction.getAmount());
		
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public double getBalance(String accountNumber) {
		double balance = 0;
		
		List<BankTransaction> banktransaction1 = getTransactionsByAccountNumber(accountNumber);
		
		for(BankTransaction trans : banktransaction1) {
			if(trans.getTransactionType().equalsIgnoreCase("deposit")) {
				balance += trans.getAmount();
			} else if(trans.getTransactionType().equalsIgnoreCase("transfer")) {
				if(accountNumber.equalsIgnoreCase(trans.getFromAccNo())) {
					balance -= trans.getAmount();
				} else if(accountNumber.equalsIgnoreCase(trans.getToAccNo())) {
					balance += trans.getAmount();
				}
			} else if(trans.getTransactionType().equalsIgnoreCase("withdraw")) {
				balance -= trans.getAmount();
			}
		}
		
		merchantService = new MerchantService();
		phoneService = new PhoneService();
		balance -= merchantService.getMerchantTotalByAccountNumber(accountNumber);
		balance -= phoneService.getPhoneTotalByAccountNumber(accountNumber);
		
		return balance;
	}

	@Override
	public void add(BankTransaction btransaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BankTransaction> findBalanceByAccNumber(String fromAccNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void update(BankTransaction withdraw) {
//		try(Connection con = dbConnection.getConnection();
//				PreparedStatement ps = con.prepareStatement(BankTransactionConstants.SQL_UPDATEBALWITHDRAW_STR)){
//			
//			ps.setDouble(1, withdraw.getAmount()); 
//			ps.setString(3, withdraw.getFromAccNo());
//			
////			ps.setDouble(1, withdraw.getBalance());
//			
//			ps.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
}
