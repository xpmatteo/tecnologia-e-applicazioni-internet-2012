package it.uninsubria.paystation.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import it.uninsubria.paystation.domain.PayStation;

import org.junit.Before;
import org.junit.Test;

public class PayStationPageTest {

	private FakeWebResponse webResponse;
	private FakeWebRequest webRequest;
	private PayStationPage page;

	@Before
	public void setUp() throws Exception {
		initPage();
	}

	private void initPage() {
		webRequest = new FakeWebRequest();
		webResponse = new FakeWebResponse();
		page = new PayStationPage(new PayStation());
	}
	
	@Test
	public void setsContentTypeToHtml() {
		page.handle(webRequest, webResponse);		
		assertEquals("text/html", webResponse.getContentType());
	}

	@Test
	public void minutesAtStartIsZero() {
		page.handle(webRequest, webResponse);
		assertEquals("0", timeBoughtInMinutes());
	}

	@Test
	public void fiveCentBuysTwoMinutes() {
		insertCoin("coin05");
		page.handle(webRequest, webResponse);
		assertEquals("2", timeBoughtInMinutes());
	}

	@Test
	public void fiveThenTenCentBuysSixMinutes() {
		insertCoin("coin05");
		page.handle(webRequest, webResponse);
		assertEquals("2", timeBoughtInMinutes());
		String state = conversationState();
		assertNotNull(state);
		
		initPage();

		webRequest.setParameter("state", state);
		insertCoin("coin10");
		page.handle(webRequest, webResponse);
		assertEquals("6", timeBoughtInMinutes());	
	}

	@Test
	public void buyTicket() throws Exception {
		insertCoin("coin10");
		page.handle(webRequest, webResponse);
		String state = conversationState();
		
		initPage();
		
		webRequest.setParameter("state", state);		
		webRequest.setParameter("buy", "non importa");
		page.handle(webRequest, webResponse);
		assertEquals("Ticket per 4 minuti", webResponse.getBody());
	}

	private void insertCoin(String name) {
		webRequest.setParameter(name, "qualsiasi");		
	}

	private String conversationState() {
		// <input type="hidden" name="state" value="qualcosa" />
		return stringBetween("<input type=\"hidden\" name=\"state\" value=\"", "\"");
	}

	private String timeBoughtInMinutes() {
		return stringBetween("Minuti acquistati: ", "</p>");
	}

	private String stringBetween(String startString, String endString) {
		String body = webResponse.getBody();
		int start = body.indexOf(startString);
		if (-1 == start) {
			fail("Conversation state not found");
		}
		int end = body.indexOf(endString, start + startString.length());
		return body.substring(start + startString.length(), end);
	}

}
