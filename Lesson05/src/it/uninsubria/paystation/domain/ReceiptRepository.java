package it.uninsubria.paystation.domain;

import java.util.List;

public interface ReceiptRepository extends PayStationObserver {
	List<Receipt> findAll();
	void add(Receipt receipt);
	Integer size();
}
