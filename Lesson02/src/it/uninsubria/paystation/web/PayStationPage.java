package it.uninsubria.paystation.web;

import it.uninsubria.paystation.domain.IllegalCoinException;
import it.uninsubria.paystation.domain.PayStation;
import it.uninsubria.paystation.web.WebRequest;
import it.uninsubria.paystation.web.WebResponse;

public class PayStationPage {
	private static final String TEMPLATE = ""
			+ "<div style=\"border:1px solid black; margin: 1em; padding: 1em; width: 20em\">\n"
			+ "<p style=\"border: 1px solid gray; padding: 0.2em\">Minuti acquistati: $minutes</p>\n"
			+ "<form method='post'>\n"
			+ "	<input type=\"submit\" name=\"coin05\" value=\"5 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"coin10\" value=\"10 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"coin25\" value=\"25 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"cancel\" value=\"Annulla\" />\n"
			+ " <input type='hidden' name='state' value='$state' />"
			+ "</form>\n" 
			+ "</div>";

	private PayStation payStation = new PayStation();

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		addCoins(webRequest);
		String minutes = String.valueOf(payStation.readDisplay());
		String body = TEMPLATE
				.replaceAll("\\$minutes", minutes)
				.replaceAll("\\$state", payStation.saveState());
		webResponse.setBody(body);
	}

	private void addCoins(WebRequest webRequest) {
		try {
			String savedState = webRequest.getParameter("state");
			if (savedState != null) {
				payStation.restore(savedState);
			}
			if (webRequest.getParameter("coin05") != null) {
				payStation.addCoin(5);
			}
			if (webRequest.getParameter("coin10") != null) {
				payStation.addCoin(10);
			}
			if (webRequest.getParameter("coin25") != null) {
				payStation.addCoin(25);
			}
			if (webRequest.getParameter("cancel") != null) {
				payStation.cancel();
			}
		} catch (IllegalCoinException e) {
			throw new RuntimeException(e);
		}
	}

	public void cancel() {
		payStation.cancel();
	}
}
