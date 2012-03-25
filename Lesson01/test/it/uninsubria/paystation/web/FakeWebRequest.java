package it.uninsubria.paystation.web;

import it.uninsubria.paystation.web.WebRequest;

public class FakeWebRequest implements WebRequest {

	private String value;

	public void setParameter(String name, String value) {
		this.value = value;
	}

	@Override
	public String getParameter(String name) {
		return value;
	}

}
