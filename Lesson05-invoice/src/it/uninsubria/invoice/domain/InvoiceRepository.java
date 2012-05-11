package it.uninsubria.invoice.domain;


public interface InvoiceRepository {
	Invoice findById(int id);
	void add(Invoice receipt);
	Integer size();
}
