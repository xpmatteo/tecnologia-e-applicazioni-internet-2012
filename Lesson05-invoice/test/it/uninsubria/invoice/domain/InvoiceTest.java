package it.uninsubria.invoice.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvoiceTest {

	@Test
	public void totalIsZeroInitially() {
		Invoice invoice = new Invoice();
		assertEquals(0, invoice.getTotal());
	}
	
	@Test
	public void computesTotal() {
		Invoice invoice = new Invoice();
		invoice.add("Voce1", 100);
		invoice.add("Voce2", 200);
		assertEquals(300, invoice.getTotal());
	}

	@Test
	public void computesVAT() {
		Invoice invoice = new Invoice();
		invoice.add("Voce1", 1000);
		assertEquals(210, invoice.getVat());
	}
	
	@Test
	public void computesTotalWithVAT() {
		Invoice invoice = new Invoice();
		invoice.add("Voce1", 1000);
		assertEquals(1210, invoice.getTotalWithVat());
	}
	
	@Test
	public void returnsFormattedLineItems() throws Exception {
		Invoice invoice = new Invoice();
		invoice.add("Prima voce", 1);
		invoice.add("Seconda voce", 567);
		assertEquals(2, invoice.getSize());
		assertEquals("Prima voce: € 0,01", invoice.getLineItem(0).toString());
		assertEquals("Seconda voce: € 5,67", invoice.getLineItem(1).toString());
	}
}
