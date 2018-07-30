package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @author breaklulz
 */
class Writer extends KeyQer {

	Writer(Selector s) {
		super(s);
	}

	@Override
	void handle(SelectionKey k) throws Throwable {
		Client c = (Client) k.attachment();
		c.flush();
	}

}
