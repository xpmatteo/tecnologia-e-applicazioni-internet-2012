package it.uninsubria.paystation.domain;

public class Receipt {
	private int minutes;
	
	public Receipt(int minutes) {
		this.minutes = minutes;
	}
	
	public int minutes() {
		return this.minutes;
	}
}
