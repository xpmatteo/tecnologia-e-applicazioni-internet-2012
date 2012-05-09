package it.uninsubria.paystation.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ReceiptTest {

	@Test
	public void returnsStringRepresentation() {
		Receipt receipt = new Receipt(10);
		assertEquals("Ticket for 10 minutes", receipt.toString());
	}
	
	@Test
	public void implementsEquals() throws Exception {
		Receipt receipt = new Receipt(111);
		Receipt same = new Receipt(111);
		Receipt different = new Receipt(222);
		assertNotEquals(receipt, null);
		assertNotEquals(receipt, "123");
		assertEquals(receipt, receipt);
		assertEquals(receipt, same);
		assertNotEquals(receipt, different);
	}

	private void assertNotEquals(Object a, Object b) {
		String message = String.format("Expected <%s> NOT to be equal to <%s>", a, b);
		assertFalse(message, a.equals(b));
	}

}
