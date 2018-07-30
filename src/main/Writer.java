package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author breaklulz
 */
class Writer implements Runnable {

	Selector s;
	LinkedBlockingQueue<SelectionKey> q;

	Writer(Selector s) {
		this.s = s;
		q = new LinkedBlockingQueue<SelectionKey>();
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			try {
				SelectionKey k = q.take();

				Client c = (Client) k.attachment();
				c.flush();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
