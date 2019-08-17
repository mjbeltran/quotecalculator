package com.mbg.zopa;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class QuoteCalculatorApplicationIntegTest {

	private static final String LOAN_AMOUNT_1000 = "1000";
	private static final String SRC_TEST_RESOURCES_MARKET_DATA_CSV = "src/test/resources/Market_Data.csv";
	private static String pathFile;
	private static String amount;

	@BeforeClass
	public static void arguments() {
		pathFile = SRC_TEST_RESOURCES_MARKET_DATA_CSV;
		amount = LOAN_AMOUNT_1000;
	}

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		pathFile = SRC_TEST_RESOURCES_MARKET_DATA_CSV;
		amount = LOAN_AMOUNT_1000;
		System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() {
		System.setOut(System.out);
	}

	@Test
	public void testCallMainAppArgsOK() {
		QuoteCalculatorApplication.main(new String[] { pathFile, amount });
		assertTrue(out.toString().contains("Requested amount: £1000") && out.toString().contains("Rate: 7.0%")
				&& out.toString().contains("Monthly repayment: £30.78")
				&& out.toString().contains("Total repayment: £1108.10"));

	}

	@Test
	public void testAmountStringInvalid() {
		QuoteCalculatorApplication.main(new String[] { pathFile, "aa" });
		assertTrue(out.toString().contains("[loan_amount] must be an integer value"));
	}

	@Test
	public void testAmountInvalidMore15000() {
		QuoteCalculatorApplication.main(new String[] { pathFile, "20000" });
		assertTrue(out.toString().contains("[loan_amount] must be between 1000 and 15000"));
	}

	@Test
	public void testAmountInvalidLess1000() {
		QuoteCalculatorApplication.main(new String[] { pathFile, "200" });
		assertTrue(out.toString().contains("[loan_amount] must be between 1000 and 15000"));
	}

	@Test
	public void testAmountInvalidNotMult100() {
		QuoteCalculatorApplication.main(new String[] { pathFile, "7656" });
		assertTrue(out.toString().contains("[loan_amount] value has to be multiple of 100"));
	}

	@Test
	public void testInvalidMarketFile() {
		QuoteCalculatorApplication.main(new String[] { "xxxx.csv", LOAN_AMOUNT_1000 });
		assertTrue(out.toString().contains("[market_file_path] is not correct"));
	}

	@Test
	public void testEmptyMarketFile() {
		QuoteCalculatorApplication.main(new String[] { "src/test/resources/emptyFile.csv", LOAN_AMOUNT_1000 });
		assertTrue(out.toString().contains("No offers available to provide the loan amout in this moment"));
	}

	@Test
	public void testInvalidMarketFileExtension() {
		QuoteCalculatorApplication.main(new String[] { "src/test/resources/emptyFile.txt", LOAN_AMOUNT_1000 });
		assertTrue(out.toString().contains("[market_file_path] extension should be .csv"));
	}
}
