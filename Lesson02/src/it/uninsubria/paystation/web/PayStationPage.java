package it.uninsubria.paystation.web;


import it.uninsubria.paystation.domain.IllegalCoinException;
import it.uninsubria.paystation.domain.PayStation;
import it.uninsubria.paystation.domain.Receipt;

public class PayStationPage {
	private PayStation payStation = new PayStation();

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		restoreState(webRequest);
		if (acquista(webRequest)) {
			Receipt receipt = payStation.buy();
			webResponse.setBody(receipt.toString());
		} else {
			insertCoin(webRequest);
			PayStationView view = new PayStationView();
			view.set("minutes", payStation.readDisplay());
			view.set("state", payStation.saveState());
			webResponse.setBody(view.toHtml());
		}
	}

	private void restoreState(WebRequest webRequest) {
		String state = webRequest.getParameter("state");
		if (state != null)
			payStation.restore(state);
	}

	private boolean acquista(WebRequest webRequest) {
		return webRequest.getParameter("buy") != null;
	}

	private void insertCoin(WebRequest webRequest) {
		try {
			if (webRequest.getParameter("coin05") != null)
				payStation.addCoin(5);
			if (webRequest.getParameter("coin10") != null)
				payStation.addCoin(10);
		} catch (IllegalCoinException e) {
			throw new RuntimeException(e);
		}
	}
}
