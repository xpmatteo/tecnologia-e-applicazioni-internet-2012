package it.uninsubria.paystation.web.simpleweb;

import it.uninsubria.paystation.web.WebResponse;

import java.io.IOException;
import java.io.PrintStream;

import org.simpleframework.http.Response;

public class RealWebResponse implements WebResponse {

	private final Response response;

	public RealWebResponse(Response response) {
		this.response = response;
	}

	@Override
	public void setBody(String body) {
		try {
			PrintStream stream = response.getPrintStream();
			stream.print(body);
			stream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setContentType(String type) {
		response.set("Content-Type", type);
	}

}
