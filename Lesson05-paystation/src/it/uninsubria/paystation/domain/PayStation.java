package it.uninsubria.paystation.domain;

import java.util.ArrayList;
import java.util.List;

public class PayStation {
	private Integer totalMoneyInserted = 0;
	private List<PayStationObserver> observers = new ArrayList<PayStationObserver>();
	
	public PayStation() {
		totalMoneyInserted = 0;
	}
	
	public void addCoin(int faceValue) throws IllegalCoinException {
		if (faceValue == 5 || faceValue == 10 || faceValue == 25) {
			totalMoneyInserted += faceValue;
		} else {
			throw new IllegalCoinException();
		}
	}
	
	public int readDisplay() {
		return minutesBought();
	}

	public Receipt buy() {
		Receipt receipt = new Receipt(minutesBought());
		notifyObservers(receipt);
		return receipt;
	}

	private void notifyObservers(Receipt receipt) {
		for (PayStationObserver observer : this.observers) {
			observer.notifyPurchase(receipt);
		}
	}

	private int minutesBought() {
		return totalMoneyInserted / 5 * 2;
	}

	public void cancel() {
		totalMoneyInserted = 0;
	}

	public String saveState() {
		return Integer.toString(totalMoneyInserted, 2);
	}

	public void restore(String state) {
		totalMoneyInserted = Integer.parseInt(state, 2);
	}

	public void addObserver(PayStationObserver observer) {
		this.observers.add(observer);
	}
}
