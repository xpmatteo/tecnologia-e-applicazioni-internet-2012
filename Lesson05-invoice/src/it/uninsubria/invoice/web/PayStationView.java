package it.uninsubria.invoice.web;

import it.uninsubria.generic.view.TemplateView;
import it.uninsubria.generic.view.View;

public class PayStationView implements View {
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
