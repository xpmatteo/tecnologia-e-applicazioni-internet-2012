package it.uninsubria.paystation.domain;

import static org.junit.Assert.*;
import it.uninsubria.paystation.domain.IllegalCoinException;
import it.uninsubria.paystation.domain.PayStation;
import it.uninsubria.paystation.domain.Receipt;

import org.junit.Before;
import org.junit.Test;

public class PayStationTest {

	private PayStation station;
	
	@Before
	public void initialize() throws Exception {
		station = new PayStation();
	}
	
	@Test
	public void fiveCentsBuyTwoMinutes() throws Exception {
		station.addCoin(5);
		assertEquals(2, station.readDisplay());
	}
	
	@Test
	public void twentyfiveCentsBuyTenMinutes() throws Exception {
		station.addCoin(25);
		assertEquals(10, station.readDisplay());
	}

	@Test
	public void multipleCoinsAreAccumulated() throws Exception {
		PayStation station = new PayStation();
		station.addCoin(5);
		station.addCoin(10);
		assertEquals(6, station.readDisplay());
	}
	
	@Test(expected=IllegalCoinException.class)
	public void rejectsInvalidCoin() throws Exception {
		station.addCoin(11);
	}
	
	@Test
	public void onPressingBuyUserGetsReceipt() throws Exception {
		station.addCoin(25);
		Receipt receipt = station.buy();
		assertEquals(10, receipt.minutes());
	}
}
