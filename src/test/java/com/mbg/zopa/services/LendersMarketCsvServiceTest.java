package com.mbg.zopa.services;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.service.impl.LendersMarketCsvServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LendersMarketCsvServiceTest {

	@InjectMocks
	LendersMarketCsvServiceImpl lendersMarketCsvServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = Exception.class)
	public void testMarketEmptyFileCsv() throws IOException {
		// when
		lendersMarketCsvServiceImpl.initDataMarket("src/test/resources/emptyFile.csv");
	}

	@Test
	public void testReadFileOK() throws IOException {
		// when
		final List<Lender> lenders = lendersMarketCsvServiceImpl.initDataMarket("src/test/resources/Market_Data.csv");
		// then
		assertNotNull(lenders);
	}

	@Test(expected = IOException.class)
	public void testNotFileCsv() throws IOException {
		// when
		lendersMarketCsvServiceImpl.initDataMarket("src/test/resources/aaa.csv");
	}

}
