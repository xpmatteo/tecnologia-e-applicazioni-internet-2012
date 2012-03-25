package it.uninsubria.paystation.main;

import it.uninsubria.paystation.web.simpleweb.HelloWorldContainer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Main {

	public static void main(String[] args) throws IOException {
		HelloWorldContainer container = new HelloWorldContainer();
		Connection connection = new SocketConnection(container);
		SocketAddress address = new InetSocketAddress(8080);
		connection.connect(address);
	}
}
