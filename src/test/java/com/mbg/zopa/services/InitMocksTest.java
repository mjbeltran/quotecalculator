package com.mbg.zopa.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.mbg.zopa.domain.Lender;

public class InitMocksTest {

	public static List<Lender> initListLenders() {
		List<Lender> inversors = Arrays.asList(new Lender("Bob", 0.075, 640), 
												new Lender("Jane", 0.069, 480),
												new Lender("Fred", 0.071, 520), 
												new Lender("Mary", 0.104, 170), 
												new Lender("John", 0.081, 320),
												new Lender("Dave", 0.074, 140), 
												new Lender("Angela", 0.071, 60));
		inversors.sort(Comparator.comparing(Lender::getRate));
		return inversors;
	}

}
