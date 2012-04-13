package it.uninsubria.paystation.web.simpleweb;

import static org.junit.Assert.*;
import it.uninsubria.paystation.domain.ReceiptRepository;
import it.uninsubria.paystation.web.FakeWebRequest;
import it.uninsubria.paystation.web.PayStationPage;
import it.uninsubria.paystation.web.PurchaseListPage;
import it.uninsubria.paystation.web.WebRequest;

import org.junit.Test;
import org.simpleframework.http.Request;

public class PayStationRouterTest {

	ReceiptRepository repository = new ReceiptRepository();
	PayStationRouter router = new PayStationRouter(repository);
	
	@Test
	public void returnsPaystationPage() {
		FakeWebRequest request = new FakeWebRequest();
		request.setPath("/anything");
		Page page = router.getPageFor(request);
		assertEquals(PayStationPage.class, page.getClass());
	}

	@Test
	public void returnsAdminPage() {
		FakeWebRequest request = new FakeWebRequest();
		request.setPath("/admin");
		Page page = router.getPageFor(request);
		assertEquals(PurchaseListPage.class, page.getClass());
	}

}
