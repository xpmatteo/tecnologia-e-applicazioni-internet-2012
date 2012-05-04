package it.uninsubria.paystation.web;

import static org.junit.Assert.assertTrue;
import it.uninsubria.paystation.web.xml.XmlFragment;

import org.junit.Test;

public class PayStationViewTest {
	PayStationView view = new PayStationView();

	@Test
	public void displaysMinutes() {
		view.setMinutes(456);
		assertMatchesXPath("//p[text()='Minuti acquistati: 456']");
	}

	@Test
	public void savesHiddenState() {
		view.setState("pippo");
		assertMatchesXPath("//input[@type='hidden'][@name='state'][@value='pippo']");
	}

	@Test
	public void containsBuyButton() throws Exception {
		/* verifica che esista un elemento input che ha come
		 * etichetta "Acquista", come attributo name "buy",
		 * e che sia contenuto all'interno di un elemento form 
		 */
		assertMatchesXPath("//input[@name='buy'][@value='Acquista']");
	}

	private void assertMatchesXPath(String xpath) {
		String body = view.toHtml();
		XmlFragment html = new XmlFragment(body);
		String message = String.format("Expected \n%s\nnot found in \n%s", xpath, body);
		assertTrue(message, html.matchesXPath(xpath));
	}	
}
