package it.uninsubria.paystation.domain;

public class PayStation {
	Integer totalMoneyInserted;
	
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
		return new Receipt(minutesBought());
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

}
