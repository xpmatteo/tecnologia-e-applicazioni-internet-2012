package it.uninsubria.paystation.web;

public class PayStationView {

	private int minutes;

	public String toHtml() {
		String template = "<html>\n" + 
				"	<head>\n" + 
				"		<title>Pay Station</title>		\n" + 
				"	</head>\n" + 
				"	<body>\n" + 
				"		<p id=\"display\">Minuti comprati: $minutes</p>\n" + 
				"		<form method=\"post\">\n" + 
				"			<input type=\"submit\" name=\"coin5\" value=\"5\"/>\n" + 
				"			<input type=\"submit\" name=\"coin10\" value=\"10\"/>\n" + 
				"			<input type=\"submit\" name=\"coin25\" value=\"25\"/>\n" + 
				"			<br/>\n" + 
				"			<input type=\"submit\" name=\"buy\" value=\"Compra\"/>\n" + 
				"			<input type=\"submit\" name=\"cancel\" value=\"Annulla\"/>\n" + 
				"		</form>\n" + 
				"	</body>\n" + 
				"</html>";
		return template.replace("$minutes", String.valueOf(minutes));
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}
