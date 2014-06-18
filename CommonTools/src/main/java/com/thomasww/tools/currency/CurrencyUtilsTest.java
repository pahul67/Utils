/**
 * Created on Jul 27, 2012.
 * Copyright ©DecodeApps.
 */
package com.thomasww.tools.currency;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class CurrencyUtilsTest {

	/**
	 * Test method for {@link com.thomasww.tools.currency.CurrencyUtils#getRateToEuro(java.lang.String)}.
	 */
	@Test
	public void testGetRateToEuro() {
		CurrencyUtils curUtils = new CurrencyUtils();
		assertTrue(curUtils.getRateToEuro("USD") > 0.0);
		assertTrue(curUtils.getRateToEuro("AUD") > 0.0);
	}

}
