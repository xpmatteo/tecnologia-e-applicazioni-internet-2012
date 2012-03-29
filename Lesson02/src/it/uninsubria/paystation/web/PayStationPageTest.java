package it.uninsubria.paystation.web;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;
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
	public void initialTimeIsZero() {
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 0"));
	}

	@Test
	public void setsContentTypeToHtml() {
		page.handle(webRequest, webResponse);		
		assertEquals("text/html", webResponse.getContentType());
	}
	
	@Test
	public void buy2minutes() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 2"));
	}
	
	@Test
	public void buy2minutesThen2More() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 2"));
		
		page = new PayStationPage();
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 4"));
	}
	
	@Test
	public void buyTwoMinutesThenCancel() throws Exception {
		webRequest.setParameter("coin05", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 2"));

		webRequest.setParameter("cancel", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 0"));
	}
	
	@Test
	public void insertTenCentCoin() throws Exception {
		webRequest.setParameter("coin10", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 4"));		
	}

	@Test
	public void insert25CentCoin() throws Exception {
		webRequest.setParameter("coin25", "anything");
		page.handle(webRequest, webResponse);
		assertThat(webResponse.getBody(), containsString("Minuti acquistati: 10"));		
	}
}
