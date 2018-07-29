package main;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

/**
 * @author breaklulz
 */
class Bouncer implements Runnable {

	Selector s;
	HashMap<SelectionKey, Client> clients;
	ServerSocketChannel ssc;

	Bouncer(Selector s, HashMap<SelectionKey, Client> clients) throws Throwable {
		this.s = s;
		this.clients = clients;
		ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress("localhost", 1337));
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			try {
				accept();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	void accept() throws Throwable {
		SocketChannel ac = null;
		while ((ac = ssc.accept()) != null) {
			try {
				SelectionKey rk = ac.register(s, SelectionKey.OP_READ);
				clients.put(rk, new Client(rk));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
