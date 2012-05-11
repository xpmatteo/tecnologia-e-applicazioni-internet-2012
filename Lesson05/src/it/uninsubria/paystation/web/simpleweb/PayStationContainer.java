package it.uninsubria.paystation.web.simpleweb;

import it.uninsubria.paystation.database.Database;
import it.uninsubria.paystation.database.DatabaseConnector;
import it.uninsubria.paystation.database.DatabaseReceiptRepository;
import it.uninsubria.paystation.web.WebRequest;
import it.uninsubria.paystation.web.WebResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public class PayStationContainer implements Container {

	private DatabaseConnector connector 
		= new DatabaseConnector("pay_station", "pay_station", "jdbc:mysql://localhost/pay_station_development", "com.mysql.jdbc.Driver");
	private Connection connection = connector.getConnection();
	private Database database = new Database(connection );
	private DatabaseReceiptRepository repository = new DatabaseReceiptRepository(database);

	@Override
	public void handle(Request request, Response response) {
		try {
			tryToHandle(request, response);
		} catch (Exception e) {
			response.setCode(500);
			printStackTrace(response, e);
		}
	}

	private void tryToHandle(Request request, Response response) {
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
}
