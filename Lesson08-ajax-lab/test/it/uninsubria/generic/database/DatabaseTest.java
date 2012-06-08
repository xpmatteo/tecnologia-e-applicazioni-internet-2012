package it.uninsubria.generic.database;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	private Database database;
	private Connection connection;
	
	@Before
	public void setUp() throws Exception {		
		connection = getConnection();
		database = new Database(connection);
		drop_temp_table();
		create_test_table();
	}
	
	@After
	public void tearDown() throws Exception {
		connection.close();
	}
	
	@Test
	public void insertThenSelect() throws Exception {
		database.execute("insert into prova (name) values (?)", "pippo");
		database.execute("insert into prova (name) values (?)", "pluto");

		ListOfRows rows = database.select("select * from prova");
		assertEquals(2, rows.size());
		assertEquals("pippo", rows.get(0).get("name"));
		assertEquals("pluto", rows.get(1).get("name"));
	}

	private void drop_temp_table() {
		try {
			database.execute("drop table prova");
		} catch (Exception ignored) {}
	}

	private void create_test_table() throws SQLException, ClassNotFoundException {
		String sql = "create table prova (id integer auto_increment, name varchar(255), primary key(id))";
		database.execute(sql);
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/pay_station_test", "pay_station", "pay_station");
	}
}
