package it.uninsubria.invoice.main;

import it.uninsubria.generic.web.StaticAssetContainer;
import it.uninsubria.invoice.web.simpleweb.InvoiceContainer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Main {

	public static void main(String[] args) throws IOException {
		InvoiceContainer payStation = new InvoiceContainer();
		StaticAssetContainer assets = new StaticAssetContainer(payStation);
		Connection connection = new SocketConnection(assets);
		SocketAddress address = new InetSocketAddress(8080);
		connection.connect(address);
	}
}
