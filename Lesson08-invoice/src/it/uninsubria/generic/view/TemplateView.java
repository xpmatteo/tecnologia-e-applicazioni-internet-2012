package it.uninsubria.generic.view;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateView {

	private final Map<String, Object> model = new HashMap<String, Object>();
	private final String templateName;
	
	public TemplateView(String templateName) {
		this.templateName = templateName;
	}

	public void put(String key, Object value) {
		model.put(key, value);
	}

	public String toHtml() {
		try {
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File("templates"));
			Template template = configuration.getTemplate(templateName);

			StringWriter writer = new StringWriter();
			template.process(model, writer);
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		}
	}

}