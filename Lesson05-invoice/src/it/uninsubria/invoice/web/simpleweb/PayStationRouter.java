package it.uninsubria.invoice.web.simpleweb;

import it.uninsubria.generic.web.Page;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.invoice.domain.PayStation;
import it.uninsubria.invoice.domain.ReceiptRepository;
import it.uninsubria.invoice.web.PayStationPage;
import it.uninsubria.invoice.web.PurchaseListPage;

public class PayStationRouter {

	private ReceiptRepository repository;
	
	public PayStationRouter(ReceiptRepository repository) {
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
