package server;

import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author breaklulz
 */
class Bouncer implements Runnable {

	Selector s;
	ServerSocketChannel ssc;

	Bouncer(Selector s, SocketAddress la) throws Throwable {
		this.s = s;

		ssc = ServerSocketChannel.open().bind(la);
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			try {
				accept();
			} catch (Throwable ignore) {}
		}
	}

	void accept() throws Throwable {
		SocketChannel ac;
		while ((ac = ssc.accept()) != null) {
			ac.configureBlocking(false);
			SelectionKey k = ac.register(s, SelectionKey.OP_READ);
			k.attach(new NetSesh(k));
		}
	}

}
