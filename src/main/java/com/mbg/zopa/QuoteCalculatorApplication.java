package com.mbg.zopa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mbg.zopa.service.CalculQuoteService;
import com.mbg.zopa.service.LendersDB;
import com.mbg.zopa.service.LendersMarketService;
import com.mbg.zopa.service.ValidationService;

/**
 *
 * @author Manuel
 *
 */
@SpringBootApplication
public class QuoteCalculatorApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(QuoteCalculatorApplication.class);

	@Autowired
	private ValidationService validationService;

	@Autowired
	private CalculQuoteService calculQuoteService;

	@Autowired
	private LendersMarketService lendersMarketService;

	@Autowired
	private LendersDB lendersDB;

	private static final String USAGE_ERROR_MSG = "Example usage : java -jar quotecalculator.jar [market_file_path] [loan_amount]";

	public static void main(final String[] args) {
		SpringApplication.run(QuoteCalculatorApplication.class, args);

	}

	@Override
	public void run(final String... args) {

		logger.info("Application STARTED with arguments: {}", Arrays.toString(args));

		try {

			validateArguments(args);
			final String filename = args[0];
			final String strLoanAmount = args[1];
			validationService.validateAmountLoan(strLoanAmount);

			validationService.validateFileName(filename);
			lendersDB.setCurrentLenders(lendersMarketService.initDataMarket(filename));
			validationService.validateMarketHasMoneyForLoan(Integer.parseInt(strLoanAmount), lendersDB.getAvailableLendersAmount());
			System.out.println( calculQuoteService.processLoan(Integer.parseInt(strLoanAmount)));

		} catch (final Exception exp) {
			System.out.println(exp.getMessage());
		}
		logger.info("Application FINISHED");
	}

	private static void validateArguments(final String[] args) {
		if (args.length < 2) {
			System.out.println("Error: The application must take 2 arguments.");
			System.out.println(USAGE_ERROR_MSG);
			System.exit(-1);
		}
	}
	
	//TEST!2222222222222222222222222
}