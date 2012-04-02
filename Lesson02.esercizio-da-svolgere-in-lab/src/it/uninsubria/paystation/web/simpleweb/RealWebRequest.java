package it.uninsubria.paystation.web.simpleweb;

import it.uninsubria.paystation.web.WebRequest;

import java.io.IOException;

import org.simpleframework.http.Request;

public class RealWebRequest implements WebRequest {

	private final Request request;

	public RealWebRequest(Request request) {
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		try {
			return request.getParameter(name);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
