package it.uninsubria.tai.invoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class InvoiceAcceptanceTest {

	private HtmlPage page;

	@Before
	public void clearDatabase() throws Exception {
		Process exec = Runtime
				.getRuntime()
				.exec(new String[] {
					"/bin/bash",
					"-c",
					"echo 'delete from line_items' | /usr/local/bin/mysql -uinvoices -pinvoices invoices_development"
				});
		int result = exec.waitFor();
		assertEquals("il comando mysql non ha funzionato", 0, result);
	}
	
	@Before
	public void loadPage() throws Exception {
		WebClient client = new WebClient();
		page = client.getPage("http://0.0.0.0:8080/index.html");
	}

	@Test
	public void addLineItems() throws Exception {
		HtmlTextInput description = page.getElementByName("description");
		HtmlTextInput amount = page.getElementByName("amountInCents");
		HtmlButton button = (HtmlButton) page.getElementsByTagName("button").get(0);
		description.type("my description");
		amount.type("345");
		button.click();
		sleep(100);
		assertTrue("amount missing", page.asText().contains("€ 3,45"));
		assertTrue("descr missing", page.asText().contains("my description"));
		assertEquals("€ 3,45", getPageTotal());
	}

	private String getPageTotal() {
		List<?> elements = page.getByXPath("//tr[@id='total']");
		HtmlTableRow row = (HtmlTableRow) elements.get(0);
		HtmlTableCell cell = row.getCell(1);
		return cell.asText();
	}

	private void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
