package com.mbg.zopa.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.mbg.zopa.service.impl.CalculQuoteServiceImpl;
import com.mbg.zopa.service.impl.LendersDBImpl;
import com.mbg.zopa.services.InitMocksTest;

@RunWith(MockitoJUnitRunner.class)
public class LoanRepaymentTest {

	@InjectMocks
	CalculQuoteServiceImpl calculQuoteService;

	@Mock
	LendersDBImpl lendersDB;

	@Before
	public void init() {
		ReflectionTestUtils.setField(calculQuoteService, "numPaymentsYear", 12);
		ReflectionTestUtils.setField(calculQuoteService, "loanDuration", 3);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testExampleProvided() {
		// Given
		//		final LoanData loan = new LoanData(1000);
		final List<Lender> lenderList = InitMocksTest.initListLenders();
		when(lendersDB.getCurrentLenders()).thenReturn(lenderList);
		// test
		final String loanQuote = calculQuoteService.processLoan(1000);
		//then
		assertNotNull(loanQuote.toString());
	}

	@Test
	public void testToStringLender() {
		// Given
		final Lender lender = new Lender("Test",0.02,890);
		final String strLender = lender.toString();
		//then
		assertEquals("name=Test,rate=0.02,available=890", strLender);
	}
}
