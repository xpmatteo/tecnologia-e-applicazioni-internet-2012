package it.uninsubria.paystation.domain;

import java.util.ArrayList;
import java.util.List;

public class ReceiptRepository implements PayStationObserver {

	private List<Receipt> receipts = new ArrayList<Receipt>();

	public Integer size() {
		return receipts.size();
	}

	public void notifyPurchase(Receipt receipt) {
		receipts.add(receipt);
	}

	public Object get(int index) {
		return receipts.get(index);
	}

}
