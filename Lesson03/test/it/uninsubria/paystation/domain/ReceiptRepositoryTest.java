package it.uninsubria.paystation.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReceiptRepositoryTest {
	ReceiptRepository repository = new ReceiptRepository();

	@Test
	public void isEmptyInitially() {
		assertEquals(new Integer(0), repository.size());
	}
	
	@Test
	public void remembersOneReceipt() throws Exception {
		Receipt receipt = new Receipt(123);
		
		repository.notifyPurchase(receipt);
		
		assertEquals(new Integer(1), repository.size());
		assertEquals(receipt, repository.get(0));
	}
	
	@Test
	public void remembersTwoReceipts() throws Exception {
		Receipt first = new Receipt(111);
		Receipt second = new Receipt(222);
		
		repository.notifyPurchase(first);
		repository.notifyPurchase(second);
		
		assertEquals(new Integer(2), repository.size());
		assertEquals(first, repository.get(0));
		assertEquals(second, repository.get(1));
	}
}
