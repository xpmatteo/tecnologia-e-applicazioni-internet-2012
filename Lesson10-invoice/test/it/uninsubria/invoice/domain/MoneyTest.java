package it.uninsubria.invoice.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTest {

	@Test
	public void returnsAmountInCents() throws Exception {
		assertAmountInCents(1, "0,01");
		assertAmountInCents(10, "0,10");
		assertAmountInCents(1020, "10,20");
		assertAmountInCents(1000, "10");
	}
	
	@Test
	public void canConstructFromCents() throws Exception {
		assertEquals(new Money("1,23"), new Money(123));
	}

	private void assertAmountInCents(int expected, String money) {
		assertEquals(expected, new Money(money).toCents());
	}

}
