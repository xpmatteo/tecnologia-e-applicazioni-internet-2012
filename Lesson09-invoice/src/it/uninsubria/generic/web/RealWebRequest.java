package it.uninsubria.generic.web;


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

	@Override
	public String getPath() {
		return this.request.getPath().getPath();
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.valueOf(request.getMethod().toUpperCase());
	}

}
