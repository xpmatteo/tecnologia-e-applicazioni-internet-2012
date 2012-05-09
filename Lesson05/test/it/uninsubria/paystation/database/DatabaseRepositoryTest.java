package it.uninsubria.paystation.database;

import static org.junit.Assert.assertEquals;
import it.uninsubria.paystation.domain.Receipt;
import it.uninsubria.paystation.domain.ReceiptRepository;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class DatabaseRepositoryTest {
	private Connection connection = 
		new DatabaseConnector(
				"pay_station",
				"pay_station", 
				"jdbc:mysql://localhost/pay_station_test", 
				"com.mysql.jdbc.Driver")
			.getConnection();
	private Database database = new Database(connection);
	private ReceiptRepository repository = new DatabaseReceiptRepository(database);

	@Before
	public void setUp() {
		database.execute("delete from receipts");
	}
	
	@Test
	public void isEmptyInitially() {
		assertEquals(new Integer(0), repository.size());
	}
	
	@Test
	public void remembersOneReceipt() throws Exception {
		Receipt receipt = new Receipt(123);
		
		repository.add(receipt);
		
		List<Receipt> all = repository.findAll();
		assertEquals(1, all.size());
		assertEquals(receipt, all.get(0));
	}
	
	@Test
	public void remembersTwoReceipts() throws Exception {
		Receipt first = new Receipt(111);
		Receipt second = new Receipt(222);
		
		repository.add(first);
		repository.add(second);
		
		List<Receipt> all = repository.findAll();
		assertEquals(2, all.size());
		assertEquals(first, all.get(0));
		assertEquals(second, all.get(1));
	}


}
