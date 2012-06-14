package com.xpeppers.tempconv;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class TempConvPage {

	private static final String BASE_URL = "http://localhost:8080/c2f";
	private HtmlPage page;

	private TempConvPage(HtmlPage page) {
		this.page = page;
	}

	public void setTemperature(String temperatureString) throws IOException {
		HtmlTextInput celsius = page.getElementByName("celsius");
		celsius.type(temperatureString);
	}

	public TempConvPage submit() throws IOException {
		HtmlPage newPage = page.getElementById("submit").click();
		return new TempConvPage(newPage);
	}

	public String getDisplay() {
		return page.getElementById("display").getTextContent();
	}

	public static TempConvPage getTempConvPage() throws IOException {
		return getTempConvPage(BASE_URL);
	}

	private static TempConvPage getTempConvPage(String url) throws IOException, MalformedURLException {
		WebClient client = new WebClient();
		HtmlPage page = client.getPage(url);
		return new TempConvPage(page);
	}
}
