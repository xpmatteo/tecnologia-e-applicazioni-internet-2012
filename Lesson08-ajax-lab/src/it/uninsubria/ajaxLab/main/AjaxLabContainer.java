package it.uninsubria.ajaxLab.main;

import it.uninsubria.generic.web.RealWebRequest;
import it.uninsubria.generic.web.RealWebResponse;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.generic.web.WebResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class AjaxLabContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		try {
			tryToHandle(request, response);
		} catch (Exception e) {
			response.setCode(500);
			printStackTrace(response, e);
		}
	}

	private void tryToHandle(Request request, Response response) throws SQLException {
		WebRequest webRequest = new RealWebRequest(request);
		WebResponse webResponse = new RealWebResponse(response);
		sleep(1000);
		if (webRequest.getPath().equals("/ajax/plainText")) {
			webResponse.setContentType("text/plain");
			webResponse.setBody("Hello!!!");
		} else if (webRequest.getPath().equals("/ajax/json")) {
			webResponse.setContentType("application/json");
			webResponse.setBody("{ 'foo': 'bar' }" );
		} else if (webRequest.getPath().equals("/ajax/sleepForever")) {
			webResponse.setContentType("text/plain");
			sleep(100*1000);
			webResponse.setBody("Fine sleep");
		} else if (webRequest.getPath().equals("/ajax/500")) {
			throw new RuntimeException("boom!");
		} else if (webRequest.getPath().equals("/ajax/javascript")) {
			webResponse.setContentType("application/javascript");
			webResponse.setBody("alert('Sono javascript');");
		} else {
			webResponse.setContentType("text/html");
			webResponse.setBody("<ul><li>Uno</li><li>Due</li></ul>");
		}
	}

	private void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {}
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
