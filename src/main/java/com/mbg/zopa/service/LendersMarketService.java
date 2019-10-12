package com.mbg.zopa.service;

import java.io.IOException;
import java.util.List;

import com.mbg.zopa.domain.Lender;

/**
 *
 * @author Manuel
 *
 */
public interface LendersMarketService {

	public List<Lender> initDataMarket(String filePath) throws IOException;
}


//TESTTTTTTT