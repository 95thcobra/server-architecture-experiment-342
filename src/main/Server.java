package main;

import java.net.SocketAddress;
import java.nio.channels.Selector;

/**
 * @author breaklulz
 */
class Server {

	Server(SocketAddress la) throws Throwable {
		Selector s = Selector.open();

		new Thread(new Reactor(s, new Reader(s), new Writer(s))).start();
		new Thread(new Bouncer(s, la)).start();
	}

}
