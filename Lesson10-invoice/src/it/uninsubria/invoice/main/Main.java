package it.uninsubria.invoice.main;

import it.uninsubria.generic.web.StaticAssetContainer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Main {

	public static void main(String[] args) {
		try {
			InvoiceContainer invoices = new InvoiceContainer();
			StaticAssetContainer assets = new StaticAssetContainer(invoices);
			Connection connection = new SocketConnection(assets);
			SocketAddress address = new InetSocketAddress(8080);
			connection.connect(address);
			System.out.println(Main.class.getCanonicalName());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
