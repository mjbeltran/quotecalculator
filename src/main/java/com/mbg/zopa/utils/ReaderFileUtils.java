package com.mbg.zopa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mbg.zopa.domain.Lender;
/**
 *
 * @author Manuel
 *
 */
public class ReaderFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(ReaderFileUtils.class);

	public static List<Lender> readAndProcessFileMarkert(final String filePath)
			throws IOException {

		List<Lender> lenders;
		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
			lenders = processFile(lines);
			if (lenders.isEmpty()) {
				throw new IllegalArgumentException("No offers available to provide the loan amout in this moment");
			}
		} catch (final IOException e) {
			logger.error(String.format("Error reading CSV file - %s", e.getMessage()));
			throw e;
		}
		return lenders;
	}

	private static List<Lender> processFile(final Stream<String> lines) {
		List<Lender> listOfOffers;
		listOfOffers = lines.skip(1).map(line -> mapToLender(line)).sorted().collect(Collectors.toList());
		return listOfOffers;
	}

	private static Lender mapToLender(final String line) {
		if (!StringUtils.isEmpty(line)) {
			final String[] arrLine = line.split(",");
			return new Lender(arrLine[0], Double.parseDouble(arrLine[1]), Integer.parseInt(arrLine[2]));
		}
		return null;
	}
}
