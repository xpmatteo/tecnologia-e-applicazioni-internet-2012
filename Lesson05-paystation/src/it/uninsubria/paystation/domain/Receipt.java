package it.uninsubria.paystation.domain;


public class Receipt {
	private int minutes;
	
	public Receipt(int minutes) {
		this.minutes = minutes;
	}
	
	public int minutes() {
		return this.minutes;
	}
	
	@Override
	public String toString() {
		return "Ticket for " + minutes + " minutes";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minutes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receipt other = (Receipt) obj;
		if (minutes != other.minutes)
			return false;
		return true;
	}
	
	
}
