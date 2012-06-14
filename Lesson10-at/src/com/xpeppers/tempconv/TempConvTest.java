
package com.xpeppers.tempconv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TempConvTest {
	
	@Test
	public void convertsTemperatureFromCelsiusToFahrenheit() throws Exception {
		TempConvPage page = TempConvPage.getTempConvPage();
		assertEquals("", page.getDisplay());
		page.setTemperature("0");
		TempConvPage secondPage = page.submit();
		assertEquals("0.0 C° = 32.0 F°", secondPage.getDisplay());
	}

}
