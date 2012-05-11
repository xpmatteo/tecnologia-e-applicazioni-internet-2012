package it.uninsubria.invoice.web;

import it.uninsubria.generic.web.Page;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.generic.web.WebResponse;
import it.uninsubria.invoice.domain.Receipt;
import it.uninsubria.invoice.domain.ReceiptRepository;

public class PurchaseListPage extends Page {

	private final ReceiptRepository repository;

	public PurchaseListPage(ReceiptRepository repository) {
		this.repository = repository;
	}

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		String body = "<ul>";
		for (Receipt receipt : repository.findAll()) {
			body += "<li>" + receipt.toString() + "</li>";
		}
		body += "</ul>";
		webResponse.setContentType("text/html");
		webResponse.setBody(body);
	}

}
