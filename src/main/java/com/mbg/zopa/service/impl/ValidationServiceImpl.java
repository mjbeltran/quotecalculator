package com.mbg.zopa.service.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mbg.zopa.service.ValidationService;
import com.mbg.zopa.utils.Messages;

/**
 *
 * @author Manuel
 *
 */
@Service
public class ValidationServiceImpl implements ValidationService {

	private static final String FILE_EXTENSION = ".csv";

	@Value("${mult.loan.amount}")
	private int loanAmtMult;

	@Value("${min.loan.amoun}")
	private int loanAmtMin;

	@Value("${max.loan.amoun}")
	private int loanAmtMax;

	@Autowired
	private Messages messages;

	@Override
	public void validateAmountLoan(final String loanAmount) {

		if (!StringUtils.isNumeric(loanAmount)) {
			throw new IllegalArgumentException(messages.get("amount.not.integer"));
		}
		final long amount = Long.parseLong(loanAmount);

		if (amount < this.loanAmtMin || amount > loanAmtMax) {
			final String errMsg = String.format(messages.get("amount.between.values"), String.valueOf(this.loanAmtMin),
					String.valueOf(this.loanAmtMax));
			throw new IllegalArgumentException(errMsg);
		}

		if (amount % 100 != 0) {
			throw new IllegalArgumentException(messages.get("amount.multiple.100"));
		}

	}

	@Override
	public void validateFileName(final String filename) {

		if (StringUtils.isEmpty(filename)) {
			final String message = String.format(messages.get("market.file.empty"));
			throw new IllegalArgumentException(message);
		}
		if (!filename.endsWith(FILE_EXTENSION)) {
			final String message = String.format(messages.get("market.file.csv"), FILE_EXTENSION);
			throw new IllegalArgumentException(message);
		}

		final File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			throw new IllegalArgumentException(messages.get("market.file.path.invalid"));
		}

	}

	@Override
	public void validateMarketHasMoneyForLoan(final Integer requestedAmount, final long availableLendersAmount) {
		if (requestedAmount > availableLendersAmount) {
			final String message = String.format(messages.get("market.amount.not.suff"), requestedAmount,
					availableLendersAmount);
			throw new IllegalArgumentException(message);
		}

	}

}
