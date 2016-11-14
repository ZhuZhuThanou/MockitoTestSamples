package com.thanou.service;

import java.math.BigDecimal;

import com.thanou.service.connector.LegacySystem;

public class BankService {
	private LegacySystem legacySystem;

	public BankService() {
		legacySystem = new LegacySystem();
	}
	public Account getAccountInfo(String accountNumber) {
		// call legacy system 
		String retreiveSavingBalance = legacySystem.retrieveSavingBalance(accountNumber);
		
		// transform data for legacy system to the new system
		String[] dataArray = retreiveSavingBalance.split(",");
		Account account = new Account();
		account.setAccountNumber(dataArray[0]);
		account.setAmmount(new BigDecimal(dataArray[1]));
		account.setAccountType(dataArray[2]);
		return account;
	}
}
