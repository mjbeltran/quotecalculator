package com.mbg.zopa.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.service.impl.LendersDBImpl;

@RunWith(MockitoJUnitRunner.class)
public class LendersDBTest {

	@InjectMocks
	LendersDBImpl lendersDBImpl;

	private List<Lender> lenderList;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		lenderList = InitMocksTest.initListLenders();
	}

	@Test
	public void testGetCurrentLenders() {
		// when
		lendersDBImpl.setCurrentLenders(lenderList);
		//then
		assertEquals(lendersDBImpl.getCurrentLenders(), lenderList);
	}

	@Test
	public void testGetAmoutAvailable() {
		// when
		lendersDBImpl.setCurrentLenders(lenderList);
		//then
		assertEquals(lendersDBImpl.getAvailableLendersAmount(), 2330);
	}

}
