package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author breaklulz
 */
class Reader implements Runnable {

	Selector s;
	HashMap<SelectionKey, Client> clients;

	Reader(Selector s, HashMap<SelectionKey, Client> clients) throws Throwable {
		this.s = s;
		this.clients = clients;
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			int ns = -1;

			try {
				ns = s.select();
			} catch (Throwable e) {
				e.printStackTrace();
			}

			if (ns != -1) {
				read();
			}
		}
	}

	void read() {
		Iterator<SelectionKey> keys = s.selectedKeys().iterator();

		while (keys.hasNext()) {
			try {
				SelectionKey k = keys.next();
				keys.remove();

				Client c = clients.get(k);
				c.read();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
