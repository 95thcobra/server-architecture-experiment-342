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
	ByteBuffer in, out;

	Client(SelectionKey k) throws Throwable {
		this.k = k;

		c = (SocketChannel) k.channel();
		c.configureBlocking(false);

		in = ByteBuffer.allocateDirect(256);
		out = ByteBuffer.allocateDirect(256);
	}

	void read() throws Throwable {
		int nread = c.read(in);
	}

	void flush() throws Throwable {
		int nwrite = c.write(out);
		out.clear();
	}

}
