package edu.school.server;

import edu.school.server.Client;
import edu.school.server.MessageProcessing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private final int port;
	public static List<Client> clients = new ArrayList<>();

	public Server(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		try(ServerSocket listener = new ServerSocket(this.port)) {
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			while (true) {
				Socket clientSocket = listener.accept();
				executorService.execute(new MessageProcessing(clientSocket));
			}
		}
	}
}
