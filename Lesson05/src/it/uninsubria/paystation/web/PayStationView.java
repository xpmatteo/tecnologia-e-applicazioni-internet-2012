package it.uninsubria.paystation.web;

import it.uninsubria.paystation.web.template.TemplateView;

public class PayStationView {
	private TemplateView template = new TemplateView("pay_station.ftl");
	
	public String toHtml() {
		return template.toHtml();
	}

	public void setMinutes(int minutes) {
		template.put("minutes", minutes);
	}

	public void setState(String state) {
		template.put("state", state);
	}
}
