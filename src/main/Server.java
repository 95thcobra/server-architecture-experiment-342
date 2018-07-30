package main;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;

/**
 * @author breaklulz
 */
class Server {

	Server() throws Throwable {
		Selector s = Selector.open();

		Reader r = new Reader(s);
		new Thread(r).start();

		Bouncer b = new Bouncer(s, new InetSocketAddress("localhost", 1337));
		new Thread(b).start();
	}

}
