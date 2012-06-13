package it.uninsubria.invoice.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineItemTest {

	@Test
	public void constructorAndGetters() throws Exception {
		LineItem lineItem = new LineItem("descrizione", new Money("10,00"));
		assertEquals("descrizione", lineItem.description());
		assertEquals(new Money("10,00"), lineItem.amount());
	}
	
	@Test
	public void returnsJsonRepresentation() throws Exception {
		LineItem lineItem = new LineItem("bla bla", new Money("1,00"));
		String expected = "{ \"description\": \"bla bla\", \"amountInCents\": 100 }";
		assertEquals(expected, lineItem.toJson());
	}
}
