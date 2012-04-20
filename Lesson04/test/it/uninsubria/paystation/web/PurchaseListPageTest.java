package it.uninsubria.paystation.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.domain.ReceiptRepository;

import org.junit.Test;

public class PurchaseListPageTest {
	FakeWebRequest webRequest = new FakeWebRequest();
	FakeWebResponse webResponse = new FakeWebResponse();
	ReceiptRepository repository = new ReceiptRepository();
	PurchaseListPage page = new PurchaseListPage(repository);

	@Test
	public void containsZeroReceipts() {
		page.handle(webRequest, webResponse);		
		assertFalse(webResponse.getBody().contains("Ticket per"));
	}
	
	@Test
	public void containsOneReceipt() throws Exception {
		Receipt first = new Receipt(1);
		repository.notifyPurchase(first);
		
		page.handle(webRequest, webResponse);
		
		String body = webResponse.getBody();
		assertTrue(body, body.contains("Ticket per 1 minuti"));
	}

	@Test
	public void containsTwoReceipts() throws Exception {
		Receipt first = new Receipt(111);
		repository.notifyPurchase(first);
		
		Receipt second = new Receipt(222);
		repository.notifyPurchase(second);
		
		page.handle(webRequest, webResponse);
		String body = webResponse.getBody();
		assertTrue(body, body.contains("Ticket per 111 minuti"));
		assertTrue(body, body.contains("Ticket per 222 minuti"));
	}


}
