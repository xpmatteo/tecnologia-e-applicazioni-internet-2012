package it.uninsubria.paystation.web;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class HelloWorldContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		HelloWorldPage page = new HelloWorldPage();
		WebRequest webRequest = null;
		WebResponse webResponse = new RealWebResponse(response);
		page.handle(webRequest, webResponse);
	}


}
