package main;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author breaklulz
 */
class Reactor implements Runnable {

	Selector s;
	KeyQer r, w;

	Reactor(Selector s, KeyQer r, KeyQer w) {
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
		s.selectedKeys().clear();

		try {
			s.select();
		} catch (IOException ignore) {}

		s.selectedKeys().iterator().forEachRemaining(k -> {
			if (k.isReadable()) {
				r.q(k);
			} else if (k.isWritable()) {
				w.q(k);
			}
		});
	}

}
