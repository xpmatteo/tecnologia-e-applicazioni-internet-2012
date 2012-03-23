package it.uninsubria.paystation.web;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class PayStationViewTest {

	@Test
	public void containsMinutiComprati() {
		PayStationView view = new PayStationView();
		assertTrue(view.toHtml(), view.toHtml().contains("Minuti comprati: 0"));
		
		view.setMinutes(7);
		assertTrue(view.toHtml(), view.toHtml().contains("Minuti comprati: 7"));
	}

}
