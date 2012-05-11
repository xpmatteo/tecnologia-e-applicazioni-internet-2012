package it.uninsubria.paystation.domain;

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
