package it.uninsubria.paystation.domain;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReceiptRepository implements PayStationObserver, ReceiptRepository {

	private List<Receipt> receipts = new ArrayList<Receipt>();

	@Override
	public Integer size() {
		return receipts.size();
	}

	@Override
	public void notifyPurchase(Receipt receipt) {
		this.add(receipt);
	}

	@Override
	public List<Receipt> findAll() {
		return receipts;
	}

	@Override
	public void add(Receipt receipt) {
		receipts.add(receipt);
	}
}
