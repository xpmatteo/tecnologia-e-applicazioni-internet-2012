package it.uninsubria.paystation.web;

import java.util.HashMap;
import java.util.Map;

public class PayStationView {
	private static final String TEMPLATE = "<div style=\"border:1px solid black; margin: 1em; padding: 1em; width: 20em\">\n"
			+ "<p style=\"border: 1px solid gray; padding: 0.2em\">Minuti acquistati: %minutes</p>\n"
			+ "<form method='post'>\n"
			+ "	<input type=\"hidden\" name=\"state\" value=\"%state\" />\n"
			+ "	<input type=\"submit\" name=\"coin05\" value=\"5 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"coin10\" value=\"10 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"coin25\" value=\"25 &cent;\" />\n"
			+ "	<input type=\"submit\" name=\"cancel\" value=\"Annulla\" />\n"
			+ "	<input type=\"submit\" name=\"buy\" value=\"Acquista\" />\n"
			+ "</form>\n" + "</div>";

	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	public void set(String name, Object value) {
		parameters.put(name, value);
	}

	public String toHtml() {
		return replaceParameters(TEMPLATE);
	}

	private String replaceParameters(String template) {
		for (String name : parameters.keySet()) {
			Object value = parameters.get(name);
			template = template.replace("%" + name, value.toString());
		}
		return template;
	}

}
