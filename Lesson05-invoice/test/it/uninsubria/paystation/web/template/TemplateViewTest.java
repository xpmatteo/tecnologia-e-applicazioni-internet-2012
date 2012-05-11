package it.uninsubria.paystation.web.template;

import static org.junit.Assert.assertEquals;
import it.uninsubria.generic.view.TemplateView;

import org.junit.Test;


public class TemplateViewTest {
	
	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		TemplateView view = new TemplateView("test.ftl");
		view.put("user", "Tizius");
	    assertEquals("Hello, Tizius!", view.toHtml());
	}
}
