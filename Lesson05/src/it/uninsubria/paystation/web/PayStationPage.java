package it.uninsubria.paystation.web;


import it.uninsubria.paystation.domain.IllegalCoinException;
import it.uninsubria.paystation.domain.PayStation;
import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.web.simpleweb.Page;

public class PayStationPage extends Page {
	private PayStation payStation;
	
	public PayStationPage(PayStation payStation) {
		this.payStation = payStation;
	}

	public void handle(WebRequest webRequest, WebResponse webResponse) {
		webResponse.setContentType("text/html");
		restoreState(webRequest);
		if (acquista(webRequest)) {
			Receipt receipt = payStation.buy();
			webResponse.setBody(receipt.toString());
		} else {
			insertCoin(webRequest);
			PayStationView view = createView();
			ApplicationLayout layout = new ApplicationLayout(view);
			webResponse.setBody(layout.toHtml());
		}
	}

	private PayStationView createView() {
		PayStationView view = new PayStationView();
		view.setMinutes(payStation.readDisplay());
		view.setState(payStation.saveState());
		return view;
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
