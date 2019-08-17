package com.mbg.zopa.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mbg.zopa.domain.Lender;
import com.mbg.zopa.service.LendersDB;
/**
 *
 * @author Manuel
 *
 */
@Service
public class LendersDBImpl implements LendersDB{

	private List<Lender> currentLenders;

	@Override
	public List<Lender> getCurrentLenders() {
		return currentLenders;
	}

	@Override
	public void setCurrentLenders(final List<Lender> currentLenders) {
		this.currentLenders = currentLenders;
		currentLenders.sort(Comparator.comparing(Lender::getRate));
	}
	@Override
	public long getAvailableLendersAmount() {
		return currentLenders.stream().collect(Collectors.summingInt(Lender::getAmount));
	}
}
