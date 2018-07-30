package main;

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
		ssc = ServerSocketChannel.open();
		ssc.bind(la);
	}

	@Override
	public void run() {
		while (s.isOpen()) {
			try {
				accept();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	void accept() throws Throwable {
		SocketChannel ac = null;
		while ((ac = ssc.accept()) != null) {
			try {
				SelectionKey rk = ac.register(s, SelectionKey.OP_READ);
				rk.attach(new Client(rk));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
