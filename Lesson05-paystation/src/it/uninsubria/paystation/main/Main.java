package it.uninsubria.paystation.main;

import it.uninsubria.paystation.web.simpleweb.PayStationContainer;
import it.uninsubria.paystation.web.simpleweb.StaticAssetContainer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Main {

	public static void main(String[] args) throws IOException {
		PayStationContainer payStation = new PayStationContainer();
		StaticAssetContainer assets = new StaticAssetContainer(payStation);
		Connection connection = new SocketConnection(assets);
		SocketAddress address = new InetSocketAddress(8080);
		connection.connect(address);
	}
}
