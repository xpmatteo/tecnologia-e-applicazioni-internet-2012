package it.uninsubria.paystation.web;

import static org.junit.Assert.*;
import it.uninsubria.paystation.web.xml.XmlFragment;

import org.junit.Test;

public class PayStationViewTest {
	PayStationView view = new PayStationView();

	@Test
	public void displaysMinutes() {
		view.set("minutes", "123");
		assertTrue(view.toHtml().contains("Minuti acquistati: 123"));
	}
	
	@Test
	public void containsParagraphWithMinutes() throws Exception {
		view.set("minutes", "456");
		XmlFragment html = new XmlFragment(view.toHtml());
		assertTrue(html.matchesXPath("//p[text()='Minuti acquistati: 456']"));
	}
	
	@Test
	public void containsBuyButton() throws Exception {
		/* verifica che esista un elemento input che ha come
		 * etichetta "Acquista", come attributo name "buy",
		 * e che sia contenuto all'interno di un elemento form 
		 */
	}

}
