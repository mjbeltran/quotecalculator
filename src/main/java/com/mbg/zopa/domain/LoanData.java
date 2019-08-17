package com.mbg.zopa.domain;

/**
 *
 * @author Manuel
 *
 */
public  final class LoanData {

	private final int loanAmount;

	private final double rate;

	private final double monthlyPayment;

	private final double annualPayment;


	public LoanData( final int loanAmt,final double rate,final double monthlyPayment,final double annualPayment) {
		this.loanAmount = loanAmt;
		this.rate = rate;
		this.monthlyPayment = monthlyPayment;
		this.annualPayment = annualPayment;
	}

	public double getRate() {
		return rate;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public double getAnnualPayment() {
		return annualPayment;
	}
}
