package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author breaklulz
 */
class Reactor implements Runnable {

	Selector s;
	Reader r;
	Writer w;

	Reactor(Selector s, Reader r, Writer w) {
		this.s = s;
		this.r = r;
		this.w = w;
	}

	@Override
	public void run() {
		new Thread(r).start();
		new Thread(w).start();

		while (s.isOpen()) {
			loop();
		}
	}

	void loop() {
		try {
			s.select();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Iterator<SelectionKey> keys = s.selectedKeys().iterator();

		while (keys.hasNext()) {
			SelectionKey k = keys.next();
			keys.remove();

			switch (k.interestOps()) {
			case SelectionKey.OP_READ:
				r.q.offer(k);
				break;

			case SelectionKey.OP_WRITE:
				r.q.offer(k);
				break;
			}
		}
	}

}
