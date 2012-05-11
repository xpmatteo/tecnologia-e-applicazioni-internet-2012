package it.uninsubria.invoice.database;

import static org.junit.Assert.assertEquals;
import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.DatabaseConnector;
import it.uninsubria.invoice.domain.InvoiceRepository;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;


public class DatabaseRepositoryTest {
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
		database.execute("delete from receipts");
	}
	
	@Test
	public void isEmptyInitially() {
		assertEquals(new Integer(0), repository.size());
	}
	
}
