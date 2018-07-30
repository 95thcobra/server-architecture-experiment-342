package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.LinkedBlockingQueue;

abstract class KeyQer implements Runnable {

	Selector s;
	LinkedBlockingQueue<SelectionKey> q;

	KeyQer(Selector s) {
		this.s = s;
		q = new LinkedBlockingQueue<SelectionKey>();
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			try {
				handle(q.take());
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	abstract void handle(SelectionKey k) throws Throwable;

}
