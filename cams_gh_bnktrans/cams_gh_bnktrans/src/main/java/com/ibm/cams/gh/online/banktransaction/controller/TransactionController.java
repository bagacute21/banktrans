package com.ibm.cams.gh.online.banktransaction.controller;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.devs.rest.devsrest.domain.Developer;
//import com.devs.rest.devsrest.exceptions.NoListFoundException;
//import com.devs.rest.devsrest.service.DeveloperService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.ibm.cams.gh.online.banktransaction.domain.BankTransaction;
import com.ibm.cams.gh.online.banktransaction.service.BankTransactionService;
//import com.ibm.cams.gh.online.banktransaction.service.BankTransactionServiceImpl;
import com.ibm.cams.gh.online.banktransaction.service.BankTransactionServiceImplementation;



@RestController
@EnableSwagger2
@EnableDiscoveryClient


public class TransactionController {
	private BankTransactionService transactionService;
	
	public TransactionController() {
		transactionService = new BankTransactionServiceImplementation();
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/rest/transactions/allTransactions")
	public @ResponseBody List<BankTransaction> getAllTransactions(){

		try {
			List<BankTransaction> banktransactions;

			banktransactions = transactionService.findAllTransaction();

			return banktransactions;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/rest/transactions/{accountNumber}")
	public @ResponseBody List<BankTransaction> getTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber){

		try {
			List<BankTransaction> banktransactions;

			banktransactions = transactionService.getAllTransactionByAccountNumber(accountNumber);

			return banktransactions;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/rest/transactions/type/{transactionType}")
	public @ResponseBody List<BankTransaction> getTransactionByType(@PathVariable("transactionType") String transactionType) {
		try {
			List<BankTransaction> banktransactions;

			banktransactions = transactionService.findTransactionByType(transactionType);

			return banktransactions;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/rest/transactions/id/{transactionID}")
	public @ResponseBody List<BankTransaction> getTransactionByID(@PathVariable("transactionID") String transactionID) {
		try {
			List<BankTransaction> banktransactions;

			banktransactions = transactionService.findTransactionByID(transactionID);

			return banktransactions;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/rest/currentbalance/{accountnumber}")
	public @ResponseBody double getCurrentBalance(@PathVariable("accountnumber") String accountnumber) {
		try {

			double balance = transactionService.findBalanceByAccNumber(accountnumber);

			return balance;
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
	@PostMapping
	@RequestMapping(method=RequestMethod.POST, produces="application/json", value="/rest/transactions/add")
	public Response add(BankTransaction btransaction){
		String out = transactionService.addTransaction(btransaction);
		return Response.status(200).entity(out).build();
	}
	
	
}