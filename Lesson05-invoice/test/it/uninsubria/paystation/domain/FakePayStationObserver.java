package it.uninsubria.paystation.domain;

import it.uninsubria.invoice.domain.PayStationObserver;
import it.uninsubria.invoice.domain.Receipt;

public class FakePayStationObserver implements PayStationObserver {

	private Receipt receipt;

	public Receipt getReceipt() {
		return receipt;
	}

	@Override
	public void notifyPurchase(Receipt receipt) {
		this.receipt = receipt;
	}

}
