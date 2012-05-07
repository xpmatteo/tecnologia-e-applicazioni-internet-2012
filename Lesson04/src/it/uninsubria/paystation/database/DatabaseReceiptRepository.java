package it.uninsubria.paystation.database;

import java.util.List;

import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.domain.ReceiptRepository;

public class DatabaseReceiptRepository implements ReceiptRepository {

	private final Database database;

	public DatabaseReceiptRepository(Database database) {
		this.database = database;
	}

	@Override
	public Integer size() {
		ListOfRows rows = database.select("select count(*) as count from receipts"); 
		Long result = (Long) rows.get(0).get("count");
		return result.intValue();
	}

	@Override
	public List<Receipt> findAll() {
		return null;
	}

	@Override
	public void add(Receipt receipt) {

	}
}
