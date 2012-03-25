package it.uninsubria.paystation.web.hello;

import static org.junit.Assert.assertEquals;
import it.uninsubria.paystation.web.FakeWebRequest;
import it.uninsubria.paystation.web.FakeWebResponse;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldPageTest {

	private FakeWebResponse webResponse;
	private FakeWebRequest webRequest;
	private HelloWorldPage page;

	@Before
	public void setUp() throws Exception {
		webRequest = new FakeWebRequest();
		webResponse = new FakeWebResponse();
		page = new HelloWorldPage();
	}
	
	@Test
	public void returnsHelloWorld() {
		page.handle(webRequest, webResponse);		
		assertEquals("<h1>Hello, World!</h1>", webResponse.getBody());
	}

	@Test
	public void setsContentTypeToHtml() {
		page.handle(webRequest, webResponse);		
		assertEquals("text/html", webResponse.getContentType());
	}

	@Test
	public void saysHelloToTheGivenName() {
		webRequest.setParameter("name", "Matteo");
		page.handle(webRequest, webResponse);		
		assertEquals("<h1>Hello, Matteo!</h1>", webResponse.getBody());
	}
}
