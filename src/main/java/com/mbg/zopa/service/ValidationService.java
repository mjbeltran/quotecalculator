package com.mbg.zopa.service;

/**
 *
 * @author Manuel
 *
 */
public interface ValidationService {

	void validateAmountLoan(String strLoanAmount);

	void validateFileName(String filename);

	void validateMarketHasMoneyForLoan(Integer requestedAmount, long availableLendersAmount);
}
