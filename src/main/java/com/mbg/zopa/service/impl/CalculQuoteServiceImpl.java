package com.mbg.zopa.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.domain.LoanData;
import com.mbg.zopa.service.CalculQuoteService;
import com.mbg.zopa.service.LendersDB;
/**
 *
 * @author Manuel
 *
 */
@Service
public class CalculQuoteServiceImpl implements CalculQuoteService {

	private static final int VALUE_1 = 1;

	private static final double INITIAL_VALUE_0 = 0.0;

	@Value("${payments.year}")
	private int numPaymentsYear;

	@Value("${loan.duration}")
	private int loanDuration;

	@Autowired
	private LendersDB lendersDB;

	private int loanAmount;

	private static final String POUND_SYMBOL = "£";

	@Override
	public String processLoan(final int loanAmount) {

		this.loanAmount = loanAmount;

		final double rateAverage = calculateAverageRate();
		final double monthlyRepayment = monthlyRepaymentCalculation(rateAverage);
		final LoanData loanData = new LoanData(this.loanAmount, rateAverage, monthlyRepayment,
				totalRepaymentRepayment(monthlyRepayment));
		return displayResult(loanData);
	}


	private double calculateAverageRate() {
		double totalInterest = INITIAL_VALUE_0;
		int remainingLoanAmount = this.loanAmount;

		for (final Lender lender : lendersDB.getCurrentLenders()) {
			final int borrowedAmount = Math.min(remainingLoanAmount, lender.getAmount());
			totalInterest += borrowedAmount * lender.getRate();
			lender.setAmount(lender.getAmount() - Math.min(remainingLoanAmount, lender.getAmount()));
			remainingLoanAmount -= borrowedAmount;

			if (remainingLoanAmount == 0) {
				break;
			}
		}
		return totalInterest;
	}


	private double monthlyRepaymentCalculation(final double rateAverage) {
		// Formula is used to calculate the payments on a loan
		final double annualInterestRate = rateAverage / this.loanAmount;
		final double monthlyInterestRate = Math.pow(1 + annualInterestRate, 1.0 / this.numPaymentsYear) - VALUE_1;
		return monthlyInterestRate * this.loanAmount
				/ (1 - Math.pow(1 + monthlyInterestRate, -(this.loanDuration * this.numPaymentsYear)));
	}


	private double totalRepaymentRepayment(final double monthlyRepayment) {

		return monthlyRepayment * getLoanDuration() * getNumPaymentsYear();
	}

	public int getNumPaymentsYear() {
		return numPaymentsYear;
	}

	public void setNumPaymentsYear(final int numPaymentsYear) {
		this.numPaymentsYear = numPaymentsYear;
	}

	public int getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(final int loanDuration) {
		this.loanDuration = loanDuration;
	}


	private String displayResult(final LoanData loan) {
		final String newline = System.lineSeparator();
		final StringBuilder str = new StringBuilder();
		str.append(String.format(Locale.UK, "Requested amount: " + POUND_SYMBOL + "%d", loan.getLoanAmount())).append(newline)
		.append(String.format(Locale.UK, "Annual Interest Rate: %.1f%%", loan.getRate() * 100 / loan.getLoanAmount()))
		.append(newline)
		.append(String.format(Locale.UK, "Monthly repayment: " + POUND_SYMBOL + "%.2f", loan.getMonthlyPayment()))
		.append(newline)
		.append(String.format(Locale.UK, "Total repayment: " + POUND_SYMBOL + "%.2f", loan.getAnnualPayment()));
		return str.toString();
	}

}