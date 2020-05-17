package com.ibm.cams.gh.online.banktransaction.constants;

public class BankTransactionConstants {
	
	public static final String SPACE = "";
	public static final String DOT = ".";
	
	
	public static final String SQL_INSERT_STR = "INSERT INTO TransactionTBL (transactionID, transactionDate, transactionType, fromAccNo, toAccNo, amount) VALUES (?, ?, ?, ?, ?, ?)";
	
    public static final String SQL_SELECTALL_STR = "SELECT * FROM TransactionTBL";
    
    public static final String SQL_SELECTBYACCOUNTNUMBER_STR = "SELECT * FROM TransactionTBL WHERE fromAccNo = ? OR toAccNo = ? ORDER BY transactionDate";
    
    public static final String SQL_SELECTBYTYPE_STR = "SELECT * FROM TransactionTBL WHERE transactionType = ?";
    
    public static final String SQL_SELECTBYID_STR = "SELECT * FROM TransactionTBL WHERE transactionID = ?";
    
    public static final String SQL_GET_BALANCE_BY_ACCNO_STR = "SELECT balance FROM TransactionTBL WHERE (fromAccNo = ? OR toAccNo = ?) and TRANSACTIONDATE = (SELECT MAX(TRANSACTIONDATE) FROM transactiontbl)";
    
    public static final String SQL_GET_TIMESTAMP = "SELECT current timestamp FROM sysibm.sysdummy1";
}
