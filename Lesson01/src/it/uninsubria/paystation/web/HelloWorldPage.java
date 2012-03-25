package it.uninsubria.paystation.web;

public class HelloWorldPage {

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		String name = webRequest.getParameter("name");
		if (name == null) 
			name = "World";
		webResponse.setBody("<h1>Hello, World!</h1>");
	}

}
