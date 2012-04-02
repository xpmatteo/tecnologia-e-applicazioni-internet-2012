package it.uninsubria.paystation.web;


import it.uninsubria.paystation.domain.IllegalCoinException;
import it.uninsubria.paystation.domain.PayStation;

public class PayStationPage {
	private PayStation payStation = new PayStation();

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		handleParameters(webRequest);
		PayStationView view = new PayStationView();
		view.set("minutes", payStation.readDisplay());
		view.set("conversationState", payStation.saveState());
		webResponse.setBody(view.toHtml());
	}

	private void handleParameters(WebRequest webRequest) {
		try {
			String state = webRequest.getParameter("state");
			if (state != null)
				payStation.restore(state);
			if (webRequest.getParameter("coin05") != null)
				payStation.addCoin(5);
			if (webRequest.getParameter("coin10") != null)
				payStation.addCoin(10);
		} catch (IllegalCoinException e) {
			throw new RuntimeException(e);
		}
	}
}
