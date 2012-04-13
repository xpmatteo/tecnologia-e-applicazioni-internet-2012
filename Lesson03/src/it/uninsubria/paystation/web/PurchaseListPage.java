package it.uninsubria.paystation.web;

import it.uninsubria.paystation.domain.ReceiptRepository;
import it.uninsubria.paystation.web.simpleweb.Page;

public class PurchaseListPage extends Page {

	private final ReceiptRepository repository;

	public PurchaseListPage(ReceiptRepository repository) {
		this.repository = repository;
	}

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		String body = "<ul>";
		for (int i=0; i<repository.size(); i++) {
			body += "<li>" + repository.get(i).toString() + "</li>";
		}
		body += "</ul>";
		webResponse.setContentType("text/html");
		webResponse.setBody(body);
	}

}
