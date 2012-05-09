package it.uninsubria.paystation.database;

import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.domain.ReceiptRepository;

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
		for (Map<String, Object> row : database.select(sql)) {
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
}
