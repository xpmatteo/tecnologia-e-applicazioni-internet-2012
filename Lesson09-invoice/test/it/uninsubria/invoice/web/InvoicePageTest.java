package it.uninsubria.invoice.web;

import static org.junit.Assert.*;
import it.uninsubria.generic.web.FakeWebRequest;
import it.uninsubria.generic.web.FakeWebResponse;
import it.uninsubria.generic.web.HttpMethod;
import it.uninsubria.generic.web.WebResponse;
import it.uninsubria.invoice.domain.InvoiceRepository;
import it.uninsubria.invoice.domain.LineItem;
import it.uninsubria.invoice.domain.Money;

import org.junit.Test;
import org.simpleframework.http.Request;

public class InvoicePageTest {
	InvoiceRepository repository = new FakeInvoiceRepository();
	InvoicePage page = new InvoicePage(repository);
	FakeWebRequest request = new FakeWebRequest();
	FakeWebResponse response = new FakeWebResponse();

	@Test
	public void addLineItemToInvoice() throws Exception {
		request.setPath("/line-items");
		request.setMethod(HttpMethod.POST);
		request.setParameter("description", "qualunque");
		request.setParameter("amountInCents", "1234");		
		
		page.handle(request, response);
		
		assertEquals(1, repository.size());
		assertEquals(new LineItem("qualunque", new Money("12,34")), repository.all().get(0));
		assertEquals(200, response.getStatus());
	}

	@Test
	public void getAllLineItems() throws Exception {
		LineItem item0 = new LineItem("one", new Money(123));
		LineItem item1 = new LineItem("two", new Money(456));
		repository.addLineItem(item0);
		repository.addLineItem(item1);
		request.setPath("/line-items");
		request.setMethod(HttpMethod.GET);
		
		page.handle(request, response);
		
		assertEquals(200, response.getStatus());
		assertEquals("application/json", response.getContentType());
		String expected = "" +
				"[\n" +
				"  " + item0.toJson() + "\n" +
				",  " + item1.toJson() + "\n" +
				"]";
		assertEquals(expected , response.getBody());
	}
}
