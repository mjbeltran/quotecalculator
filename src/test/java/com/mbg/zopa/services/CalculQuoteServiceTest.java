package com.mbg.zopa.services;

import static org.junit.Assert.assertTrue;
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

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.service.impl.CalculQuoteServiceImpl;
import com.mbg.zopa.service.impl.LendersDBImpl;

@RunWith(MockitoJUnitRunner.class)
public class CalculQuoteServiceTest {

	@InjectMocks
	CalculQuoteServiceImpl calculQuoteServiceImpl;

	@Mock
	LendersDBImpl lendersDB;

	@Before
	public void init() {
		ReflectionTestUtils.setField(calculQuoteServiceImpl, "numPaymentsYear", 12);
		ReflectionTestUtils.setField(calculQuoteServiceImpl, "loanDuration", 3);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testProcessLoan() {

		// Give
		final List<Lender> lenderList = InitMocksTest.initListLenders();
		when(lendersDB.getCurrentLenders()).thenReturn(lenderList);
		// test
		final String result = calculQuoteServiceImpl.processLoan(1000);
		// then
		assertTrue(result.contains("30.78"));
		assertTrue(result.contains("1108.10"));
		assertTrue(result.contains("1108.10"));

	}
}
