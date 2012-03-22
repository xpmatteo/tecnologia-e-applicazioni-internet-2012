package it.uninsubria.paystation.web;

import java.io.IOException;
import java.io.PrintStream;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class PayStationContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		try {
			response.set("Content-Type", "text/html");
			PrintStream body = response.getPrintStream();
			body.println("Hello!");
			body.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
