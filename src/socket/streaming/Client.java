package socket.streaming;

import java.io.BufferedInputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] argv) throws Exception {
		Socket sock = new Socket("172.26.122.106", 8025);
		int count;
		long startTime, endTime;
		String line;
		startTime = System.currentTimeMillis();
		byte[] buffer = new byte[4096];
		BufferedInputStream bufferedInputStream = new BufferedInputStream(sock.getInputStream());
		System.out.println("Streaming started...");
		while ((count = bufferedInputStream.read(buffer, 0, 4096)) > 0) {
			line = new String(buffer, "UTF-8");
			//currently not doing anything with the received data
		}
		bufferedInputStream.close();
		sock.close();
		endTime = System.currentTimeMillis();
		System.out.println("Streaming completed.");
		System.out.println("Total time taken by client = "
				+ (endTime - startTime) / 1000.0 + " sec");
	}
}
