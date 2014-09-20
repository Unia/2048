package nl.tudelft.ti2206.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Networking {

	private static final int PORT = 2526;

	private static List<String> addresses = new ArrayList<String>();

	private static Socket socket;
	private static boolean initialized = false;

	private static DataInputStream dInputStream;
	private static DataOutputStream dOutputStream;
	private static ObjectInputStream oInputStream;
	private static ObjectOutputStream oOutputStream;

	public static List<String> initalize() {
		return initLocalAddresses();
	}
	/**
	 * Create a list of local IP addresses.
	 * @return list
	 */
	private static List<String> initLocalAddresses() {

		if (addresses.isEmpty()) {

			try {
				System.out.println("Enumerating network devices...");
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
		return addresses;
	}

	public static String strAddresses() {
		String res = "";
		for (String address : getLocalAddresses()) {
			res += address + "\r\n";
		}
		return res;
	}

	public static List<String> getLocalAddresses() {

		return initLocalAddresses();
	}

	public static void startServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println("Starting server on port " + PORT);

				ServerSocketHints serverSocketHint = new ServerSocketHints();
				serverSocketHint.acceptTimeout = 0;
				ServerSocket serverSocket = Gdx.net.newServerSocket(
						Protocol.TCP, PORT, serverSocketHint);

				while (true) {
					socket = serverSocket.accept(null);

					System.out.println("Accepted incoming connection from "
							+ socket.getRemoteAddress());

					setInput(socket);
					setOutput(socket);

					setInitialized(true);

					while (isConnected()) {
						receiveLoop();
					}
				}
			}
		}).start();
	}

	public static void startClient(final String address, final int port) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Starting client, connecting to " + address
						+ ":" + port);

				SocketHints socketHints = new SocketHints();
				socketHints.connectTimeout = 4000;
				socket = Gdx.net.newClientSocket(Protocol.TCP, address, port,
						socketHints);

				System.out.println("I'm connected to "
						+ socket.getRemoteAddress());

				setInput(socket);
				setOutput(socket);

				setInitialized(true);

				while (isConnected()) {
					receiveLoop();
				}
			}

		}).start();
	}

	@SuppressWarnings("deprecation")
	private static void receiveLoop() {
		Object response;
		try {

			while (isConnected()
					&& (response = dInputStream.readLine()) != null) {
				processResponse(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void setInput(Socket socket) {
		dInputStream = new DataInputStream(socket.getInputStream());

//		try {
//			oInputStream = new ObjectInputStream(socket.getInputStream());
//		} catch (IOException e) {
//			System.err.println("Error setting object input stream:");
//			e.printStackTrace();
//		}
	}

	private static void setOutput(Socket socket) {
		dOutputStream = new DataOutputStream(socket.getOutputStream());

//		try {
//			oOutputStream = new ObjectOutputStream(socket.getOutputStream());
//		} catch (IOException e) {
//			System.err.println("Error setting object output stream:");
//			e.printStackTrace();
//		}

	}

	public static void sendString(String str) {
		System.out.println("sending str = " + str);
		if (isConnected()) {
			try {
				dOutputStream.writeBytes(str);
				// dOutputStream.writeChars(str);
			} catch (IOException e) {
				System.err.println("Unable to send string:");
				e.printStackTrace();
			}
		}
	}

	public static void sendObject(Object object) {
		System.out.println("sending obj = " + object);
		if (isConnected()) {
			try {
				oOutputStream.writeObject(object + "\r\n");
			} catch (IOException e) {
				System.err.println("Unable to send object:");
				e.printStackTrace();
			}
		}
	}

	private static void processResponse(Object object) {

	}

	public static String getRemoteAddress() {
		return socket.getRemoteAddress().replaceFirst("/", "");
	}

	public static Socket getSocket() {
		return socket;
	}

	public static boolean isConnected() {
		return socket.isConnected();
	}

	public static boolean isInitialized() {
		return initialized;
	}

	private static void setInitialized(boolean initialized) {
		System.out.println("setting initialized to " + initialized);
		Networking.initialized = initialized;
	}
	
	
	public static void disconnect() {
		if (isInitialized()) {
			System.out.println("disconnect(): disconnecting...");
			socket.dispose();
			setInitialized(false);
			try {
				dInputStream.close();
				dOutputStream.close();
				oInputStream.close();
				oOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("disconnect(): not initialized");
		}
	}
}
