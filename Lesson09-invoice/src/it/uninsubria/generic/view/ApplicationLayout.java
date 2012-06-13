package it.uninsubria.generic.view;


public class ApplicationLayout implements View {

	private TemplateView template = new TemplateView("layout.ftl");
	private final View view;

	public ApplicationLayout(View view) {
		this.view = view;
	}

	@Override
	public String toHtml() {
		template.put("content_for_layout", view.toHtml());
		return template.toHtml();
	}

}
