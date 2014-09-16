package socket.streaming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

public class Server {
	private static final int _OPTIMAL_NUMBER_OF_LINES = 27;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8025);
		File file = new File("C:\\EclipseWorkspace\\Files\\input_data1.psv");
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
		String line;
		int count;
		while (true) {
			Socket socket = null;
			try{
			socket = serverSocket.accept();
			}catch(java.net.SocketException e){
				System.out.println("Socket is closed!");
				return;
			}
			OutputStream out = socket.getOutputStream();
			long counter = 1;
			String totalLine = "";
			while ((line = buffReader.readLine()) != null) {
				totalLine += line + "#";
				if (counter % _OPTIMAL_NUMBER_OF_LINES == 0) {
					byte[] buffer = new byte[4096];
					totalLine += StringUtils.repeat("#",
							4096 - totalLine.length());
					buffer = totalLine.getBytes("UTF-8");
					count = buffer.length;
					out.write(buffer, 0, 4096);
					out.flush();
					totalLine = "";
				}
				counter++;
			}
			socket.close();
			buffReader.close();
			serverSocket.close();
		}
	}
}
