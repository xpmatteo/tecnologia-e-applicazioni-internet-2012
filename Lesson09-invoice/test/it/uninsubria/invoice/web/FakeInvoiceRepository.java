package it.uninsubria.invoice.web;

import java.util.ArrayList;
import java.util.List;

import it.uninsubria.invoice.domain.InvoiceRepository;
import it.uninsubria.invoice.domain.LineItem;

public class FakeInvoiceRepository implements InvoiceRepository {
	
	List<LineItem> lineItems = new ArrayList<LineItem>(); 

	@Override
	public void addLineItem(LineItem item) {
		lineItems.add(item);
	}

	@Override
	public long size() {
		return lineItems.size();
	}

	@Override
	public List<LineItem> all() {
		return lineItems;
	}

}
