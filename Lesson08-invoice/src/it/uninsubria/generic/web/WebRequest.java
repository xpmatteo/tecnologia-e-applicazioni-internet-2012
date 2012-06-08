package it.uninsubria.generic.web;

public interface WebRequest {

	String getParameter(String name);
	String getPath();
	HttpMethod getMethod();
}
