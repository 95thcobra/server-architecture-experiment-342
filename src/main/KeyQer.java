package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author breaklulz
 */
class KeyQer implements Runnable {

	Selector s;
	LinkedBlockingQueue<SelectionKey> q = new LinkedBlockingQueue<>();

	KeyQer(Selector s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			SelectionKey k;

			try {
				k = q.take();
			} catch (InterruptedException ignore) {
				continue;
			}

			Client c = (Client) k.attachment();

			if (k.isReadable()) {
				c.read();
			} else if (k.isWritable()) {
				c.flush();
			}
		}
	}

	boolean q(SelectionKey k) {
		return q.offer(k);
	}

}
