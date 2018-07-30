package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @author breaklulz
 */
class Reader extends KeyQer {

	Reader(Selector s) {
		super(s);
	}

	@Override
	void handle(SelectionKey k) throws Throwable {
		Client c = (Client) k.attachment();
		c.read();
	}

}
