package it.uninsubria.paystation.web.simpleweb;

import static org.junit.Assert.assertEquals;
import it.uninsubria.generic.web.Page;
import it.uninsubria.invoice.domain.InMemoryReceiptRepository;
import it.uninsubria.invoice.web.PayStationPage;
import it.uninsubria.invoice.web.PurchaseListPage;
import it.uninsubria.invoice.web.simpleweb.PayStationRouter;
import it.uninsubria.paystation.web.FakeWebRequest;

import org.junit.Test;

public class PayStationRouterTest {

	InMemoryReceiptRepository repository = new InMemoryReceiptRepository();
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
