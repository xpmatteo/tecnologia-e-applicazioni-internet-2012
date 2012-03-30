package it.uninsubria.paystation.web;

import static org.junit.Assert.*;
import it.uninsubria.paystation.web.FakeWebRequest;
import it.uninsubria.paystation.web.FakeWebResponse;

import org.junit.Before;
import org.junit.Test;

public class PayStationPageTest {

	private FakeWebResponse webResponse;
	private FakeWebRequest webRequest;
	private PayStationPage page;

	@Before
	public void setUp() throws Exception {
		webRequest = new FakeWebRequest();
		webResponse = new FakeWebResponse();
		page = new PayStationPage();
		page.cancel();
	}
	
	@Test
	public void setsContentTypeToHtml() {
		page.handle(webRequest, webResponse);		
		assertEquals("text/html", webResponse.getContentType());
	}
	
	@Test
	public void initialTimeIsZero() {
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 0", contentsOfDisplay());
	}

	@Test
	public void buy2minutes() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 2", contentsOfDisplay());
	}
	
	@Test
	public void buy2minutesThen2More() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 2", contentsOfDisplay());
		
		page = new PayStationPage();
		webRequest.setParameter("coin05", "anything");
		webRequest.setParameter("state", contentsOfState());
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 4", contentsOfDisplay());
	}
	
	@Test
	public void buyTwoMinutesThenCancel() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 2", contentsOfDisplay());

		webRequest.setParameter("cancel", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 0", contentsOfDisplay());
	}
	
	@Test
	public void insertTenCentCoin() throws Exception {
		webRequest.setParameter("coin10", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 4", contentsOfDisplay());
	}

	@Test
	public void insert25CentCoin() throws Exception {
		webRequest.setParameter("coin25", "anything");
		page.handle(webRequest, webResponse);
		assertEquals("Minuti acquistati: 10", contentsOfDisplay());
	}
	
	@Test
	public void returnsState() throws Exception {
		page.handle(webRequest, webResponse);
		assertEquals("0", contentsOfState());		
	}
	
	private String contentsOfDisplay() {
		String body = webResponse.getBody();
		int start = body.indexOf("Minuti acquistati");
		int end = body.indexOf("</p>");
		return body.substring(start, end);
	}
	
	private String contentsOfState() {
		try {
			String body = webResponse.getBody();
			String startTag = "<input type='hidden' name='state' value='";
			int start = body.indexOf(startTag) + startTag.length();
			int end = body.indexOf("'", start);
			return body.substring(start, end);
		} catch (StringIndexOutOfBoundsException e) {
			fail("Campo state non trovato");
			return "";
		}
	}
}
