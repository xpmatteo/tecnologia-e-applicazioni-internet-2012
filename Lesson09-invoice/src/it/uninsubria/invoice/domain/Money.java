package it.uninsubria.invoice.domain;

import java.math.BigDecimal;



public class Money {

	private Integer value;

	public Money(String string) {
		if (hasDecimalPart(string)) {
			this.value = Integer.parseInt(string.replace(",", ""));
		} else {
			this.value = 100 * Integer.parseInt(string);
		}			
	}

	public Money(int value) {
		this.value = value;
	}

	public Money(BigDecimal bigDecimal) {
		this.value = bigDecimal.intValue();
	}

	public int toCents() {
		return value;
	}	

	@Override
	public String toString() {
		return String.format("%d,%02d", value / 100, value % 100);
	}
	
	private boolean hasDecimalPart(String string) {
		return string.indexOf(",") != -1;
	}

	@Override
	public int hashCode() {		
		return value.hashCode();
	}

	@Override
	// auto-generato da Eclipse (Source->Generate hashCode and equals)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
