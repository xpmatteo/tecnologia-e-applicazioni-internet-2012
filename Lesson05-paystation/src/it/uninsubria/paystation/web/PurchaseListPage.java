package it.uninsubria.paystation.web;

import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.domain.ReceiptRepository;
import it.uninsubria.paystation.web.simpleweb.Page;

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
