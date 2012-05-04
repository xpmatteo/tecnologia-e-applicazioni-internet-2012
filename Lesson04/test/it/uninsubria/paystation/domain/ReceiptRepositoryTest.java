package it.uninsubria.paystation.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ReceiptRepositoryTest {
	InMemoryReceiptRepository repository = new InMemoryReceiptRepository();

	@Test
	public void isEmptyInitially() {
		assertEquals(new Integer(0), repository.size());
	}
	
	@Test
	public void remembersOneReceipt() throws Exception {
		Receipt receipt = new Receipt(123);
		
		repository.notifyPurchase(receipt);
		
		List<Receipt> all = repository.findAll();
		assertEquals(1, all.size());
		assertEquals(receipt, all.get(0));
	}
	
	@Test
	public void remembersTwoReceipts() throws Exception {
		Receipt first = new Receipt(111);
		Receipt second = new Receipt(222);
		
		repository.notifyPurchase(first);
		repository.notifyPurchase(second);
		
		List<Receipt> all = repository.findAll();
		assertEquals(2, all.size());
		assertEquals(first, all.get(0));
		assertEquals(second, all.get(1));
	}
}
