package edu.school;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private final int port;

	public Server(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		try(ServerSocket listener = new ServerSocket(3000)) {
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			while (true) {
				Socket client = listener.accept();
				executorService.execute(new MessageProcessing(client));
			}
		}
	}
}
