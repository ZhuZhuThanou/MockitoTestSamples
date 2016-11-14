package com.thanou.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thanou.service.connector.LegacySystem;


@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

	@Mock
	private LegacySystem mockLegacySystem;
	
	@InjectMocks
	private BankService bankService;
	
	
	@Test
	public void testReadBalance() {
		/**
		 * this allows decoupling of the legacy service to the BankService object under testing
		 */
		when(mockLegacySystem.retrieveSavingBalance(any(String.class))).thenReturn("12333,33000,Saving");
		System.out.println(bankService);
		Account accountInfo = bankService.getAccountInfo("Acct12333");
	
		// ensure that retrieve balance is call only 1 time
		verify(mockLegacySystem, times(1)).retrieveSavingBalance("Acct12333");
		
		// assert the value is called
		assertEquals("12333", accountInfo.getAccountNumber());
		assertEquals("Saving", accountInfo.getAccountType());
		assertEquals("33000", accountInfo.getAmmount().toPlainString());
	}
}
