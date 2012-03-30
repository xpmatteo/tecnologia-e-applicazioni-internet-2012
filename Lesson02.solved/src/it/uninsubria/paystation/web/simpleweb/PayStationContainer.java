package it.uninsubria.paystation.web.simpleweb;


import java.io.IOException;
import java.io.PrintStream;

import it.uninsubria.paystation.web.WebRequest;
import it.uninsubria.paystation.web.WebResponse;
import it.uninsubria.paystation.web.PayStationPage;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class PayStationContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		try {
			tryToHandle(request, response);
		} catch (Exception e) {
			response.setCode(500);
			printStackTrace(response, e);
		}
	}

	private void tryToHandle(Request request, Response response) {
		PayStationPage page = new PayStationPage();
		WebRequest webRequest = new RealWebRequest(request);
		WebResponse webResponse = new RealWebResponse(response);
		page.handle(webRequest, webResponse);
	}

	private void printStackTrace(Response response, Exception e){
		try {
			PrintStream stream = response.getPrintStream();
			stream.println("<pre>");
			e.printStackTrace(stream);
			stream.close();
			stream.println("</pre>");
		} catch (IOException ignored) {
		}
	}
}
