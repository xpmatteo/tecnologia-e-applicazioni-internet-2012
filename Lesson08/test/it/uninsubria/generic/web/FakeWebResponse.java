package it.uninsubria.generic.web;



public class FakeWebResponse implements WebResponse {

	private String body;
	private String contentType;
	private Integer status;

	@Override
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
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

	public int getStatus() {
		return status;
	}

}
