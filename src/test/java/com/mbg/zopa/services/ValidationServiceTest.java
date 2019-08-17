package com.mbg.zopa.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.mbg.zopa.service.impl.ValidationServiceImpl;
import com.mbg.zopa.utils.Messages;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource
public class ValidationServiceTest {

	@InjectMocks
	ValidationServiceImpl validationServiceImpl;

	@Mock
	Messages messages;

	@Before
	public void init() {
		ReflectionTestUtils.setField(validationServiceImpl, "loanAmtMult", 100);
		ReflectionTestUtils.setField(validationServiceImpl, "loanAmtMin", 1000);
		ReflectionTestUtils.setField(validationServiceImpl, "loanAmtMax", 15000);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidateLoanAmoutOk() {
		// when
		validationServiceImpl.validateAmountLoan("1600");
		// then
		assert true;
	}

	@Test(expected = Exception.class)
	public void testValidateLoanAmoutNotMult100() {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateAmountLoan("5789");
		// then
		Assert.fail("An exception should be thrown");
	}

	@Test(expected = Exception.class)
	public void testLowerValueAmount() {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateAmountLoan("500");

	}

	@Test(expected = Exception.class)
	public void emptyFileNameTest()  {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateFileName("");
	}

	@Test(expected = Exception.class)
	public void testBadExtensionFileNameTest() {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateFileName("hello.txt");
	}

	@Test(expected = Exception.class)
	public void testfileNotExist() {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateFileName("hello.csv");
	}

	@Test(expected = Exception.class)
	public void testMarketAmountNotAvailable()  {
		// when
		when(messages.get(anyString())).thenReturn("test");
		// test
		validationServiceImpl.validateMarketHasMoneyForLoan(5000, 2000);
	}

}
