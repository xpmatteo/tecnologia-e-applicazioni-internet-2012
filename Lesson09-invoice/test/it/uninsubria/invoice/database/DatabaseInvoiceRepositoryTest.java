package it.uninsubria.invoice.database;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.DatabaseConnector;
import it.uninsubria.invoice.database.DatabaseInvoiceRepository;
import it.uninsubria.invoice.domain.InvoiceRepository;
import it.uninsubria.invoice.domain.LineItem;
import it.uninsubria.invoice.domain.Money;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class DatabaseInvoiceRepositoryTest {
	private Connection connection = 
		new DatabaseConnector(
				"invoices",
				"invoices", 
				"jdbc:mysql://localhost/invoices_test", 
				"com.mysql.jdbc.Driver")
			.getConnection();
	private Database database = new Database(connection);
	private InvoiceRepository repository = new DatabaseInvoiceRepository(database);

	@Before
	public void setUp() {
		database.execute("delete from line_items");
	}
	
	@Test
	public void isEmptyInitially() {
		assertEquals(0, repository.size());
	}
	
	@Test
	public void addOneLineItem() throws Exception {
		LineItem original = new LineItem("desc", new Money(12345));
		
		repository.addLineItem(original);
		
		assertEquals(1, repository.size());
		LineItem found = repository.all().get(0);
		assertEquals(original, found);
	}
	
	
	
}
