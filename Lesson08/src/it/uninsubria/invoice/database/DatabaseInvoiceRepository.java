package it.uninsubria.invoice.database;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.ListOfRows;
import it.uninsubria.invoice.domain.InvoiceRepository;
import it.uninsubria.invoice.domain.LineItem;
import it.uninsubria.invoice.domain.Money;

public class DatabaseInvoiceRepository implements InvoiceRepository {

	private final Database database;

	public DatabaseInvoiceRepository(Database database) {
		this.database = database;
	}

	@Override
	public void addLineItem(LineItem item) {
		String sql = "insert into line_items (description, amount) values (?, ?)";
		database.execute(sql, item.description(), item.amount().toCents());
	}

	@Override
	public long size() {
		ListOfRows rows = database.select("select count(*) as count from line_items");
		return (Long) rows.get(0).get("count");
	}

	@Override
	public List<LineItem> all() {
		List<LineItem> result = new ArrayList<LineItem>();
		ListOfRows rows = database.select("select * from line_items");
		for (Map<String, Object> map : rows) {
			String description = (String) map.get("description");
			Money amount = new Money((BigDecimal) map.get("amount"));
			result.add(new LineItem(description, amount));
		}
		return result;
	}

}
