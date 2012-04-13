package it.uninsubria.paystation.web.template;

import it.uninsubria.paystation.web.template.TemplateView;

import org.junit.*;

import static org.junit.Assert.*;


public class TemplateViewTest {
	
	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		TemplateView view = new TemplateView("test.ftl");
		view.put("user", "Tizius");
	    assertEquals("Hello, Tizius!", view.toHtml());
	}
}
