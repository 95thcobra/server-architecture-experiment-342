package main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author breaklulz
 */
class Client {

	SelectionKey k;
	SocketChannel sc;
	ByteBuffer in, out;

	Client(SelectionKey k) {
		this.k = k;

		sc = (SocketChannel) k.channel();

		in = ByteBuffer.allocateDirect(256);
		out = ByteBuffer.allocateDirect(256);
	}

	void read() {
		try {
			sc.read(in);
		} catch (IOException ignore) {
			return;
		}
	}

	void flush() {
		int nwrite;
		try {
			nwrite = sc.write(out);
		} catch (IOException ignore) {}
		out.clear();
		k.interestOps(SelectionKey.OP_READ);
	}

}
