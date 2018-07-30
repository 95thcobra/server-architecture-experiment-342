package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author breaklulz
 */
class Reader implements Runnable {

	Selector s;

	Reader(Selector s) throws Throwable {
		this.s = s;
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

				Client c = (Client) k.attachment();
				c.read();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
