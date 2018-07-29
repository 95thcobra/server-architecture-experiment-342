package main;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;

/**
 * @author breaklulz
 */
class Server {

	Selector s;
	HashMap<SelectionKey, Client> clients;

	Server() throws Throwable {
		s = Selector.open();

		clients = new HashMap<SelectionKey, Client>();

		Reader r = new Reader(s, clients);
		new Thread(r).start();

		Bouncer b = new Bouncer(s, clients);
		new Thread(b).start();
	}

}
