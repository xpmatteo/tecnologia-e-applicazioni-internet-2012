package it.uninsubria.invoice.domain;

import java.util.List;


public interface InvoiceRepository {
	void addLineItem(LineItem item);
	long size();
	List<LineItem> all();
}
