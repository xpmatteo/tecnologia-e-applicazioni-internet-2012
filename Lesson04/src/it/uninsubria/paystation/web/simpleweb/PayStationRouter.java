package it.uninsubria.paystation.web.simpleweb;

import it.uninsubria.paystation.domain.PayStation;
import it.uninsubria.paystation.domain.InMemoryReceiptRepository;
import it.uninsubria.paystation.web.PayStationPage;
import it.uninsubria.paystation.web.PurchaseListPage;
import it.uninsubria.paystation.web.WebRequest;

public class PayStationRouter {

	private InMemoryReceiptRepository repository;
	
	public PayStationRouter(InMemoryReceiptRepository repository) {
		this.repository = repository;
	}

	public Page getPageFor(WebRequest request) {
		if (request.getPath().equals("/admin")) {
			return new PurchaseListPage(repository);
		}
		PayStation payStation = new PayStation();
		payStation.addObserver(repository);
		PayStationPage payStationPage = new PayStationPage(payStation);
		return payStationPage;
	}

}
