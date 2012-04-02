package it.uninsubria.paystation.web;

import it.uninsubria.paystation.web.WebResponse;

public class FakeWebResponse implements WebResponse {

	private String body;
	private String contentType;

	@Override
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public void setContentType(String type) {
		this.contentType = type;
	}
	
	public String getBody() {
		return body;
	}

	public String getContentType() {
		return contentType;
	}

}
