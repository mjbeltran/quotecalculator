package com.mbg.zopa.domain;

/**
 *
 * @author Manuel
 *
 */
public final class Lender implements Comparable<Lender> {

	private final String name;

	private final double rate;

	private int amount;

	public Lender(final String lender, final double rate, final int amount) {
		this.name = lender;
		this.rate = rate;
		this.amount = amount;
	}

	public String getLender() {
		return name;
	}

	public double getRate() {
		return rate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(final Lender offer) {
		if (getRate() == offer.getRate())
			return 0;
		else {
			return getRate() > offer.getRate() ? 1 : -1;
		}
	}

	@Override
	public String toString() {
		return "name=" + name + ',' + "rate=" + rate + ',' + "available=" + amount;
	}

}
