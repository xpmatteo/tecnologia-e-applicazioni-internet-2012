package it.uninsubria.paystation.web;

import static org.junit.Assert.*;

import org.junit.Test;

public class PayStationViewTest {

	@Test
	public void displaysMinutes() {
		PayStationView view = new PayStationView();
		view.set("minutes", "123");
		assertTrue(view.toHtml().contains("Minuti acquistati: 123"));
	}

}
