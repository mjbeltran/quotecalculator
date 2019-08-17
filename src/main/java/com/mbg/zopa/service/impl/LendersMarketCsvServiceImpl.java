package com.mbg.zopa.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.service.LendersMarketService;
import com.mbg.zopa.utils.ReaderFileUtils;

/**
 *
 * @author Manuel
 *
 */
@Service
public class LendersMarketCsvServiceImpl implements LendersMarketService {

	@Override
	public List<Lender> initDataMarket(final String filePath) throws IOException {

		return ReaderFileUtils.readAndProcessFileMarkert(filePath);
	}
}
