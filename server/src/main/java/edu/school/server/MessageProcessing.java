package edu.school.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageProcessing implements Runnable{
	private Client client;
	private final Socket socket;

	public MessageProcessing(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String inline;
		String name;

		try (
				PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				) {
			while (true) {
				printWriter.println("What is name?");
				if ((name = bufferedReader.readLine()) != null) {
					client = new Client(name, this.socket, printWriter, bufferedReader);
					Clients.clients.add(client);
					break ;
				}
			}
			printWriter.println("Welcome " + client.getName());
			while (true) {
				if ((inline = bufferedReader.readLine()).equals("bye")) {
					break ;
				}
				for (Client c: Clients.clients) {
					c.getPrintWriter().println(client.getName() + ": "  + inline);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
