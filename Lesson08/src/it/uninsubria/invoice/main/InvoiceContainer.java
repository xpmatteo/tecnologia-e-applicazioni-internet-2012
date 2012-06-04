package it.uninsubria.invoice.main;

import it.uninsubria.generic.database.Database;
import it.uninsubria.generic.database.DatabaseConnector;
import it.uninsubria.generic.web.Page;
import it.uninsubria.generic.web.RealWebRequest;
import it.uninsubria.generic.web.RealWebResponse;
import it.uninsubria.generic.web.WebRequest;
import it.uninsubria.generic.web.WebResponse;
import it.uninsubria.invoice.database.DatabaseInvoiceRepository;
import it.uninsubria.invoice.domain.InvoiceRepository;
import it.uninsubria.invoice.web.InvoicePage;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class InvoiceContainer implements Container {

	@Override
	public void handle(Request request, Response response) {
		DatabaseConnector connector = new DatabaseConnector("invoices", "invoices", "jdbc:mysql://localhost/invoices_development", "com.mysql.jdbc.Driver");
		Connection connection = null;
		try {
			connection = connector.getConnection();
			tryToHandle(connection, request, response);
		} catch (Exception e) {
			response.setCode(500);
			printStackTrace(response, e);
		} finally {
			close(connection);
		}
	}

	private void tryToHandle(Connection connection, Request request, Response response) throws SQLException {
		WebRequest webRequest = new RealWebRequest(request);
		WebResponse webResponse = new RealWebResponse(response);
		InvoiceRepository repository = new DatabaseInvoiceRepository(new Database(connection));
		Page page = new InvoicePage(repository);
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
