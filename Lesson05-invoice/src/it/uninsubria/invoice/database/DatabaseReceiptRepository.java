package it.uninsubria.invoice.database;

import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.ListOfRows;
import it.uninsubria.invoice.domain.Receipt;
import it.uninsubria.invoice.domain.ReceiptRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<Receipt> result = new ArrayList<Receipt>();
		String sql = "select * from receipts";
		ListOfRows rows = database.select(sql);
		for (Map<String, Object> row : rows) {
			int minutes = (Integer) row.get("minutes");
			Receipt receipt = new Receipt(minutes);
			result.add(receipt);
		}
		return result;
	}

	@Override
	public void add(Receipt receipt) {
		String sql = "insert into receipts (minutes) values (?)";
		database.execute(sql, receipt.minutes());
	}

	@Override
	public void notifyPurchase(Receipt receipt) {
		add(receipt);
	}
}
