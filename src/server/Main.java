package server;

import java.net.InetSocketAddress;

/**
 * @author breaklulz
 */
class Main {

	public static void main(String[] args) {
		try {
			new Server(new InetSocketAddress("localhost", 1337));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
