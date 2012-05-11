package it.uninsubria.invoice.web.simpleweb;

import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.DatabaseConnector;
import it.uninsubria.generic.web.Page;
import it.uninsubria.generic.web.RealWebRequest;
import it.uninsubria.generic.web.RealWebResponse;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.generic.web.WebResponse;
import it.uninsubria.invoice.database.DatabaseReceiptRepository;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class PayStationContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		DatabaseConnector connector = new DatabaseConnector("pay_station", "pay_station", "jdbc:mysql://localhost/pay_station_development", "com.mysql.jdbc.Driver");
		Connection connection = connector.getConnection();
		try {
			tryToHandle(connection, request, response);
		} catch (Exception e) {
			response.setCode(500);
			printStackTrace(response, e);
		} finally {
			close(connection);
		}
	}

	private void tryToHandle(Connection connection, Request request, Response response) throws SQLException {
		Database database = new Database(connection);
		DatabaseReceiptRepository repository = new DatabaseReceiptRepository(database);

		WebRequest webRequest = new RealWebRequest(request);
		WebResponse webResponse = new RealWebResponse(response);
		PayStationRouter router = new PayStationRouter(repository);
		Page page = router.getPageFor(webRequest);
		page.handle(webRequest, webResponse);
	}

	private void printStackTrace(Response response, Exception e){
		try {
			PrintStream stream = response.getPrintStream();
			stream.println("<pre>");
			e.printStackTrace(stream);
			stream.close();
			stream.println("</pre>");
		} catch (IOException ignored) {
		}
	}

	private void close(Connection connection) {
		try {
			if (null != connection)
				connection.close();
		} catch (SQLException ignored) {}
	}

}
