package it.uninsubria.paystation.web;

import java.util.HashMap;
import java.util.Map;

import it.uninsubria.paystation.web.WebRequest;

public class FakeWebRequest implements WebRequest {

	private Map<String, String> values = new HashMap<String, String>();

	public void setParameter(String name, String value) {
		values.put(name, value);
	}

	@Override
	public String getParameter(String name) {
		return values.get(name);
	}

}
