package it.uninsubria.generic.web;

public interface WebResponse {
	void setBody(String body);
	void setContentType(String type);
	void setStatus(int status);
}
