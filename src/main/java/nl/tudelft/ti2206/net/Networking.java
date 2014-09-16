package nl.tudelft.ti2206.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Networking {

	private static final int PORT = 2526;

	private static List<String> addresses = new ArrayList<String>();

	public static void initalize() {
		initIPv4Address();
		System.out.println(listIPAdresses());
	}

	private static void initIPv4Address() {

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			for (NetworkInterface ni : Collections.list(interfaces)) {
				for (InetAddress address : Collections.list(ni
						.getInetAddresses())) {
					if (address instanceof Inet4Address) {
						if (!address.getHostAddress().equals("127.0.0.1")) {
							addresses.add(address.getHostAddress());
						}
					}
				}
			}
		} catch (SocketException se) {
			se.printStackTrace();
		}
	}

	private static String listIPAdresses() {
		String res = "";
		for (String address : addresses) {
			res += address + "\n";
		}
		return res;
	}

	public static void startServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				ServerSocketHints serverSocketHint = new ServerSocketHints();
				// 0 means no timeout. Probably not the greatest idea in
				// production!
				serverSocketHint.acceptTimeout = 0;

				// Only one app can listen to a port at a time, keep in mind
				// many ports are reserved
				// especially in the lower numbers ( like 21, 80, etc )
				ServerSocket serverSocket = Gdx.net.newServerSocket(
						Protocol.TCP, PORT, serverSocketHint);

				// Loop forever
				while (true) {
					// Create a socket

					Socket socket = serverSocket.accept(null);

					// Read data from the socket into a BufferedReader
					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));

					try {
						// Read to the next newline (\n) and display that text
						// on labelMessage
						System.out.println(buffer.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start(); // And, start the thread running
	}

	public static void startClient(final String address, final int port) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				SocketHints socketHints = new SocketHints();
				// Socket will time our in 4 seconds
				socketHints.connectTimeout = 4000;
				// create the socket and connect to the server entered in the
				// text box ( x.x.x.x format ) on port 9021
				Socket socket = Gdx.net.newClientSocket(Protocol.TCP, address,
						port, socketHints);

				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				String str = "Connection established";
				out.print(str);
			}

		});
	}
}
