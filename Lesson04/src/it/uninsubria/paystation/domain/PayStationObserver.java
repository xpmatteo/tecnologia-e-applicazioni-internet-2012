package it.uninsubria.paystation.domain;

public interface PayStationObserver {

	void notifyPurchase(Receipt receipt);

}