package it.uninsubria.invoice.domain;

public interface PayStationObserver {

	void notifyPurchase(Receipt receipt);

}