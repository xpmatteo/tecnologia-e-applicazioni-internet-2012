package it.uninsubria.tai.invoice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class InvoiceAcceptanceTest {

	@Before
	public void clearDatabase() throws Exception {
		Process exec = Runtime
				.getRuntime()
				.exec(new String[] {
					"/bin/bash",
					"-c",
					"echo 'delete from line_items' | /usr/local/bin/mysql -uroot invoices_development"
				});
		int result = exec.waitFor();
		Assert.assertEquals(0, result);
	}

	@Test
	public void test() throws Exception {
		WebClient client = new WebClient();
		client.setJavaScriptEnabled(true);
		HtmlPage page = client.getPage("http://0.0.0.0:8080/index.html");
		HtmlTextInput description = page.getElementByName("description");
		HtmlTextInput amount = page.getElementByName("amountInCents");
		HtmlButton button = (HtmlButton) page.getElementsByTagName("button")
				.get(0);
		description.type("my description");
		amount.type("345");
		button.click();
		sleep(100);
		assertTrue("amount missing", page.asText().contains("€ 3,45"));
		assertTrue("descr missing", page.asText().contains("my description"));
	}

	private void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
