package it.uninsubria.invoice.database;

import it.uninsubria.generic.database.Database;
import it.uninsubria.invoice.domain.Invoice;
import it.uninsubria.invoice.domain.InvoiceRepository;

public class DatabaseInvoiceRepository implements InvoiceRepository {

	private final Database database;

	public DatabaseInvoiceRepository(Database database) {
		this.database = database;
	}

	@Override
	public Invoice findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Invoice receipt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return null;
	}

}
