package it.uninsubria.invoice.web;


import it.uninsubria.generic.web.Page;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.generic.web.WebResponse;

public class InvoicePage extends Page {

	@Override
	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setBody("ciao");
	}
}
