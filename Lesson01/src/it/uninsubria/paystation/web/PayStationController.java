package it.uninsubria.paystation.web;

public class PayStationController {

	public String toHtml() {
		return new PayStationView().toHtml();
	}

}
