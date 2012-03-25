package it.uninsubria.paystation.web.hello;

import it.uninsubria.paystation.web.WebRequest;
import it.uninsubria.paystation.web.WebResponse;

public class HelloWorldPage {

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		String name = webRequest.getParameter("name");
		if (name == null) 
			name = "World";
		String body = "<h1>Hello, $name!</h1>".replace("$name", name);
		webResponse.setBody(body);
	}

}
