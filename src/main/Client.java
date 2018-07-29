package main;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author breaklulz
 */
class Client {

	SelectionKey k;
	SocketChannel c;
	ByteBuffer in;

	Client(SelectionKey k) throws Throwable {
		this.k = k;
		c = (SocketChannel) k.channel();
		c.configureBlocking(false);
	}

	void read() throws Throwable {
		int nread = c.read(in);
	}

}
