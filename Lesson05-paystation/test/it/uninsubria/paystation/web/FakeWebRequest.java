package it.uninsubria.paystation.web;

import java.util.HashMap;
import java.util.Map;

public class FakeWebRequest implements WebRequest {

	private Map<String, String> values = new HashMap<String, String>();
	private String path;

	public void setParameter(String name, String value) {
		values.put(name, value);
	}

	@Override
	public String getParameter(String name) {
		return values.get(name);
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return path;
	}

}
