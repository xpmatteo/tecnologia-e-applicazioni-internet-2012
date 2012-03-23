package it.uninsubria.paystation.web;

import static org.junit.Assert.*;

import org.junit.Test;

public class PayStationControllerTest {

	@Test
	public void returnsPayView() throws Exception {
		PayStationController controller = new PayStationController();
		assertTrue(controller.toHtml(), controller.toHtml().contains("Minuti comprati: 0"));
	}

}
