package it.uninsubria.paystation.web.simpleweb;

import it.uninsubria.paystation.web.WebRequest;
import it.uninsubria.paystation.web.WebResponse;

public abstract class Page {
	public abstract void handle(WebRequest webRequest, WebResponse webResponse);
}
